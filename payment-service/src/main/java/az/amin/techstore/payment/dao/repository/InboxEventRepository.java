package az.amin.techstore.payment.dao.repository;

import org.example.paymentms.dao.InboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InboxEventRepository extends JpaRepository<InboxEvent, Long> {
    boolean  existsByEventId(UUID eventId);
}
