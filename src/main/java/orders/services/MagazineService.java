package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;


/**
 * Created by szypows_local on 19.11.2018.
 */
public interface MagazineService {
    public OrderStatusDetails postOrderToMagazine(OrderDto orderDto);
}
