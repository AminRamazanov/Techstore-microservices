package az.amin.techstore.order.model.request.command;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CartCheckedOutEvent {

    private UUID eventId;
    private Long userId;
    private Long cardId;
    private List<CartCheckoutItem> items;
    private BigDecimal totalAmount;
    private String correlationId = "saga-" + UUID.randomUUID();

}