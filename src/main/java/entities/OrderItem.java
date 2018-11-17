package entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by szypows_local on 17.11.2018.
 */
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    private String productName;

    private BigDecimal productPrice;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
