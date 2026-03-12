package com.example.e_commerce.repository;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    void deleteByCart(Cart cart);
}
