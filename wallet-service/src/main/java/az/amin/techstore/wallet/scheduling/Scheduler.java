package az.amin.techstore.wallet.scheduling;

import az.amin.techstore.wallet.service.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final OutboxService outboxService;

    @Scheduled(fixedDelay = 5000)
    public void publishEvents() {

        try {

            log.info("Starting Outbox Publisher");

            outboxService.publishPendingEvents();

        } catch (Exception ex) {

            log.error("Unexpected error while publishing outbox events", ex);

        }

    }
}

