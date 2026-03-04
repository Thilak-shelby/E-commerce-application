package com.example.e_commerce.payment;

import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;

public interface PaymentStrategy {

    PaymentResult pay(PaymentRequest request);
}