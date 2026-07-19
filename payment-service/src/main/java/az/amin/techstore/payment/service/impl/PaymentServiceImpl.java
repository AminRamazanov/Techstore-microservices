package az.amin.techstore.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentms.dao.InboxEvent;
import org.example.paymentms.dao.OutboxEvent;
import org.example.paymentms.dao.PaymentEntity;
import org.example.paymentms.dao.repository.InboxEventRepository;
import org.example.paymentms.dao.repository.OutboxRepository;
import org.example.paymentms.dao.repository.PaymentRepository;
import org.example.paymentms.mapper.PaymentMapper;
import org.example.paymentms.model.events.OrderCreatedEvent;
import org.example.paymentms.model.events.PaymentStartCommand;
import org.example.paymentms.properties.RabbitPublisherProperties;
import org.example.paymentms.service.PaymentService;
import org.example.paymentms.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final InboxEventRepository inboxEventRepository;
    private final JsonUtil util;
    private final RabbitPublisherProperties rabbitPublisherProperties;
    private final OutboxRepository outboxRepository;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public void processPayment(OrderCreatedEvent orderCreatedEvent) {

        InboxEvent inboxEvent = new InboxEvent();

        if (inboxEventRepository.existsByEventId(orderCreatedEvent.getEventId())){
            log.info("Duplicate command, event id {} skipping ", orderCreatedEvent.getEventId());
            return;
        } else {
            inboxEvent.setProcessed(false);
            inboxEvent.setEventId(orderCreatedEvent.getEventId());
            inboxEventRepository.save(inboxEvent);
        }


        PaymentEntity paymentEntity = paymentMapper.fromOrderCreated(orderCreatedEvent);
        paymentRepository.save(paymentEntity);

        PaymentStartCommand paymentStartCommand = PaymentStartCommand.fromOrderCreated(orderCreatedEvent);

        OutboxEvent outboxEvent = new OutboxEvent(
                paymentStartCommand.getEventId(),
                paymentStartCommand.getCorrelationId(),
                paymentStartCommand.getAggregateId(),
                util.toJson(paymentStartCommand),
                rabbitPublisherProperties.getRoutingKey()
        );

        outboxRepository.save(outboxEvent);

        inboxEvent.setProcessed(true);
        inboxEvent.setProcessedAt(LocalDateTime.now());
    }



}
