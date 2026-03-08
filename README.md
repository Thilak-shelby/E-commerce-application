# E-Commerce Backend (Spring Boot)

A simplified **E-commerce backend** built with **Spring Boot** demonstrating core backend engineering concepts such as:

- REST API design
- Layered architecture
- JWT authentication
- Database modeling with JPA
- Design patterns in backend systems

The system models a small e-commerce platform where products belong to categories, customers can create orders, and orders can be paid using different payment strategies.

---

# Features

### Authentication & Security
- User registration
- Secure login using **JWT authentication**
- Password hashing with **BCrypt**
- Protected API endpoints using **Spring Security**

### Product Management
- Create and manage products
- Product pricing and stock handling
- Products organized into categories

### Category System
- Category hierarchy
- Products assigned to categories

### Orders
- Create orders containing multiple products
- Order items with quantities
- Order status updates

### Payment System
- Payment processing implemented using the **Strategy Pattern**
- Support for multiple payment methods

### Backend Architecture
- Clean layered architecture
- RESTful API design
- DTO-based request handling
- PostgreSQL database integration

---

# Architecture

The project follows a standard **Spring Boot layered architecture**:

```
Controller → Service → Repository → Database
```

### Controller
Handles incoming HTTP requests and responses.

### Service
Contains business logic such as:
- Order creation
- Payment processing
- Product management

### Repository
Handles database operations using **Spring Data JPA**.

### Entity
Represents database tables.

### DTO
Used for request and response objects.

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
 ├── ProductController
 ├── OrderController
 ├── PaymentController
 ├── CategoryController
 └── TestController

dto
 ├── CardPaymentRequest
 ├── LoginRequest
 ├── OrderItemRequest
 ├── OrderRequest
 ├── PaymentRequest
 ├── PaymentResult
 ├── PayPalPaymentRequest
 ├── ProductRequest
 ├── ProductResponse
 └── RegistrationRequest

entity
 ├── Category
 ├── Order
 ├── OrderItem
 ├── OrderStatus
 ├── Product
 ├── Role
 └── User

payment
 ├── PaymentStrategy
 ├── CardPaymentStrategy
 └── PayPalPaymentStrategy

repository
 ├── CategoryRepository
 ├── OrderItemRepository
 ├── OrderRepository
 ├── ProductRepository
 └── UserRepository

service
 ├── CategoryService
 ├── OrderService
 ├── PaymentService
 └── ProductService

security
 ├── JwtService
 └── JwtAuthenticationFilter
```

# Domain Model

Core entities in the system:

```
Category
 └── Product

Order
 └── OrderItem
      └── Product

User
 └── Order
```

### Relationships

- A **Category** contains multiple **Products**
- An **Order** contains multiple **OrderItems**
- Each **OrderItem** references a **Product**
- A **User** can create multiple **Orders**

---

# Payment Design (Strategy Pattern)

Payment processing is implemented using the **Strategy Pattern**.

```
PaymentStrategy
 ├── CardPaymentStrategy
 └── PayPalPaymentStrategy
```

The system dynamically selects the correct payment strategy depending on the payment type.

This allows new payment methods to be added without modifying existing business logic.

---

# Order Flow

Typical order workflow:

```
Create Products
      ↓
Create Order
      ↓
Add Order Items
      ↓
Process Payment
      ↓
Update Order Status
```

### Order Status

```
CREATED
PAID
FAILED
```

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

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Jackson

---

# Example API Endpoints

### Register User

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

### Login

```
POST /api/auth/login
```

Returns a JWT token.

---

### Get Products

```
GET /api/products
```

Requires authentication.

---

### Create Order

```
POST /orders
```

Example request:

```json
{
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ]
}
```

---

### Pay Order with Card

```
POST /orders/{orderId}/pay/card
```

Example request:

```json
{
  "amount": 2480,
  "cardNumber": "12345678",
  "cvv": "123",
  "expiry": "12/26"
}
```

---

### Pay Order with PayPal

```
POST /orders/{orderId}/pay/paypal
```

Example request:

```json
{
  "amount": 2480,
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

- Spring Boot backend development
- REST API design
- JWT authentication
- Database modeling with JPA
- Layered architecture
- Design patterns in backend systems

---

# Future Improvements

Possible extensions for the project:

- Shopping cart functionality
- Product search and filtering
- Pagination
- Global exception handling
- API documentation with **Swagger / OpenAPI**
- Payment transaction persistence
- Role-based authorization (ADMIN / CUSTOMER)

---

# Author

Backend project built as a learning exercise for modern **Java backend development using Spring Boot**.
