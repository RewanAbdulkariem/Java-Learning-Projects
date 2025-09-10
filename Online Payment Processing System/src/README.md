# Online Payment Processing System

This project demonstrates an **object-oriented design** for an online payment processing system. It showcases the use of **abstract classes, interfaces, subclasses, and polymorphism** to handle multiple payment types and security checks.

---

## Features

- **Abstract Class: Payment**
  - **Fields:**
    - `amount`
  - **Methods:**
    - `processPayment()` → Abstract method to be implemented by subclasses.
    - `generateReceipt()` → Non-abstract method that prints a generic receipt.

- **Interface: SecureTransaction**
  - **Methods:**
    - `boolean authenticateUser()`
    - `boolean validateTransaction()`
  - **Usage:**  
    Any payment type that requires extra security checks should implement this interface.

- **Subclasses of Payment**
  - **CreditCardPayment**
    - Requires `cardNumber`, `cvv`, and `expiryDate`.
    - Implements `SecureTransaction`.
  - **PayPalPayment**
    - Requires `email` and `password`.
    - Implements `SecureTransaction`.
  - **BankTransferPayment**
    - Requires `accountNumber` and `bankName`.
    - Does **not** implement `SecureTransaction` (assume handled externally).

- **Abstract Class: RefundablePayment**
  - Extends `Payment`.
  - **Abstract Method:** `refund()`
  - `CreditCardPayment` and `PayPalPayment` extend this class (refundable).
  - `BankTransferPayment` extends only `Payment` (not refundable).

- **PaymentProcessor Class**
  - Accepts a `List<Payment>`.
  - For each payment:
    - If it implements `SecureTransaction`:
      - Authenticate and validate before calling `processPayment()`.
    - Otherwise, directly call `processPayment()`.
    - Call `generateReceipt()`.
    - If refundable, call `refund()`.

- **Polymorphism**
  - Store different `Payment` objects (Credit Card, PayPal, Bank Transfer) in a single list or array.
  - Iterate and:
    - Call `processPayment()` and `generateReceipt()`.
  - If `SecureTransaction`, authenticate and validate first.
  - If `RefundablePayment`, call `refund()`.
