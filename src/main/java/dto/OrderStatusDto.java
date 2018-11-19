package dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by szypows_local on 19.11.2018.
 */
@Getter
@Setter
public class OrderStatusDto {
    private String deliveryTime;

    public OrderStatusDto(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

}
