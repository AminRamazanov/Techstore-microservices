package az.amin.techstore.wallet.dao;

import az.amin.techstore.wallet.enums.EventType;
import az.amin.techstore.wallet.enums.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Setter
@Getter
@Builder
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

    public OutboxEvent(UUID eventId, String correlationId,
                       Long aggregateId, String routingKey,
                       String payload) {
        this.eventId = eventId;
        this.correlationId = correlationId;
        this.aggregateId = aggregateId;
        this.routingKey = routingKey;
        this.payload = payload;
    }
}