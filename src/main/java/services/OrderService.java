package services;

import dto.OrderDto;
import dto.OrderStatusDto;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface OrderService {
    public void save(OrderDto orderDto);
}
