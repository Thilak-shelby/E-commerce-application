package com.example.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;

public class PaypalPaymentRequest extends PaymentRequest {

    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}