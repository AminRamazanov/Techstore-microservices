package az.amin.techstore.order.model;

import lombok.Data;
import org.example.orderms.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;

    private Long userId;

    private BigDecimal price;

    private Status status;

    private LocalDateTime createdAt;

    private Long idempotencyKey;
}
