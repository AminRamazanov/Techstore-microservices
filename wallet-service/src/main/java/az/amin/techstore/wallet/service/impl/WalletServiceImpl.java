package az.amin.techstore.wallet.service.impl;

import az.amin.techstore.wallet.dao.InboxEvent;
import az.amin.techstore.wallet.dao.OutboxEvent;
import az.amin.techstore.wallet.dao.WalletEntity;
import az.amin.techstore.wallet.dao.repository.InboxEventRepository;
import az.amin.techstore.wallet.dao.repository.OutboxRepository;
import az.amin.techstore.wallet.dao.repository.WalletRepository;
import az.amin.techstore.wallet.model.BaseEvent;
import az.amin.techstore.wallet.model.PaymentFailedEvent;
import az.amin.techstore.wallet.model.PaymentStartCommand;
import az.amin.techstore.wallet.model.PaymentSuccessEvent;
import az.amin.techstore.wallet.properties.RabbitPublisherProperties;
import az.amin.techstore.wallet.service.WalletService;
import az.amin.techstore.wallet.util.JsonUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final InboxEventRepository inboxEventRepository;
    private final WalletRepository walletRepository;
    private final JsonUtil jsonUtil;
    private final OutboxRepository outboxRepository;
    private final RabbitPublisherProperties rabbitPublisherProperties;

    @Override
    @Transactional
    public void handlePayment(PaymentStartCommand paymentStartCommand) {

        if (!createInboxEvent(
                paymentStartCommand.getEventId(),
                paymentStartCommand.getCorrelationId())
        ) return;

        try {
            decreaseBalance(paymentStartCommand);

            saveSuccessOutboxEvent(paymentStartCommand);

        } catch (EntityNotFoundException | IllegalArgumentException e) {
            saveFailureOutboxEvent(paymentStartCommand, e);
        }

        markInboxEventAsProcessed(paymentStartCommand.getEventId());
    }


    private void decreaseBalance(PaymentStartCommand paymentStartCommand) {
        WalletEntity walletEntity = walletRepository
                .findByUserId(paymentStartCommand.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found for User"));

        walletEntity.decreaseBalance(paymentStartCommand.getAmount());
    }

    private void saveSuccessOutboxEvent(PaymentStartCommand paymentStartCommand) {

        PaymentSuccessEvent paymentSuccessEvent = PaymentSuccessEvent
                .from(paymentStartCommand);

        saveOutbox(
                paymentSuccessEvent,
                rabbitPublisherProperties.getSuccessRoutingKey()
        );
    }

    private void saveFailureOutboxEvent(PaymentStartCommand paymentStartCommand,
                                        RuntimeException e) {

        PaymentFailedEvent paymentFailedEvent = PaymentFailedEvent
                .from(paymentStartCommand);
        paymentFailedEvent.setReason(e.getMessage());

        saveOutbox(
                paymentFailedEvent,
                rabbitPublisherProperties.getFailedRoutingKey()
        );
    }

    private void saveOutbox(BaseEvent event, String routingKey) {
        OutboxEvent outboxEvent = new OutboxEvent(
                event.getEventId(),
                event.getCorrelationId(),
                event.getAggregateId(),
                routingKey,
                jsonUtil.toJson(event)
        );
        outboxRepository.save(outboxEvent);
    }

    private boolean createInboxEvent(UUID eventId, String correlationId) {

        InboxEvent inbox = new InboxEvent(eventId);

        try {
            inboxEventRepository.save(inbox);
            return true;
        } catch (DataIntegrityViolationException ex) {
            log.info(
                    "Duplicate event. eventId={}, correlationId={}",
                    eventId,
                    correlationId
            );
            return false;
        }
    }

    private void markInboxEventAsProcessed(UUID eventId) {
        InboxEvent inboxEvent = inboxEventRepository
                .findByEventId(eventId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Inbox not found for eventId: " + eventId
                ));

        inboxEvent.markProcessed();
    }
}
