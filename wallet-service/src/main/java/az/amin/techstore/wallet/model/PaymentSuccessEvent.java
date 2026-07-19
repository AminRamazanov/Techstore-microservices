package az.amin.techstore.wallet.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentSuccessEvent extends BaseEvent {

    private Long orderId;

    public PaymentSuccessEvent() {}

    public PaymentSuccessEvent(Long orderId) {
        this.orderId = orderId;
        setAggregateId(orderId);
    }

    public static PaymentSuccessEvent from(PaymentStartCommand command) {
        PaymentSuccessEvent event = new PaymentSuccessEvent();

        event.setAggregateId(command.getAggregateId());
        event.setCorrelationId(command.getCorrelationId());
        event.setOrderId(command.getOrderId());

        return event;
    }
}