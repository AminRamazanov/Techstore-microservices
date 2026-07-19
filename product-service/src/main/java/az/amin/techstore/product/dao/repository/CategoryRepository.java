package az.amin.techstore.product.dao.repository;

import az.amin.techstore.product.dao.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
