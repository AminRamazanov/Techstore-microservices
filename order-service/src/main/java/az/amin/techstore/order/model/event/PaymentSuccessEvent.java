package az.amin.techstore.order.model.event;

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