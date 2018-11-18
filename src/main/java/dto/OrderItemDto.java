package dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Getter
@Setter
public class OrderItemDto {

    private Long productId;
    private Integer quantity;

}
