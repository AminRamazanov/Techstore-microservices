package az.amin.techstore.payment.model.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPaymentFailedEvent extends BaseEvent {

    private Long orderId;

    private Long paymentId;

    private Long userId;

    private String reason;
}