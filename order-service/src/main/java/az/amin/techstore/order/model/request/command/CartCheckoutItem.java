package az.amin.techstore.order.model.request.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class CartCheckoutItem {
    private Long productId;
    private String name;
    private BigDecimal quantity;
    private BigDecimal price;
}
