package converters;

import dto.OrderItemDto;
import entities.OrderItem;
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
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
