package orders.entities;

import com.sun.xml.internal.bind.v2.TODO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by szypows_local on 17.11.2018.
 */
@Getter
@Setter
@Entity
@Table(name = "[order_tab]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Long sellerId;

    private LocalDate orderDate;

    private LocalDateTime sendDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }


}
