package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrderStatusDto saveOrder(@RequestBody OrderDto orderDto) {
        Long orderId = orderService.save(orderDto);
        //waiting for magazine implementation
        orderDto.setOrderId(orderId);
        System.out.println(orderDto.toString());
        // OrderStatusDto orderStatusDto = magazineService.postOrderToMagazine(orderDto);
        //mocked answer
        OrderStatusDto orderStatusDto = new OrderStatusDto(LocalDate.now().toString());
        return orderStatusDto;
    }

    //update order by magazine
    //order can by CANCELED by SELLER form VIEW
    @PostMapping("order/update")
    public void updateOrder(@RequestBody OrderStatusDto orderStatusDto) {
        //orderService.update(orderStatusDto);
    }
}
