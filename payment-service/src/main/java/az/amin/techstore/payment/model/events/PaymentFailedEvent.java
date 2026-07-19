package az.amin.techstore.payment.model.events;

public class PaymentFailedEvent extends BaseEvent {

    private Long orderId;
    private String reason;

    public PaymentFailedEvent() {}

    public PaymentFailedEvent(Long orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
        setAggregateId(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }
}