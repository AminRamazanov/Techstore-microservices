package az.amin.techstore.payment.dao.repository;

import org.example.paymentms.dao.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
