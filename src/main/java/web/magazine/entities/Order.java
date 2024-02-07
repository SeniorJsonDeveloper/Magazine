package web.magazine.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal price;
    @CreationTimestamp
    Instant createdAt = Instant.now();
    @UpdateTimestamp
    Instant updatedAt = Instant.now();
    @Column(name = "product")
    String product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Client client;
    String category;
}
