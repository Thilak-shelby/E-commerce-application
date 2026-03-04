package com.example.e_commerce.payment;

import com.example.e_commerce.dto.PayPalPaymentRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;
import org.springframework.stereotype.Component;

@Component("PAYPAL")
public class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentResult pay(PaymentRequest request) {

        if (!(request instanceof PayPalPaymentRequest paypal)) {
            return new PaymentResult(false, "Invalid PayPal request");
        }

        if (paypal.getEmail() == null) {
            return new PaymentResult(false, "Email required");
        }

        return new PaymentResult(true, "PayPal payment successful");
    }
}