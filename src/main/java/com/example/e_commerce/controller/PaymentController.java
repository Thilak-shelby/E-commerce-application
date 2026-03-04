package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CardPaymentRequest;
import com.example.e_commerce.dto.PayPalPaymentRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;
import com.example.e_commerce.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/card")
    public PaymentResult payWithCard(@Valid @RequestBody CardPaymentRequest request) {
        return paymentService.processPayment("CARD", request);
    }

    @PostMapping("/paypal")
    public PaymentResult payWithPaypal(@Valid @RequestBody PayPalPaymentRequest request) {
        return paymentService.processPayment("PAYPAL", request);
    }
}