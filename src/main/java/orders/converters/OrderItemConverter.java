package orders.converters;

import orders.dto.OrderItemDto;
import orders.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by szypows_local on 10.11.2018.
 */
@Component
public class OrderItemConverter implements Function<OrderItemDto, OrderItem> {
    @Autowired
    private OrderDtoConverter orderDtoConverter;

    @Override
    public OrderItem apply(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProductPrice(orderItemDto.getProductPrice());
     //   orderItem.setOrder(orderDtoConverter.apply(orderItemDto.getOrderDto()));
        return orderItem;
    }
}
