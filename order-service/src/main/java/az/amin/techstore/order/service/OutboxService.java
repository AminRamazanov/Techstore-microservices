package az.amin.techstore.order.service;

import org.example.orderms.dao.OutboxEvent;

public interface OutboxService {
    void processSingleEvent(OutboxEvent event);
}
