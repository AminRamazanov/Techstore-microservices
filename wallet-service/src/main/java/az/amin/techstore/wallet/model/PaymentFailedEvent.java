package az.amin.techstore.wallet.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentFailedEvent extends BaseEvent {

    private Long orderId;
    private String reason;

    public PaymentFailedEvent() {}

    public PaymentFailedEvent(Long orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
        setAggregateId(orderId);
    }

    public static PaymentFailedEvent from(PaymentStartCommand paymentStartCommand) {
        PaymentFailedEvent event = new PaymentFailedEvent();
        event.setAggregateId(paymentStartCommand.getAggregateId());
        event.setCorrelationId(paymentStartCommand.getCorrelationId());
        event.setOrderId(paymentStartCommand.getOrderId());
        return event;
    }
}