package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by szypows_local on 17.11.2018.
 */
@Entity
@Table(name = "[order]")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private LocalDateTime order_date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
