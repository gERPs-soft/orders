package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import orders.entities.OrderStatus;


/**
 * Created by szypows_local on 19.11.2018.
 */
public interface MagazineService {
    public OrderStatusDetails postOrderToMagazine(Order order);

    //void updateStatusInMagazine(Long orderId, OrderStatus orderStatus);
    OrderStatusDetails updateStatusInMagazine(OrderStatusDetails orderStatusDetails);
}
