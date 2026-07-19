package az.amin.techstore.wallet.dao;

import az.amin.techstore.wallet.enums.EventType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inbox_events")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class InboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(nullable = false, unique = true)
    private UUID eventId;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean processed;

    private LocalDateTime processedAt;

    public void markProcessed() {
        this.processed = true;
        this.processedAt = LocalDateTime.now();
    }

    public InboxEvent(UUID eventId) {
        this.eventId = eventId;
        this.processed = false;
    }
}
