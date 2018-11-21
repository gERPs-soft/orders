package orders.converters;

import orders.dto.OrderItemDto;
import orders.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by szypows_local on 10.11.2018.
 */
@Component
public class OrderItemConverter implements Function<OrderItemDto, OrderItem> {

    @Override
    public OrderItem apply(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProductPrice(orderItemDto.getProductPrice());
        return orderItem;
    }
}
