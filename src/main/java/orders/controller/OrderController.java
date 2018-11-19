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

    @Autowired
    public OrderController(OrderService orderService, MagazineService magazineService) {
    }

    @PostMapping("/order/save")
    public OrderStatusDto saveOrder(@ModelAttribute OrderDto orderDto) {
        orderService.save(orderDto);
        OrderStatusDto orderStatusDto = magazineService.postOrderToMagazine(orderDto);
        return orderStatusDto;
    }
    @GetMapping("/test")
    public void testMe(){
        OrderItemDto orderItemDto = new OrderItemDto(1l,1);
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(orderItemDto);
        OrderDto orderDto = new OrderDto(1l,1l,orderItemDtoList);
        magazineService.postOrderToOrder(orderDto);
    }
}
