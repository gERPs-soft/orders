package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import orders.exceptions.CustomernotFoundException;
import orders.exceptions.OrderNotFoundException;


import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface OrderService {
    public Order save(OrderDto orderDto) throws CustomernotFoundException;
    public void updateStatus(OrderStatusDetails orderStatusDetails) throws OrderNotFoundException;
    public List<OrderDto> findAll();
    public OrderDto findById(Long id) throws OrderNotFoundException;
}
