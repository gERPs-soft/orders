package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.entities.Order;
import orders.exceptions.CustomernotFoundException;
import orders.exceptions.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orders.services.MagazineService;
import orders.services.OrderService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
@RestController
public class OrderController {


    private OrderService orderService;
    private MagazineService magazineService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService, MagazineService magazineService) {
        this.orderService = orderService;
        this.magazineService = magazineService;
    }

    @PostMapping("/save")
    public ResponseEntity<LocalDateTime> saveOrder(@RequestBody OrderDto orderDto) {
        LOGGER.info("save order()");
        Order order;
        try {
            order = orderService.save(orderDto);
        } catch (CustomernotFoundException e) {
            LOGGER.warn("Can't find customer with id: " + orderDto.getCustomerId());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        OrderStatusDetails orderStatusDetails = magazineService.postOrderToMagazine(order);
        try {
            orderService.updateStatus(orderStatusDetails);
            return new ResponseEntity(orderStatusDetails.getSendDate(), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            LOGGER.warn("Can't find order with id: " + orderStatusDetails.getOrderId());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //update order by magazine
    //order can by CANCELED by SELLER from VIEW
    @PostMapping("/update_status")
    public ResponseEntity updateOrderStatus(@RequestBody OrderStatusDetails orderStatusDetails) {
        LOGGER.info("update status of order with id = " + orderStatusDetails.getOrderId());
        try {
            orderService.updateStatus(orderStatusDetails);
            return new ResponseEntity(HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            LOGGER.warn("Can't find order with id: " + orderStatusDetails.getOrderId());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/orders")
    public ResponseEntity<List<OrderDto>> findAll() {
        LOGGER.info("get all orders");
        return new ResponseEntity(orderService.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        LOGGER.info("get order with id = " + id);
        try {
            return new ResponseEntity(orderService.findById(id), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            LOGGER.warn("Can't find order with id: " + id);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
