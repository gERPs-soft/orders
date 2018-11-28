package orders.services.impl;

import orders.converters.OrderDtoConverter;
import orders.converters.OrderItemConverter;
import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderItemConverter orderItemConverter;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, OrderItemConverter orderItemConverter, OrderDtoConverter orderDtoConverter) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemConverter = orderItemConverter;
    }

    @Override
    public Long save(OrderDto orderDto) {
        Order order = new Order();
        Long customerId = orderDto.getCustomerId();
        order.setCustomer(customerRepository.findById(customerId).get());
        order.setSellerId(orderDto.getSellerId());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.DRAFT);
        order.setOrderItems(orderDto.getItems().stream().map(orderItemConverter).collect(Collectors.toList()));
        //order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order.getId()));
        Order savedOrder = orderRepository.save(order);
        Order finalSavedOrder = savedOrder;
        savedOrder.getOrderItems().forEach(orderItem -> orderItem.setOrder(finalSavedOrder));
        Order savedOrderUpdated = orderRepository.save(savedOrder);
        return savedOrderUpdated.getId();
    }

    @Override
    public void updateStatus(OrderStatusDetails orderStatusDetails) throws OrderNotFoundException {
        Long id = orderStatusDetails.getOrderId();
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(orderStatusDetails.getOrderStatus());
            order.setSendDate(orderStatusDetails.getSendDate());
            orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Can't find order with ID " + id);
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder;
        } else {
            return Optional.empty();
        }
    }

}
