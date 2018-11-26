package orders.services.impl;

import orders.converters.OrderItemConverter;
import orders.dto.OrderDto;
import orders.dto.OrderStatusDto;
import orders.entities.Order;
import orders.entities.OrderStatus;
import orders.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import orders.repositories.CustomerRepository;
import orders.repositories.OrderRepository;
import orders.services.OrderService;

import java.time.LocalDate;
import java.util.Optional;
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

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            OrderItemConverter orderItemConverter, RestTemplateBuilder restTemplateBuilder) {
    }

    @Override
    public Long save(OrderDto orderDto) {
        Order order = new Order();
        Long customerId = orderDto.getCustomerId();
        order.setCustomer(customerRepository.findById(customerId).get());
        order.setSellerId(orderDto.getSellerId());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.DRAFT);
        order.setOrderItems(orderDto.getItems().stream()
                .map(orderItemConverter).collect(Collectors.toList()));
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Override
    public void updateStatus(OrderStatusDto orderStatusDto) throws OrderNotFoundException {
        Long id = orderStatusDto.getOrderId();
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(orderStatusDto.getOrderStatus());
            orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Can't find order with ID " + id);
        }
    }

    @Override
    public void updateSendDate(Long id, LocalDate sendDate) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setSendDate(sendDate);
            orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Can't find order with ID " + id);
        }
    }

}
