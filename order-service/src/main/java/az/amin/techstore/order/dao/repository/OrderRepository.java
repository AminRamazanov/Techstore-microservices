package az.amin.techstore.order.dao.repository;

import org.example.orderms.dao.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
