package controller;

import dto.OrderDto;
import dto.OrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.MagazineService;
import services.OrderService;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Controller
public class OrderController {
    private OrderService orderService;
    private MagazineService magazineService;

    @Autowired
    public OrderController(OrderService orderService, MagazineService magazineService) {
    }

    @PostMapping("/order/save")
    public OrderStatusDto saveOrder(@ModelAttribute OrderDto orderDto) {
        orderService.save(orderDto);
        OrderStatusDto orderStatusDto = magazineService.postOrderToMagazine(orderDto);
        return orderStatusDto;
    }
}
