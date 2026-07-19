package az.amin.techstore.payment.service;

public interface OutboxService {
    void publishPendingEvents();
}
