package az.amin.techstore.order.dao;

import jakarta.persistence.*;
import lombok.*;
import org.example.orderms.enums.EventType;
import org.example.orderms.enums.OutboxStatus;
import org.example.orderms.model.event.BaseEvent;
import org.example.orderms.model.event.Event;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
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
}