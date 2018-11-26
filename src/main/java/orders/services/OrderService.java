package orders.services;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDto;
import orders.exceptions.OrderNotFoundException;

import java.time.LocalDate;

/**
 * Created by szypows_local on 18.11.2018.
 */
public interface OrderService {
    public Long save(OrderDto orderDto);
    public void updateStatus(OrderStatusDto orderStatusDto) throws OrderNotFoundException;
    public void updateSendDate(Long id, LocalDate sendDate) throws OrderNotFoundException;
}
