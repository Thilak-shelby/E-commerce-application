package com.example.e_commerce.dto;

public abstract class PaymentRequest {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}