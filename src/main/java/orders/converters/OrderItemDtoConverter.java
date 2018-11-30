package orders.converters;

import orders.dto.OrderItemDto;
import orders.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by szypows_local on 10.11.2018.
 */
@Component
public class OrderItemDtoConverter implements Function<OrderItem, OrderItemDto> {

    @Override
    public OrderItemDto apply(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setProductPrice(orderItem.getProductPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
       // orderItemDto.setOrderId(orderItem.getOrder().getId());
        return orderItemDto;
    }
}
