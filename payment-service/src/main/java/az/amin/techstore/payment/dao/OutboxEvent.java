package az.amin.techstore.payment.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.paymentms.enums.EventType;
import org.example.paymentms.enums.OutboxStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JdbcTypeCode(SqlTypes.UUID)
    private UUID eventId;

    private String correlationId;

    private Long aggregateId;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private String routingKey;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private int retryCount;

    private LocalDateTime nextRetryAt;

    private String lastError;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;

    public OutboxEvent(){
        this.status = OutboxStatus.PENDING;
    }

    public OutboxEvent(
            UUID eventId,
            String correlationId,
            Long aggregateId,
            String payload,
            String routingKey
    ) {
        this.eventId = eventId;
        this.correlationId = correlationId;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.routingKey = routingKey;
        this.status = OutboxStatus.PENDING;
    }
}