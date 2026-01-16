# SEL E-Commerce Application

This project is a small **console-based Java e-commerce application** developed as part of the **Software Engineering Lab (SEL)** course.

The purpose of the project is to demonstrate the correct application of object-oriented **design patterns** in a realistic but simplified e-commerce scenario.

---

## 📌 Overview

The application simulates a basic e-commerce workflow:

- Products are organized in a hierarchical catalogue
- Users can browse categories and products
- Products can be selected by product number and quantity
- Different payment methods can be chosen at runtime

The focus of the project is **software design and structure**, not real payment processing.

---

## 🧩 Design Patterns Used

### Composite Pattern – Product Catalogue

The **Composite pattern** is used to model the product catalogue as a tree structure.

- **Component:** `Product`
- **Composite:** `ProductGroup` (product categories)
- **Leaf:** `SimpleProduct` (sellable products)
- **Client:** `Demo`

Traversal and product lookup are handled entirely inside the composite hierarchy.  
The demo does not access or iterate over internal structures, ensuring uniform treatment of products and categories.

---

### Strategy Pattern – Payment Methods

The **Strategy pattern** is used to support multiple payment methods.

- **Strategy:** `PaymentStrategy`
- **Concrete Strategies:** `CreditCard`, `PaypalPayment`
- **Context:** `Order`
- **Client:** `Demo`

Payment behavior is encapsulated in strategy classes and can be changed at runtime.  
The demo interacts only with the `PaymentStrategy` interface.

---

## 🛠️ Technologies

- Java
- Console-based input/output
- No external libraries

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/sel-ecommerce-design-patterns.git
