package com.example.e_commerce.controller;

import com.example.e_commerce.entity.Order;
import com.example.e_commerce.service.CheckoutService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public Order checkout(Authentication authentication) {

        String email = authentication.getName();

        return checkoutService.checkout(email);
    }
}