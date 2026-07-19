package az.amin.techstore.order.model.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.orderms.enums.EventType;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends BaseEvent {

    private Long orderId;
    private BigDecimal amount;

    public OrderCreatedEvent() {
        this.setType(EventType.ORDER_CREATED);
    }

}