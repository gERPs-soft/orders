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
import orders.services.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import orders.repositories.CustomerRepository;
import orders.repositories.OrderRepository;
import orders.services.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderItemConverter orderItemConverter;
    private OrderDtoConverter orderDtoConverter;
    private MagazineService magazineService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            OrderItemConverter orderItemConverter, OrderDtoConverter orderDtoConverter,
                            MagazineService magazineService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemConverter = orderItemConverter;
        this.orderDtoConverter = orderDtoConverter;
        this.magazineService = magazineService;
    }

    @Override
    public OrderStatusDetails save(OrderDto orderDto) throws CustomernotFoundException, OrderNotFoundException {
        Order order;
        Long customerId = orderDto.getCustomerId();
        Optional<Customer> customerOptional = customerRepository.findByIdAndActiveTrue(customerId);
        if (customerOptional.isPresent()) {
            Order orderToSave = newOrderBulider(orderDto, customerOptional);
            Order savedOrder = orderRepository.save(orderToSave);
            savedOrder.getOrderItems().forEach(orderItem -> orderItem.setOrder(savedOrder));
            order = orderRepository.save(savedOrder);
        } else {
            throw new CustomernotFoundException("Can't find customer with id: " + customerId);
        }
        OrderStatusDetails orderStatusDetails = magazineService.postOrderToMagazine(order);
        this.updateStatus(orderStatusDetails);
        return orderStatusDetails;
    }

    private Order newOrderBulider(OrderDto orderDto, Optional<Customer> customerOptional) {
        Order orderToSave = new Order();
        orderToSave.setCustomer(customerOptional.get());
        orderToSave.setSellerId(orderDto.getSellerId());
        orderToSave.setOrderDate(LocalDateTime.now());
        orderToSave.setOrderStatus(OrderStatus.DRAFT);
        orderToSave.setOrderItems(orderDto.getItems().stream().map(orderItemConverter).collect(Collectors.toList()));
        return orderToSave;
    }

    @Override
    public void updateStatus(OrderStatusDetails orderStatusDetails) throws OrderNotFoundException {
        Long id = orderStatusDetails.getOrderId();
        Optional<Order> orderOptional = orderRepository.findByIdAndOrderStatusNotLike(id, OrderStatus.CANCELED);
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
        return StreamSupport.stream(orderRepository.findAllByOrderStatusNotLike(OrderStatus.CANCELED).spliterator(), false)
                .map(orderDtoConverter)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findByIdAndOrderStatusNotLike(id, OrderStatus.CANCELED);
        if (optionalOrder.isPresent()) {
            return orderDtoConverter.apply(optionalOrder.get());
        } else {
            throw new OrderNotFoundException("Can't find order with ID " + id);
        }
    }

}
