package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.exceptions.CustomernotFoundException;
import orders.exceptions.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orders.services.OrderService;

import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
@RestController
public class OrderController {


    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto) {
        LOGGER.info("save order()");
        try {
            OrderStatusDetails orderStatusDetails = orderService.save(orderDto);
            return new ResponseEntity(orderStatusDetails, HttpStatus.OK);
        } catch (CustomernotFoundException e) {
            LOGGER.warn("Can't find customer with id: " + orderDto.getCustomerId());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (OrderNotFoundException e) {
            LOGGER.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

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
