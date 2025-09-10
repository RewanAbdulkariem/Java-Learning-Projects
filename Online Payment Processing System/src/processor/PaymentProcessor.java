package processor;

import payment.Payment;
import payment.RefundablePayment;
import payment.SecureTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PaymentProcessor {
    private ArrayList<Payment> payments;
    public PaymentProcessor(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public void processPayments(){
        System.out.println("*****************************************");
        for (Payment payment : payments){
            System.out.println("Processing payment: " + payment.getClass().getSimpleName());

            if (payment instanceof SecureTransaction securePayment){
                if (securePayment.validateTransaction()){
                    System.out.println("Secure Transaction successful");
                    payment.processPayment();
                    payment.generateReceipt();
                } else {
                    System.out.println("Secure Transaction failed. Skipping payment.");
                }
            } else {
                System.out.println("Not a secure transaction");
                payment.processPayment();
                payment.generateReceipt();
            }

            if (payment instanceof RefundablePayment refundable){
                System.out.println("Refunding payment of " + payment.getAmount());
                refundable.refund(payment.getAmount());
            }
            System.out.println("*****************************************");
        }
    }

}
