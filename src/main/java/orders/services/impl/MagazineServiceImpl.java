package orders.services.impl;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDto;
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
    public OrderStatusDto postOrderToMagazine(OrderDto orderDto) {
        String magazineOrderUrl = "http://localhost:8082/magazine/add_order";
        OrderStatusDto orderStatusDto = restTemplate.postForObject(magazineOrderUrl, orderDto, OrderStatusDto.class);
        return orderStatusDto;
    }
    @Override
    public OrderStatusDto postOrderToOrder(OrderDto orderDto) {
        String magazineOrderUrl = "http://localhost:8082/order/save";
        OrderStatusDto orderStatusDto = restTemplate.postForObject(magazineOrderUrl, orderDto, OrderStatusDto.class);
        return orderStatusDto;
    }
}
