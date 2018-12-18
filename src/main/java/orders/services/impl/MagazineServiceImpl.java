package orders.services.impl;

import orders.converters.OrderDtoConverter;
import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import orders.entities.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import orders.services.MagazineService;

/**
 * Created by szypows_local on 19.11.2018.
 */

@Service
public class MagazineServiceImpl implements MagazineService {
    private RestTemplate restTemplate = new RestTemplate();
    private OrderDtoConverter orderDtoConverter;
    @Value("${magazine.server.address}")
    String magazineOrderUrl;

    @Autowired
    public MagazineServiceImpl(OrderDtoConverter orderDtoConverter) {
        this.orderDtoConverter = orderDtoConverter;
    }

    @Override
    public OrderStatusDetails postOrderToMagazine(Order order) {
        OrderDto orderDto = orderDtoConverter.apply(order);
        OrderStatusDetails
                orderStatusDetails = restTemplate.postForObject(magazineOrderUrl+"add-order", orderDto, OrderStatusDetails.class);
        return orderStatusDetails;
    }

    @Override
    public OrderStatusDetails updateStatusInMagazine(OrderStatusDetails orderStatusDetails) {
        RestTemplate restTemp = new RestTemplate();
        OrderStatusDetails orderStatus = restTemp.postForObject(magazineOrderUrl+"change-status", orderStatusDetails, OrderStatusDetails.class);

        return orderStatus;
    }
}
