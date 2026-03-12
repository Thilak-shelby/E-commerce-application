package com.example.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;

public class CardPaymentRequest extends PaymentRequest {

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cvv;

    @NotBlank
    private String expiry;

    public String getCardNumber() {

        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {

        this.cardNumber = cardNumber;
    }

    public String getCvv() {

        return cvv;
    }

    public void setCvv(String cvv) {

        this.cvv = cvv;
    }

    public String getExpiry() {

        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}