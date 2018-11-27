package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDetails;
import orders.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orders.services.MagazineService;
import orders.services.OrderService;

import java.time.LocalDateTime;


/**
 * Created by szypows_local on 18.11.2018.
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MagazineService magazineService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto) {
        Long orderId = orderService.save(orderDto);
        //waiting for magazine implementation
        OrderStatusDetails orderStatusDetails = magazineService.postOrderToMagazine(orderDto);
        //mocked answer
        LocalDateTime sendDate = LocalDateTime.now();
        try {
            orderService.updateStatus(orderStatusDetails);
            return new ResponseEntity(sendDate, HttpStatus.OK);
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
        try {
            orderService.updateStatus(orderStatusDetails);
            return new ResponseEntity(HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        Long id = orderStatusDetails.getOrderId();
        return new ResponseEntity(HttpStatus.valueOf("Can't find order with id: " + id));
    }
}
