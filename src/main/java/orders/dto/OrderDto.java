package orders.dto;

import lombok.*;
import orders.entities.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private Long sellerId;
    private Long customerId;
    private LocalDateTime sendDate;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<OrderItemDto> items;

}
