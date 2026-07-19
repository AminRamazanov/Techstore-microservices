package az.amin.techstore.payment.service;

import org.example.paymentms.model.events.OrderCreatedEvent;

public interface PaymentService {
    void processPayment(OrderCreatedEvent event);

    void
}
