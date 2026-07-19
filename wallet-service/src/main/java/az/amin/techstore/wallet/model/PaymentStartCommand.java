package az.amin.techstore.wallet.model;

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

}