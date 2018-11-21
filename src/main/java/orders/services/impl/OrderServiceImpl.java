package orders.services.impl;

import orders.converters.OrderItemConverter;
import orders.dto.OrderDto;
import orders.entities.Order;
import orders.entities.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import orders.repositories.CustomerRepository;
import orders.repositories.OrderRepository;
import orders.services.OrderService;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderItemConverter orderItemConverter;

    private RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            OrderItemConverter orderItemConverter, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void save(OrderDto orderDto) {
        Order order = new Order();
        Long customerId = orderDto.getCustomerId();
        order.setCustomer(customerRepository.findById(customerId).get());
        order.setSellerId(orderDto.getSellerId());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.DRAFT);
        order.setOrderItems(orderDto.getItems().stream()
                .map(orderItemConverter).collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
