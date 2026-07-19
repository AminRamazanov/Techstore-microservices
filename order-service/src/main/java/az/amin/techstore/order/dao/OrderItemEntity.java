package az.amin.techstore.order.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String productName;

    private BigDecimal price;

    private BigDecimal quantity;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}