package org.example.lab4.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lab4.entities.OrderProduct;
import org.example.lab4.services.OrderProductService;
import org.example.lab4.services.OrderService;
import org.example.lab4.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-products")
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService orderProductService;
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping
    public String listOrderProducts(Model model) {
        model.addAttribute("orderProducts", orderProductService.getAllOrderProducts());
        return "order_product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("orderProduct", new OrderProduct());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("products", productService.getAllProducts());
        return "order_product/create";
    }

    @PostMapping
    public String createOrderProduct(@ModelAttribute("orderProduct") OrderProduct orderProduct) {
        orderProductService.saveOrderProduct(orderProduct);
        return "redirect:/order-products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        OrderProduct orderProduct = orderProductService.getOrderProductById(id);
        model.addAttribute("orderProduct", orderProduct);
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("products", productService.getAllProducts());
        return "order_product/edit";
    }

    @PutMapping("/{id}")
    public String updateOrderProduct(@PathVariable("id") Long id, @ModelAttribute("orderProduct") OrderProduct updatedOrderProduct) {
        orderProductService.updateOrderProduct(id, updatedOrderProduct);
        return "redirect:/order-products";
    }

    @GetMapping("/{id}")
    public String showOrderProduct(@PathVariable("id") Long id, Model model) {
        OrderProduct orderProduct = orderProductService.getOrderProductById(id);
        model.addAttribute("orderProduct", orderProduct);
        return "order_product/detail";
    }

    @DeleteMapping("/{id}")
    public String deleteOrderProduct(@PathVariable("id") Long id) {
        orderProductService.deleteOrderProduct(id);
        return "redirect:/order-products";
    }
}

