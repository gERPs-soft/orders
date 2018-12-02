package orders.services.impl;

import orders.converters.OrderDtoConverter;
import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
                orderStatusDetails = restTemplate.postForObject(magazineOrderUrl, orderDto, OrderStatusDetails.class);
        return orderStatusDetails;
    }
}
