package services.impl;

import converters.OrderItemConverter;
import dto.OrderDto;
import entities.Order;
import entities.OrderStatus;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;
import repositories.OrderRepository;
import services.OrderService;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Service

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderItemConverter orderItemConverter;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            OrderItemConverter orderItemConverter) {
    }

    @Override
    public void save(OrderDto orderDto) {
        Order order = new Order();
        //CRUD customerRepo
        Long customerId = orderDto.getCustomerId();
        order.setCustomer(customerRepository.findById(customerId).get());
        order.setSellerId(orderDto.getSellerId());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.DRAFT);
        order.setOrderItems(orderDto.getItems().stream()
                .map(orderItemConverter).collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
