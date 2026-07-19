package az.amin.techstore.order.dao.repository;

import org.example.orderms.dao.InboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InboxEventRepository extends JpaRepository<InboxEvent, Long> {
    boolean  existsByEventId(UUID eventId);
}
