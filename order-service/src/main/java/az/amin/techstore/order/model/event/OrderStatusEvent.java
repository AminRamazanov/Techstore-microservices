package az.amin.techstore.order.model.event;

import lombok.Data;
import org.example.orderms.enums.EventType;

import java.math.BigDecimal;

@Data
public class OrderStatusEvent {
    private Long id;

    private Long userId;

    private BigDecimal price;

    private EventType orderStatus;
}
