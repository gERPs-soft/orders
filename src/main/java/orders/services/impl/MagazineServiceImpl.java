package orders.services.impl;

import orders.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import orders.services.MagazineService;

import java.time.LocalDate;

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
    public LocalDate postOrderToMagazine(OrderDto orderDto) {
        String magazineOrderUrl = "http://localhost:8082/magazine/orders/add-order";
        LocalDate sendDate = restTemplate.postForObject(magazineOrderUrl, orderDto, LocalDate.class);
        return sendDate;
    }
}
