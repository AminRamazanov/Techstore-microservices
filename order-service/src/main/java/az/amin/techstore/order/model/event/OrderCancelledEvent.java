package az.amin.techstore.order.model.event;

public class OrderCancelledEvent extends BaseEvent {

    private Long orderId;
    private String reason;

    public OrderCancelledEvent() {}

    public OrderCancelledEvent(Long orderId, String reason) {
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