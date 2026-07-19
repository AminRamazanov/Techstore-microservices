package az.amin.techstore.wallet.service.impl;

import az.amin.techstore.wallet.dao.OutboxEvent;
import az.amin.techstore.wallet.dao.repository.OutboxRepository;
import az.amin.techstore.wallet.enums.OutboxStatus;
import az.amin.techstore.wallet.messaging.Publisher;
import az.amin.techstore.wallet.service.OutboxProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OutboxProcessorImpl implements OutboxProcessor {

    private final OutboxRepository outboxRepository;
    private final Publisher publisher;

    private static final int MAX_RETRY_COUNT = 3;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void processSingleEvent(OutboxEvent outboxEvent) {

        outboxEvent.setStatus(OutboxStatus.PROCESSING);

        try {
            handleSuccess(outboxEvent);
        } catch (Exception ex){
            handleFailure(outboxEvent, ex);
        }

        outboxRepository.save(outboxEvent);
    }

    private void handleSuccess(OutboxEvent outboxEvent) {
        publisher.publishEvents(outboxEvent);

        outboxEvent.setLastError(null);
        outboxEvent.setNextRetryAt(null);
        outboxEvent.setStatus(OutboxStatus.SENT);
        outboxEvent.setProcessedAt(LocalDateTime.now());
    }

    private void handleFailure(OutboxEvent outboxEvent, Exception ex) {
        outboxEvent.setRetryCount(outboxEvent.getRetryCount() + 1);
        outboxEvent.setLastError(ex.getMessage());

        if (outboxEvent.getRetryCount() > MAX_RETRY_COUNT){
            outboxEvent.setStatus(OutboxStatus.DEAD);
            outboxEvent.setNextRetryAt(null);
            outboxEvent.setProcessedAt(LocalDateTime.now());

        } else {
            int delay = calculateDelay(outboxEvent.getRetryCount());
            outboxEvent.setStatus(OutboxStatus.FAILED);
            outboxEvent.setNextRetryAt(LocalDateTime.now().plusSeconds(delay));
        }
    }

    private int calculateDelay(int retryCount) {
        return switch (retryCount){
            case 1 -> 5;
            case 2 -> 30;
            default -> 120;
        };
    }

}