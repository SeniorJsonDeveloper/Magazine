package web.magazine.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<>();
    @Column(name = "phoneNumber",unique = true)
    String phoneNumber;

    public void addOrder(Order order){
        if (order!=null) orders.add(order);
    }
    public void removeOrder(Long orderId){
        orders = orders
                .stream()
                .filter(o->!o.getId().equals(orderId))
                .collect(Collectors.toList());
    }

}
