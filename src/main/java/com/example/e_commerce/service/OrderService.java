package com.example.e_commerce.service;

import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.OrderStatus;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    // Get single order
    public Order getOrder(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Get orders of logged in user
    public List<Order> getUserOrders(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUser(user);
    }

    // Cancel order
    public Order cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.PAID) {
            throw new RuntimeException("Cannot cancel a paid order");
        }

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }

}