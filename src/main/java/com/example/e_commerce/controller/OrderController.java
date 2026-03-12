package com.example.e_commerce.controller;

import com.example.e_commerce.entity.Order;
import com.example.e_commerce.service.OrderService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get order by id
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    // Get orders of logged-in user
    @GetMapping("/my-orders")
    public List<Order> getUserOrders(Authentication authentication) {

        String email = authentication.getName();

        return orderService.getUserOrders(email);
    }

    // Cancel order
    @PutMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

}