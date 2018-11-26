package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDto;
import orders.entities.OrderStatus;
import orders.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orders.services.MagazineService;
import orders.services.OrderService;

import java.time.LocalDate;


/**
 * Created by szypows_local on 18.11.2018.
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MagazineService magazineService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/order/save")
    public LocalDate saveOrder(@RequestBody OrderDto orderDto) {
        Long orderId = orderService.save(orderDto);
        //waiting for magazine implementation
        //LocalDate sendDate = magazineService.postOrderToMagazine(orderDto);
        //mocked answer
        LocalDate sendDate = LocalDate.now();
        try {
            orderService.updateSendDate(orderId, sendDate);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        return sendDate;
    }

    //update order by magazine
    //order can by CANCELED by SELLER from VIEW
    @PostMapping("order/update_status")
    public ResponseEntity updateOrderStatus(@RequestBody OrderStatusDto orderStatusDto) {
        try {
            orderService.updateStatus(orderStatusDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        Long id = orderStatusDto.getOrderId();
        return new ResponseEntity(HttpStatus.valueOf("Can't find order with id: " + id));
    }
}
