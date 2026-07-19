package az.amin.techstore.wallet.dao;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WalletEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    private LocalDateTime lastProcessedAt;

    @Version
    private Long version;

    public void decreaseBalance(BigDecimal amount) {

        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        this.balance = this.balance.subtract(amount);
    }
}
