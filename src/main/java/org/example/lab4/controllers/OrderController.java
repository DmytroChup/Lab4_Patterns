package org.example.lab4.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab4.entities.Order;
import org.example.lab4.services.CustomerService;
import org.example.lab4.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "order/create";
    }

    @PostMapping
    public String createOrder(@ModelAttribute Order order) {
        order.setOrderDate(LocalDateTime.now());
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "order/edit";
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable("id") Long id, @ModelAttribute("order") Order updatedOrder) {
        orderService.updateOrder(id, updatedOrder);
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order/detail";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}

