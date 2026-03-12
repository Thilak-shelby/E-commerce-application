# E-Commerce Backend (Spring Boot)

A simplified **E-commerce backend** built with **Spring Boot** demonstrating core backend engineering concepts including:

* REST API design
* Layered backend architecture
* JWT authentication
* Database modeling with JPA
* Strategy design pattern for payment processing
* Order checkout workflow

The system models a small e-commerce platform where users can browse products, add them to a cart, create orders, and pay using different payment strategies.

---

# Features

## Authentication & Security

* User registration
* Secure login using **JWT authentication**
* Password hashing with **BCrypt**
* Protected API endpoints using **Spring Security**

## Product Management

* Create and manage products
* Product pricing and stock handling
* Products organized into categories

## Category System

* Categories group related products
* Products assigned to categories

## Shopping Cart

* Add products to cart
* Update product quantities
* View cart contents

## Checkout & Orders

* Convert cart into an order
* Orders contain multiple order items
* Order status tracking

## Payment System

* Payment processing using the **Strategy Pattern**
* Support for multiple payment methods
* Payment persistence and status tracking

## Backend Architecture

* Clean layered architecture
* RESTful API design
* DTO-based request/response handling
* PostgreSQL database integration

---

# Architecture

The project follows a standard **Spring Boot layered architecture**:

```
Controller → Service → Repository → Database
```

### Controller

Handles HTTP requests and responses.

### Service

Contains business logic such as:

* Cart management
* Order creation
* Checkout process
* Payment processing
* Product management

### Repository

Handles database access using **Spring Data JPA**.

### Entity

Represents database tables and relationships.

### DTO

Request and response objects for API communication.

### Security Layer

Handles authentication and authorization using **JWT and Spring Security**.

---

# Project Structure

```
src/main/java/com/example/e_commerce

config
 └── SecurityConfig

controller
 ├── AuthController
 ├── CartController
 ├── CategoryController
 ├── CheckoutController
 ├── OrderController
 ├── PaymentController
 └── ProductController

dto
 ├── AddToCartRequest
 ├── CardPaymentRequest
 ├── CartItemResponse
 ├── CartResponse
 ├── LoginRequest
 ├── PaymentRequest
 ├── PaymentResult
 ├── PaypalPaymentRequest
 ├── ProductRequest
 ├── ProductResponse
 └── RegistrationRequest

entity
 ├── Cart
 ├── CartItem
 ├── Category
 ├── Order
 ├── OrderItem
 ├── OrderStatus
 ├── Payment
 ├── PaymentStatus
 ├── Product
 ├── Role
 └── User

payment
 ├── PaymentStrategy
 ├── CardPaymentStrategy
 └── PaypalPaymentStrategy

repository
 ├── CartItemRepository
 ├── CartRepository
 ├── CategoryRepository
 ├── OrderItemRepository
 ├── OrderRepository
 ├── PaymentRepository
 ├── ProductRepository
 └── UserRepository

security
 ├── JwtAuthenticationFilter
 └── JwtService

service
 ├── CartService
 ├── CategoryService
 ├── CheckoutService
 ├── OrderService
 ├── PaymentService
 └── ProductService
```

---

# Domain Model

Core entities in the system:

```
Category
 └── Product

Cart
 └── CartItem
      └── Product

Order
 └── OrderItem
      └── Product

User
 ├── Cart
 └── Order

Order
 └── Payment
```

---

# Checkout Flow

The checkout process follows this sequence:

```
Add Product to Cart
        ↓
View Cart
        ↓
Checkout
        ↓
Create Order
        ↓
Process Payment
        ↓
Update Order Status
        ↓
Clear Cart
```

---

# Order Status

```
 PENDING
 PAID
 FAILED
 CANCELLED
```

---

# Payment Design (Strategy Pattern)

Payment processing is implemented using the **Strategy Pattern**.

```
PaymentStrategy
 ├── CardPaymentStrategy
 └── PaypalPaymentStrategy
```

The correct payment strategy is selected dynamically based on the payment type.

This allows new payment methods to be added without modifying existing business logic.

---

# Authentication Flow (JWT)

```
Register User
      ↓
Login
      ↓
Receive JWT Token
      ↓
Send Token in Authorization Header
      ↓
Access Protected Endpoints
```

Example header:

```
Authorization: Bearer <jwt_token>
```

---

# Tech Stack

* Java 21
* Spring Boot
* Spring Security
* JWT (JSON Web Tokens)
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Jackson

---

# Example API Endpoints

## Register User

```
POST /api/auth/register
```

Example request:

```json
{
  "username": "john",
  "email": "john@email.com",
  "password": "123456"
}
```

---

## Login

```
POST /api/auth/login
```

Returns a JWT token.

---

## Get Products

```
GET /api/products
```

Requires authentication.

---

## Add Item to Cart

```
POST /api/cart/add
```

Example request:

```json
{
  "productId": 1,
  "quantity": 2
}
```

---

## Checkout

```
POST /checkout
```

Creates an order from the cart.

---

## Pay Order with Card

```
POST /orders/{orderId}/card
```

Example request:

```json
{
  "cardNumber": "12345678",
  "cvv": "123",
  "expiry": "12/26"
}
```

---

## Pay Order with PayPal

```
POST /orders/{orderId}/paypal
```

Example request:

```json
{
  "email": "test@paypal.com"
}
```

---

# Running the Project

### Clone the repository

```
git clone https://github.com/Thilak-shelby/E-commerce-application.git
```

### Configure PostgreSQL

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

### Run the application

```
mvn spring-boot:run
```

The API will start at:

```
http://localhost:8080
```

---

# Learning Goals

This project was built to practice:

* Spring Boot backend development
* REST API design
* JWT authentication
* Database modeling with JPA
* Layered architecture
* Strategy design pattern
* Backend system design

---

# Future Improvements

Possible extensions for the project:

* Product search and filtering
* Pagination
* Global exception handling
* API documentation with **Swagger / OpenAPI**
* Payment gateway integration (Stripe / PayPal API)
* Order history and tracking

---

# Author

Backend project built as a learning exercise for modern **Java backend development using Spring Boot**.
