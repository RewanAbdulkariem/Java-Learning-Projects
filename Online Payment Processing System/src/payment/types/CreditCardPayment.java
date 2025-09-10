package payment.types;
import payment.RefundablePayment;
import payment.SecureTransaction;

import java.math.BigDecimal;
import java.time.YearMonth;

public class CreditCardPayment extends RefundablePayment implements SecureTransaction {
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;
    private BigDecimal accountBalance;


    public CreditCardPayment(BigDecimal amount, String cardNumber, String cvv, String expiryDate, BigDecimal accountBalance) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.accountBalance = accountBalance;
    }

    /**
     * This method uses the Luhn Algorithm to check if a credit card number is valid.
     * The steps are as follows:
     * 1. Start from the rightmost digit (check digit) and move left.
     * 2. Double the value of every second digit.
     * - If the doubled value is greater than 9, sum its digits (e.g., 12 → 1 + 2 = 3).
     * 3. Sum all the digits after the above modification.
     * 4. If the total sum is divisible by 10, the credit card number is valid.
     *
     * @return true if the card number is valid, false otherwise
     */
    public boolean validateCardNumber() {
        int sum = 0;
        boolean doubleDigit = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }
        return sum % 10 == 0;
    }

    /**
     * This method uses a regular expression (Regex) to validate the CVV.
     * It checks if the CVV consists only of numeric digits and has a length of 3 or 4.
     *
     * @return true if the CVV is valid, false otherwise
     */


    public boolean validateCvv() {

        return cvv.matches("^[0-9]{3,4}$");
    }

    /**
     * This method validates that the credit card's expiry date is not in the past.
     *
     * @return true if the expiry date is valid (not in the past), false otherwise
     */

    public boolean validateExpiryDate() {
        YearMonth cardExpiry = YearMonth.parse(expiryDate, java.time.format.DateTimeFormatter.ofPattern("MM/yy"));
        YearMonth currentDate = YearMonth.now();
        return !cardExpiry.isBefore(currentDate);
    }

    /**
     * Authenticates the credit card user by validating all card details.
     * It performs the following checks:
     * 1. validateCardNumber() - ensures the card number is valid.
     * 2. validateCvv() - ensures the CVV is numeric and has 3 or 4 digits.
     * 3. validateExpiryDate() - ensures the card has not expired.
     *
     * @return true if all validations pass (user is authenticated), false otherwise
     */
    @Override
    public boolean authenticateUser() {
        return validateCardNumber() && validateCvv() && validateExpiryDate();
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
     * It performs the following checks:
     * 1. authenticateUser() - ensures the credit card details are valid (card number, CVV, expiry date).
     * 2. validateBalance(paymentAmount) - ensures there is enough balance to cover the payment.
     *
     * @return true if all checks pass and the transaction is valid, false otherwise
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
        accountBalance  = accountBalance.add(refundAmount);
        System.out.println("Refunded " + refundAmount + " to card. New balance: " + accountBalance);
    }

    /**
     * Processes a payment if balance is sufficient and creditcard details are valid.
     */
    @Override
    public void processPayment() {
        BigDecimal paymentAmount = getAmount();

        if (validateTransaction()) {
            accountBalance = accountBalance.subtract(paymentAmount);
            System.out.println("Payment successful! Remaining balance: " + accountBalance);
        } else {
            System.out.println("Payment failed. Check card details or balance.");
        }
    }
}
