package az.amin.techstore.payment.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStartCommand extends BaseEvent {

    private Long orderId;
    private Long userId;
    private BigDecimal amount;


    public static PaymentStartCommand fromOrderCreated(OrderCreatedEvent orderCreatedEvent){

        PaymentStartCommand paymentStartCommand = new PaymentStartCommand();
        paymentStartCommand.setAmount(orderCreatedEvent.getAmount());
        paymentStartCommand.setUserId(orderCreatedEvent.getUserId());
        paymentStartCommand.setOrderId(orderCreatedEvent.getOrderId());
        paymentStartCommand.setAggregateId(orderCreatedEvent.getAggregateId());
        paymentStartCommand.setCorrelationId(orderCreatedEvent.getCorrelationId());

        return paymentStartCommand;
    }

}