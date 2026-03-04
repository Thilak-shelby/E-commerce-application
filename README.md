# E-Commerce Backend (Spring Boot)

A simplified e-commerce backend built with **Spring Boot** that demonstrates core backend engineering concepts such as REST API design, layered architecture, database relationships, and design patterns.

This project models a small e-commerce system where products are organized into categories, customers can create orders, and orders can be paid using different payment strategies.

---

# Features

- Product management
- Category hierarchy with subcategories
- Order creation with multiple products
- Order items with quantities
- Payment processing using the **Strategy Pattern**
- Order status updates after payment
- RESTful API design
- PostgreSQL database integration
- Layered Spring Boot architecture

---

# Architecture

The project follows a standard **Spring Boot layered architecture**:

```
Controller → Service → Repository → Database
```

- **Controller**  
  Handles HTTP requests and responses.

- **Service**  
  Contains business logic such as order creation and payment processing.

- **Repository**  
  Uses Spring Data JPA to interact with the database.

- **Entity**  
  Represents the domain model stored in the database.

- **DTO**  
  Used for request objects and API communication.

---

# Domain Model

The core entities in the system are:

```
Category
 └── Product

Order
 └── OrderItem
      └── Product
```

Orders contain multiple order items, and each order item references a product and its quantity.

---

# Payment Design (Strategy Pattern)

Payment processing is implemented using the **Strategy Pattern**.

This allows different payment types to be added without modifying existing business logic.

```
PaymentStrategy
 ├── CardPaymentStrategy
 └── PayPalPaymentStrategy
```

The system dynamically selects the correct payment strategy depending on the payment type.

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

Order statuses include:

```
CREATED
PAID
FAILED
```

---

# Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Jackson (JSON serialization)

---

# Example API Endpoints

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

### 1. Clone the repository

```
git clone https://github.com/your-username/ecommerce-backend.git
```

### 2. Configure PostgreSQL database in `application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

### 3. Run the application

```
mvn spring-boot:run
```

The API will start on:

```
http://localhost:8080
```

---

# Learning Goals

This project was built to practice:

- Spring Boot backend development
- REST API design
- Database modeling with JPA
- Layered architecture
- Design patterns in backend systems

---

# Future Improvements

Possible extensions for the project:

- Authentication and user accounts
- Shopping cart functionality
- Pagination for products
- Global exception handling
- DTO response mapping
- Payment transaction persistence

---

# Author

Backend project built as a learning exercise to practice modern Java backend development using Spring Boot.
