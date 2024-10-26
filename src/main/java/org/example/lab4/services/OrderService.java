package org.example.lab4.services;

import org.example.lab4.entities.Order;
import org.example.lab4.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Long id, Order newOrder) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setOrderDate(newOrder.getOrderDate());
        existingOrder.setCustomer(newOrder.getCustomer());
        existingOrder.setProducts(newOrder.getProducts());

        orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

