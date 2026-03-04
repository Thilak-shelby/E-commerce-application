package com.example.e_commerce.service;

import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;
import com.example.e_commerce.payment.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Service
public class PaymentService {

    private final Map<String, PaymentStrategy> strategies;

    public PaymentService(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public PaymentResult processPayment(String type, PaymentRequest request) {

        PaymentStrategy strategy = strategies.get(type.toUpperCase());

        if (strategy == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unsupported payment type"
            );
        }

        return strategy.pay(request);
    }
}