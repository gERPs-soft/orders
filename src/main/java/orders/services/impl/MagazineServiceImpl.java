package orders.services.impl;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import orders.services.MagazineService;

/**
 * Created by szypows_local on 19.11.2018.
 */
@Service
public class MagazineServiceImpl implements MagazineService {
    private RestTemplate restTemplate;

    @Autowired
    public MagazineServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public OrderStatusDetails postOrderToMagazine(OrderDto orderDto) {
        String magazineOrderUrl = "http://localhost:8080/magazine/orders/add-order";
        OrderStatusDetails orderStatusDetails = restTemplate.postForObject(magazineOrderUrl, orderDto, OrderStatusDetails.class);
        return orderStatusDetails;
    }
}
