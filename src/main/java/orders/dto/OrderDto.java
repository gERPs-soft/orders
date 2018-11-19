package orders.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Getter
@Setter
public class OrderDto {

    private Long sellerId;
    private Long customerId;
    private List<OrderItemDto> items;

    public OrderDto() {
    }

    public OrderDto(Long sellerId, Long customerId, List<OrderItemDto> items) {
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.items = items;
    }
}
