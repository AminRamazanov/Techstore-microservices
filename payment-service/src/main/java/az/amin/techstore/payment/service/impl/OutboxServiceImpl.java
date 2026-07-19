package az.amin.techstore.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentms.dao.OutboxEvent;
import org.example.paymentms.dao.repository.OutboxRepository;
import org.example.paymentms.service.OutboxProcessor;
import org.example.paymentms.service.OutboxService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {

    private final OutboxRepository outboxRepository;
    private final OutboxProcessor outboxProcessor;


    @Override
    public void publishPendingEvents() {
        log.info("Action.log.publishEvents started");

        List<OutboxEvent> events =
                outboxRepository.findBatchForProcessing();

        for (OutboxEvent event : events) {
            outboxProcessor.processSingleEvent(event);
        }

        log.info("Action.log.publishEvents ended");
    }


}
