package az.amin.techstore.order.dao.repository;

import org.example.orderms.dao.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {

//    @Query(value = """
//                SELECT * FROM outbox_events
//                WHERE status = :status
//                AND retry_count < :retryCount
//                ORDER BY created_at
//                LIMIT 10
//                FOR UPDATE SKIP LOCKED
//            """, nativeQuery = true)
//    List<OutboxEvent> findBatchForProcessing(
//            @Param("status") String status,
//            @Param("retryCount") int retryCount
//    );

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
