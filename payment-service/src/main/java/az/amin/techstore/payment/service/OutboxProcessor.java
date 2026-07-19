package az.amin.techstore.payment.service;

import org.example.paymentms.dao.OutboxEvent;

public interface OutboxProcessor {
    void processSingleEvent(OutboxEvent outboxEvent);
}
