package orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import orders.entities.OrderStatus;

import java.time.LocalDateTime;

/**
 * Created by szypows_local on 27.11.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDetails {
    private Long orderId;
    private LocalDateTime sendDate;
    private String message;
    private OrderStatus orderStatus;
}
