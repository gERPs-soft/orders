package orders.services;

import orders.dto.OrderDto;

import java.time.LocalDate;

/**
 * Created by szypows_local on 19.11.2018.
 */
public interface MagazineService {
    public LocalDate postOrderToMagazine(OrderDto orderDto);
}
