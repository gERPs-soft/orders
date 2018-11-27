package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import orders.exceptions.OrderNotFoundException;


import java.util.List;
import java.util.Optional;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface OrderService {
    public Long save(OrderDto orderDto);
    public void updateStatus(OrderStatusDetails orderStatusDetails) throws OrderNotFoundException;
    public List<Order> findAll();
    public Optional<Order> findById(Long id);
}
