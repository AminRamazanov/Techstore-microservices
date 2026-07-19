package az.amin.techstore.payment.dao.repository;

import org.example.paymentms.dao.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {
    @Query(value = """
        SELECT * FROM outbox_events
        WHERE status IN ('PENDING', 'FAILED')
        AND retry_count < 4
        AND (
            next_retry_at IS NULL
            OR next_retry_at <= NOW()
        )
        ORDER BY created_at
        LIMIT 10
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    List<OutboxEvent> findBatchForProcessing();
}
