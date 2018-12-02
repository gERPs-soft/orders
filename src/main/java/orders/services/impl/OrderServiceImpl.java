package orders.services.impl;

import orders.converters.OrderDtoConverter;
import orders.converters.OrderItemConverter;
import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Customer;
import orders.entities.Order;
import orders.entities.OrderStatus;
import orders.exceptions.CustomernotFoundException;
import orders.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orders.repositories.CustomerRepository;
import orders.repositories.OrderRepository;
import orders.services.OrderService;

import java.time.LocalDateTime;
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
    private OrderDtoConverter orderDtoConverter;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, OrderItemConverter orderItemConverter, OrderDtoConverter orderDtoConverter) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemConverter = orderItemConverter;
        this.orderDtoConverter = orderDtoConverter;
    }

    @Override
    public Order save(OrderDto orderDto) throws CustomernotFoundException {
        Order order = new Order();
        Long customerId = orderDto.getCustomerId();
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            order.setCustomer(customerOptional.get());
            order.setSellerId(orderDto.getSellerId());
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.DRAFT);
            order.setOrderItems(orderDto.getItems().stream().map(orderItemConverter).collect(Collectors.toList()));
            Order savedOrder = orderRepository.save(order);
            savedOrder.getOrderItems().forEach(orderItem -> orderItem.setOrder(savedOrder));
            Order savedOrderUpdated = orderRepository.save(savedOrder);
            return savedOrderUpdated;
        } else {
            throw new CustomernotFoundException("Can't find customer with id: " + customerId);
        }
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
    public List<OrderDto> findAll() {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList.stream().map(orderDtoConverter).collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return orderDtoConverter.apply(optionalOrder.get());
        } else {
            throw new OrderNotFoundException("Can't find order with ID " + id);
        }
    }

}
