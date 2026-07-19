package az.amin.techstore.payment.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.paymentms.enums.Status;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Setter
@Getter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long userId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String correlationId;

    public PaymentEntity() {
        this.status = Status.CREATED;
    }

    public void markSuccess() {
        status = Status.SUCCESS;
    }

    public void markFailed() {
        status = Status.FAILED;
    }
}
