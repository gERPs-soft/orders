package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.exceptions.CustomernotFoundException;
import orders.exceptions.OrderNotFoundException;


import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface OrderService {
    public OrderStatusDetails save(OrderDto orderDto) throws CustomernotFoundException, OrderNotFoundException;
    public void updateStatus(OrderStatusDetails orderStatusDetails) throws OrderNotFoundException;
    public List<OrderDto> findAll();
    public OrderDto findById(Long id) throws OrderNotFoundException;
}
