package payment;


import java.math.BigDecimal;

// double 100.0-100.0 = 0.568
public abstract class Payment {
    private final BigDecimal amount;

    public Payment(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public abstract void processPayment();

    public void generateReceipt(){
        System.out.println("Receipt: Payment of: " + amount + "$");
    }
}
