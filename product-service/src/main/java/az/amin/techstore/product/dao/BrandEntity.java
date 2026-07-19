package az.amin.techstore.product.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(
        name = "brands",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
