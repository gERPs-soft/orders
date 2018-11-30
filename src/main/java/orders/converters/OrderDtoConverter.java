package orders.converters;

import orders.dto.OrderDto;
import orders.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by szypows_local on 27.11.2018.
 */
@Component
public class OrderDtoConverter implements Function<Order, OrderDto> {
    @Autowired
    private OrderItemDtoConverter orderItemDtoConverter;

    @Override
    public OrderDto apply(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setSendDate(order.getSendDate());
        orderDto.setOrderId(order.getId());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setSellerId(order.getSellerId());
        orderDto.setSendDate(order.getSendDate());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setItems(order.getOrderItems().stream().map(orderItemDtoConverter).collect(Collectors.toList()));
        return orderDto;
    }
}
