package com.example.e_commerce.service;

import com.example.e_commerce.dto.OrderRequest;
import com.example.e_commerce.dto.OrderItemRequest;
import com.example.e_commerce.dto.PaymentRequest;
import com.example.e_commerce.dto.PaymentResult;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.OrderItem;
import com.example.e_commerce.entity.OrderStatus;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.repository.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.paymentService = paymentService;

    }

    public Order createOrder(OrderRequest request) {

        Order order = new Order();

        double total = 0;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);

            order.getItems().add(item);

            total += product.getPrice() * itemRequest.getQuantity();
        }

        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    public Order payOrder(Long orderId,
                          String paymentType,
                          PaymentRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        PaymentResult result = paymentService.processPayment(paymentType, request);

        if (result.isSuccess()) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.FAILED);
        }

        return orderRepository.save(order);
    }
}