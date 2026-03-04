package com.example.e_commerce.payment;

import com.example.e_commerce.dto.CardPaymentRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;
import org.springframework.stereotype.Component;

@Component("CARD")
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentResult pay(PaymentRequest request) {

        if (!(request instanceof CardPaymentRequest card)) {
            return new PaymentResult(false, "Invalid card request");
        }

        if (card.getCardNumber() == null || card.getCvv() == null) {
            return new PaymentResult(false, "Missing card details");
        }

        return new PaymentResult(true, "Card payment successful");
    }
}