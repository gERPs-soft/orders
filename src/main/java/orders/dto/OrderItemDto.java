package orders.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private Integer quantity;
    private BigDecimal productPrice;
    private Long orderId;

}
