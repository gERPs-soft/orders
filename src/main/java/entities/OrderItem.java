package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by szypows_local on 17.11.2018.
 */
@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long Id;

    private Long productId;

    private BigDecimal productPrice;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
