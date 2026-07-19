package az.amin.techstore.wallet.model;

import az.amin.techstore.wallet.enums.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

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