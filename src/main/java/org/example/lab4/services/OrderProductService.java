package org.example.lab4.services;

import lombok.RequiredArgsConstructor;
import org.example.lab4.entities.OrderProduct;
import org.example.lab4.repositories.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    public OrderProduct getOrderProductById(Long id) {
        return orderProductRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void saveOrderProduct(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    public void updateOrderProduct(Long id, OrderProduct updatedOrderProduct) {
        OrderProduct existingOrderProduct = orderProductRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrderProduct.setOrder(updatedOrderProduct.getOrder());
        existingOrderProduct.setProduct(updatedOrderProduct.getProduct());
        existingOrderProduct.setQuantity(updatedOrderProduct.getQuantity());

        orderProductRepository.save(existingOrderProduct);
    }

    public void deleteOrderProduct(Long id) {
        orderProductRepository.deleteById(id);
    }
}


