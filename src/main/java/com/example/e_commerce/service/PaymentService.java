package com.example.e_commerce.service;

import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentCallbackRequest;
import com.example.e_commerce.dto.PaymentInitiationResponse;
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
    public PaymentInitiationResponse processPayment(Long orderId, String type, PaymentRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        PaymentStrategy strategy = strategies.get(type.toUpperCase());

        if (strategy == null) {
            throw new RuntimeException("Unsupported payment type");
        }

        PaymentInitiationResponse result = strategy.pay(request);

        String transactionId = "TXN-" + System.currentTimeMillis();

        // Create payment record
        Payment payment = new Payment(
                order,
                order.getTotalAmount(),
                type.toUpperCase(),
                transactionId
        );

        payment.setStatus(PaymentStatus.PENDING);

        paymentRepository.save(payment);

        // Update order state
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);

        return new PaymentInitiationResponse(
                true,
                "Payment initiated successfully. Transaction ID: " + transactionId
        );
    }

    public void handlePaymentCallback(PaymentCallbackRequest request) {

        Payment payment = paymentRepository
                .findByTransactionId(request.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Prevent duplicate callbacks
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            return;
        }

        Order order = payment.getOrder();

        if ("SUCCESS".equalsIgnoreCase(request.getPaymentStatus())) {

            payment.setStatus(PaymentStatus.SUCCESS);

            order.setStatus(OrderStatus.PAID);

        } else {

            payment.setStatus(PaymentStatus.FAILED);

            order.setStatus(OrderStatus.FAILED);
        }

        paymentRepository.save(payment);

        orderRepository.save(order);
    }
}