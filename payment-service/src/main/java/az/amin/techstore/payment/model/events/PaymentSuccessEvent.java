package az.amin.techstore.payment.model.events;

public class PaymentSuccessEvent extends BaseEvent {

    private Long orderId;

    public PaymentSuccessEvent() {}

    public PaymentSuccessEvent(Long orderId) {
        this.orderId = orderId;
        setAggregateId(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }
}