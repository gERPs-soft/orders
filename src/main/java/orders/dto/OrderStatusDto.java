package orders.dto;

import lombok.Getter;
import lombok.Setter;
import orders.entities.OrderStatus;

/**
 * Created by szypows_local on 19.11.2018.
 */
@Getter
@Setter
public class OrderStatusDto {
    private Long orderId;
    private OrderStatus orderStatus;

    public OrderStatusDto(Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
