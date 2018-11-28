package orders.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Getter
@Setter
public class OrderItemDto {

    private Long productId;
    private Integer quantity;
    private BigDecimal productPrice;
    private OrderDto orderDto;

    public OrderItemDto() {
    }

    public OrderItemDto(Long productId, Integer quantity, BigDecimal productPrice, OrderDto orderDto) {
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.orderDto = orderDto;
    }
}
