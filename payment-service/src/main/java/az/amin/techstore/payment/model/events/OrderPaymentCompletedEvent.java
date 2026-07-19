package az.amin.techstore.payment.model.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPaymentCompletedEvent extends BaseEvent {

    private UUID eventId;

    private String correlationId;

    private Long orderId;

    private Long paymentId;

    private BigDecimal amount;

}
