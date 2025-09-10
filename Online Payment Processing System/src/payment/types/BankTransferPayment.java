package payment.types;

import payment.Payment;

import java.math.BigDecimal;

public class BankTransferPayment extends Payment {
    private String accountNumber;
    private String bankName;
    private BigDecimal accountBalance;

    public BankTransferPayment(BigDecimal amount, String accountNumber, String bankName, BigDecimal accountBalance) {
        super(amount);
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountBalance = accountBalance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * Processes a payment if balance is sufficient.
     */
    @Override
    public void processPayment() {
        BigDecimal paymentAmount = getAmount();
        System.out.println("Processing bank transfer payment of " + paymentAmount
                + " from account " + accountNumber + " at " + bankName);

        if (accountBalance.compareTo(paymentAmount) >= 0){
            accountBalance = accountBalance.subtract(paymentAmount);
            System.out.println("Payment successful! Remaining balance: " + accountBalance);
        } else {
            System.out.println("Payment failed! Check your balance.");
        }
    }
}
