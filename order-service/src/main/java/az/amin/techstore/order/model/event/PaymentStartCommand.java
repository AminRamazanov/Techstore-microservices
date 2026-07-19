package az.amin.techstore.order.model.event;

import java.math.BigDecimal;

public class PaymentStartCommand extends BaseEvent {

    private Long orderId;
    private BigDecimal amount;

    public PaymentStartCommand() {}

    public PaymentStartCommand(Long orderId, BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
        setAggregateId(orderId);
    }

    public Long getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}