package az.amin.techstore.payment.model.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.paymentms.enums.EventType;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends BaseEvent {

    private Long orderId;
    private Long userId;
    private BigDecimal amount;

    public OrderCreatedEvent() {
        this.setType(EventType.ORDER_CREATED);
    }

}