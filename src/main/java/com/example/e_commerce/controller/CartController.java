package com.example.e_commerce.controller;

import com.example.e_commerce.dto.AddToCartRequest;
import com.example.e_commerce.dto.CartResponse;
import com.example.e_commerce.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody AddToCartRequest request,
                                  Authentication authentication){

        String email = authentication.getName();

        return cartService.addToCart(email, request);
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponse removeFromCart(@PathVariable Long productId,
                                       Authentication authentication) {

        String email = authentication.getName();

        return cartService.removeFromCart(email, productId);
    }

    @GetMapping
    public CartResponse displayCart(Authentication authentication){

        String email = authentication.getName();
        return cartService.displayCart(email);
    }
}