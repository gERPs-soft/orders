package controller;

import dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import services.OrderService;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Controller
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
    }

    @PostMapping("/order/save")
    public String saveOrder(@ModelAttribute OrderDto orderDto) {
        orderService.save(orderDto);
        return "redirect:/message?msg=Save OK!!";
    }
}
