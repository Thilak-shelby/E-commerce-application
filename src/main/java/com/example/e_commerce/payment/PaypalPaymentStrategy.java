package com.example.e_commerce.payment;

import com.example.e_commerce.dto.PaypalPaymentRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentInitiationResponse;
import org.springframework.stereotype.Component;

@Component("PAYPAL")
public class PaypalPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentInitiationResponse pay(PaymentRequest request) {

        if (!(request instanceof PaypalPaymentRequest paypal)) {
            return new PaymentInitiationResponse(false, "Invalid PayPal request");
        }

        if (paypal.getEmail() == null) {
            return new PaymentInitiationResponse(false, "Email required");
        }

        return new PaymentInitiationResponse(true, "PayPal payment successful");
    }
}