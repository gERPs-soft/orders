package orders.controller;

import orders.dto.OrderDto;
import orders.dto.OrderItemDto;
import orders.dto.OrderStatusDto;
import orders.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import orders.services.MagazineService;
import orders.services.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MagazineService magazineService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/order/save")
    public OrderStatusDto saveOrder(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
        OrderStatusDto orderStatusDto = magazineService.postOrderToMagazine(orderDto);
        return orderStatusDto;
    }
}
