package az.amin.techstore.payment.model.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.paymentms.enums.EventType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEvent implements Event {

    @EqualsAndHashCode.Include
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID eventId;
    private EventType type;
    private Long aggregateId;
    private LocalDateTime createdAt;
    private String correlationId;

    public BaseEvent() {
        this.eventId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }
}