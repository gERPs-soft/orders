package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDto;

/**
 * Created by szypows_local on 19.11.2018.
 */
public interface MagazineService {
    public OrderStatusDto postOrderToMagazine(OrderDto orderDto);
}
