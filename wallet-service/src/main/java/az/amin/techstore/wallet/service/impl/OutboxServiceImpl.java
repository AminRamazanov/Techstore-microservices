package az.amin.techstore.wallet.service.impl;

import az.amin.techstore.wallet.dao.OutboxEvent;
import az.amin.techstore.wallet.dao.repository.OutboxRepository;
import az.amin.techstore.wallet.service.OutboxProcessor;
import az.amin.techstore.wallet.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {

    private final OutboxRepository outboxRepository;
    private final OutboxProcessor outboxProcessor;


    @Override
    public void publishPendingEvents() {
        List<OutboxEvent> events =
                outboxRepository.findBatchForProcessing();

        for (OutboxEvent event : events) {
            outboxProcessor.processSingleEvent(event);
        }
    }
}