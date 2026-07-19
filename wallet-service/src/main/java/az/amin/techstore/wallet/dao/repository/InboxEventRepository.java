package az.amin.techstore.wallet.dao.repository;

import az.amin.techstore.wallet.dao.InboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InboxEventRepository extends JpaRepository<InboxEvent, Long> {
    Optional<InboxEvent> findByEventId(UUID eventId);
}
