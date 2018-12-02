package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.exceptions.OrderNotFoundException;
import orders.services.MagazineService;
import orders.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by szypows_local on 18.11.2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
@RestController
public class OrderController {


    private OrderService orderService;
    private MagazineService magazineService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService, MagazineService magazineService) {
        this.orderService = orderService;
        this.magazineService = magazineService;
    }

    @PostMapping("/save")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto) {
        logger.info("save new order()");
        // RW: the whole logic should br moved to service layer.
        // It is general rule to move complex logic behind the scene.
        Long orderId = orderService.save(orderDto);
        //waiting for magazine implementation
        orderDto.setOrderId(orderId);
        OrderStatusDetails orderStatusDetails = magazineService.postOrderToMagazine(orderDto);
        //mocked answer
        // LocalDateTime sendDate = LocalDateTime.now();
        try {
            orderService.updateStatus(orderStatusDetails);
            return new ResponseEntity(orderStatusDetails.getSendDate(), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        Long id = orderStatusDetails.getOrderId();
        return new ResponseEntity(HttpStatus.valueOf("Can't find order with id: " + id));
    }

    //update order by magazine
    //order can by CANCELED by SELLER from VIEW
    @PostMapping("/update_status")
    public ResponseEntity updateOrderStatus(@RequestBody OrderStatusDetails orderStatusDetails) {
        logger.info("update status of order with id = " + orderStatusDetails.getOrderId());
        try {
            orderService.updateStatus(orderStatusDetails);
            return new ResponseEntity(HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        Long id = orderStatusDetails.getOrderId();
        return new ResponseEntity(HttpStatus.valueOf("Can't find order with id: " + id));
    }

    @RequestMapping("/orders")
    public ResponseEntity findAll() {
        logger.info("get all orders");
        return new ResponseEntity(orderService.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        logger.info("get order with id = " + id);
        try {
            return new ResponseEntity(orderService.findById(id), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.valueOf("Can't find order with id: " + id));
    }
}
