package az.amin.techstore.order.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderms.dao.OutboxEvent;
import org.example.orderms.dao.repository.OutboxRepository;
import org.example.orderms.service.OutboxService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
    private final OutboxService outboxService;
    private final OutboxRepository outboxRepository;

    @Scheduled(fixedDelay = 5000)
    public void publishEvents() {
        log.info("Action.log.publishEvents started");

        List<OutboxEvent> events =
                outboxRepository.findBatchForProcessing();

        for (OutboxEvent event : events) {
            outboxService.processSingleEvent(event);
        }

        log.info("Action.log.publishEvents ended");
    }
}
