package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CardPaymentRequest;
import com.example.e_commerce.dto.PaymentCallbackRequest;
import com.example.e_commerce.dto.PaypalPaymentRequest;
import com.example.e_commerce.dto.PaymentInitiationResponse;
import com.example.e_commerce.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/orders/{orderId}/card")
    public PaymentInitiationResponse payWithCard(
            @PathVariable Long orderId,
            @RequestBody CardPaymentRequest request) {

        return paymentService.processPayment(orderId, "CARD", request);
    }

    @PostMapping("/orders/{orderId}/paypal")
    public PaymentInitiationResponse payWithPaypal(
            @PathVariable Long orderId,
            @RequestBody PaypalPaymentRequest request) {

        return paymentService.processPayment(orderId, "PAYPAL", request);
    }

    @PostMapping("/callback")
    public ResponseEntity<String> paymentCallback(
            @RequestBody PaymentCallbackRequest request) {

        paymentService.handlePaymentCallback(request);

        return ResponseEntity.ok("Callback processed successfully");
    }
}