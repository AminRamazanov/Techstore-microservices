package az.amin.techstore.order.model.event;

public class OrderCompletedEvent extends BaseEvent {

    private Long orderId;

    public OrderCompletedEvent() {}

    public OrderCompletedEvent(Long orderId) {
        this.orderId = orderId;
        setAggregateId(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }
}