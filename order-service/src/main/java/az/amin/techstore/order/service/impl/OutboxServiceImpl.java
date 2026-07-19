package az.amin.techstore.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderms.dao.OutboxEvent;
import org.example.orderms.dao.repository.OutboxRepository;
import org.example.orderms.enums.OutboxStatus;
import org.example.orderms.messaging.Publisher;
import org.example.orderms.properties.RabbitPublisherProperties;
import org.example.orderms.service.OutboxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {
    private final OutboxRepository outboxRepository;
    private final Publisher publisher;

    private static final int MAX_RETRY_COUNT = 3;

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public void processSingleEvent(OutboxEvent event){
        log.info(
                "Action.log.processSingleEvent started for event {}",
                event.getId()
        );
        event.setStatus(OutboxStatus.PROCESSING);
        outboxRepository.save(event);

        try {

            handleSuccess(event);

        } catch (Exception e) {

            handleFailure(event, e);

        }
        outboxRepository.save(event);

        log.info(
                "Action.log.processSingleEvent ended for event {}",
                event.getId()
        );    }

    private void handleSuccess(OutboxEvent event) {
        publisher.publishEvents(event);

        event.setStatus(OutboxStatus.SENT);
        event.setProcessedAt(LocalDateTime.now());
        event.setLastError(null);
        event.setNextRetryAt(null);
    }

    private void handleFailure(OutboxEvent event, Exception e){
        event.setRetryCount(event.getRetryCount() + 1);
        event.setLastError(e.getMessage());

        if (event.getRetryCount() > MAX_RETRY_COUNT) {

            event.setStatus(OutboxStatus.DEAD);
            event.setProcessedAt(LocalDateTime.now());
            event.setNextRetryAt(null);

            log.info("Action.log.processSingleEvent event {} DEAD, error message {}, time {}",
                    event.getId(),
                    e.getMessage(),
                    event.getProcessedAt()
            );

        } else {

            int delay = calculateDelay(event.getRetryCount());

            event.setStatus(OutboxStatus.FAILED);

            event.setNextRetryAt(
                    LocalDateTime.now().plusSeconds(delay)
            );

            log.info("Action.log.processSingleEvent event {} FAILED, error message {}, time {}",
                    event.getId(),
                    e.getMessage(),
                    LocalDateTime.now()
            );
        }
    }

    private int calculateDelay(int retryCount) {
        return switch (retryCount) {
            case 1 -> 5;
            case 2 -> 30;
            default -> 120;
        };
    }
}
