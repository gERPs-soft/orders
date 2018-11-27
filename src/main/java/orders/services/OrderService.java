package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.exceptions.OrderNotFoundException;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface OrderService {
    public Long save(OrderDto orderDto);
    public void updateStatus(OrderStatusDetails orderStatusDetails) throws OrderNotFoundException;
}
