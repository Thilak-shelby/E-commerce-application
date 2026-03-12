package com.example.e_commerce.service;

import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.OrderStatus;
import com.example.e_commerce.entity.Payment;
import com.example.e_commerce.entity.PaymentStatus;
import com.example.e_commerce.payment.PaymentStrategy;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {

    private final Map<String, PaymentStrategy> strategies;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(Map<String, PaymentStrategy> strategies,
                          OrderRepository orderRepository,
                          PaymentRepository paymentRepository) {

        this.strategies = strategies;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }
    public PaymentResult processPayment(Long orderId, String type, PaymentRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        PaymentStrategy strategy = strategies.get(type.toUpperCase());

        if (strategy == null) {
            throw new RuntimeException("Unsupported payment type");
        }

        PaymentResult result = strategy.pay(request);

        // Create payment record
        Payment payment = new Payment(
                order,
                order.getTotalAmount(),
                type.toUpperCase()
        );

        if (result.isSuccess()) {

            payment.setStatus(PaymentStatus.SUCCESS);
            order.setStatus(OrderStatus.PAID);

        } else {

            payment.setStatus(PaymentStatus.FAILED);
            order.setStatus(OrderStatus.FAILED);
        }

        paymentRepository.save(payment);
        orderRepository.save(order);

        return result;
    }
}