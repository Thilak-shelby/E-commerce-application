package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CardPaymentRequest;
import com.example.e_commerce.dto.OrderRequest;
import com.example.e_commerce.dto.PayPalPaymentRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.service.OrderService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @PostMapping("/{orderId}/pay/card")
    public Order payWithCard(
            @PathVariable Long orderId,
            @RequestBody CardPaymentRequest request) {

        return orderService.payOrder(orderId, "CARD", request);
    }

    @PostMapping("/{orderId}/pay/paypal")
    public Order payWithPaypal(
            @PathVariable Long orderId,
            @RequestBody PayPalPaymentRequest request) {

        return orderService.payOrder(orderId, "PAYPAL", request);
    }
}