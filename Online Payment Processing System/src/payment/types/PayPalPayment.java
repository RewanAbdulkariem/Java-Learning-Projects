package payment.types;
import payment.RefundablePayment;
import payment.SecureTransaction;

import java.math.BigDecimal;

public class PayPalPayment extends RefundablePayment implements SecureTransaction {
    private String email;
    private String password;
    private BigDecimal accountBalance;

    public PayPalPayment(BigDecimal amount, String email, String password, BigDecimal accountBalance) {
        super(amount);
        this.email = email;
        this.password = password;
        this.accountBalance = accountBalance;

    }
    /**
     * Authenticates the PayPal user by validating the email and password
     * against predefined credentials.
     *
     * @return true if email and password match the predefined credentials false otherwise
     */

    @Override
    public boolean authenticateUser() {
        String validEmail = "user@test.com";
        String validPassword = "123456";

        if (email.equals(validEmail) && password.equals(validPassword)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if the available balance is sufficient to cover the payment amount.
     *
     * @return true if the balance is enough to process the payment, false otherwise
     */
    public boolean validateBalance(){
        BigDecimal paymentAmount = getAmount();
        if (accountBalance.compareTo(paymentAmount) >= 0){
            return true;
        } else {
            System.out.println("Insufficient funds: cannot process payment of " + paymentAmount);
            return false;
        }
    }
    /**
     * Validates whether a transaction can be processed safely.
     * This includes checking:
     * 1. User authentication via email and password.
     * 2. Sufficient balance to cover the payment.
     *
     * @return true if all checks pass, false otherwise
     */
    @Override
    public boolean validateTransaction() {
        return authenticateUser() && validateBalance();
    }

    /**
     * Processes a refund by adding the specified amount back to the balance.
     *
     * @param refundAmount the amount to be refunded
     */
    @Override
    public void refund(BigDecimal refundAmount) {
        accountBalance = accountBalance.add(refundAmount);
        System.out.println("Refunded " + refundAmount + ". New balance: " + accountBalance);
    }

    /**
     * Processes a payment if the user is authenticated and has sufficient funds.
     */
    @Override
    public void processPayment() {
        BigDecimal paymentAmount = getAmount();

        if (validateTransaction()) {
            accountBalance = accountBalance.subtract(paymentAmount);
            System.out.println("Payment successful! Remaining balance: " + accountBalance);
        } else {
            System.out.println("Payment failed. Check credentials or balance.");
        }
    }

}
