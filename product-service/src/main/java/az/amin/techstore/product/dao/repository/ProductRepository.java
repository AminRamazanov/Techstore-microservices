package az.amin.techstore.product.dao.repository;

import az.amin.techstore.product.dao.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
