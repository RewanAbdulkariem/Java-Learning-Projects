package app;

import payment.Payment;
import payment.types.BankTransferPayment;
import payment.types.CreditCardPayment;
import payment.types.PayPalPayment;
import processor.PaymentProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args){
        // Create a list to store different types of payments
        ArrayList<Payment> payments = new ArrayList<>();

        // Add a Credit Card payment
        // amount to pay: 5000
        // card number, CVV, expiry date
        // account balance: 2595 (consider increasing for successful payment)
        payments.add(new CreditCardPayment(
                BigDecimal.valueOf(5000),
                "123456789145236",
                "123",
                "12/27",
                BigDecimal.valueOf(2595)
        ));

        // Add a PayPal payment
        // Note: credentials must match those in PayPalPayment class for authentication to succeed
        payments.add(new PayPalPayment(
                BigDecimal.valueOf(9000),
                "user@test.com",  // should match class validEmail for success
                "123456",
                BigDecimal.valueOf(10000)
        ));

        // Add a Bank Transfer payment
        // amount: 2650, current account balance: 2595 → payment will fail
        payments.add(new BankTransferPayment(
                BigDecimal.valueOf(2650),
                "123456789",
                "QNB",
                BigDecimal.valueOf(2595)
        ));

        // Create a PaymentProcessor and pass the list of payments
        PaymentProcessor processor = new PaymentProcessor(payments);

        // Process all payments: authenticate, validate, execute payment, generate receipt, refund if applicable
        processor.processPayments();
    }
}

