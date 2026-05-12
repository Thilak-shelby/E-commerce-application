package com.example.e_commerce.payment;

import com.example.e_commerce.dto.CardPaymentRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentInitiationResponse;
import org.springframework.stereotype.Component;

@Component("CARD")
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentInitiationResponse pay(PaymentRequest request) {

        if (!(request instanceof CardPaymentRequest card)) {
            return new PaymentInitiationResponse(false, "Invalid card request");
        }

        if (card.getCardNumber() == null || card.getCvv() == null) {
            return new PaymentInitiationResponse(false, "Missing card details");
        }

        return new PaymentInitiationResponse(true, "Card payment successful");
    }
}