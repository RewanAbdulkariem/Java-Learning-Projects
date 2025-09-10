package payment;

import java.math.BigDecimal;

public abstract class RefundablePayment extends Payment{
    public RefundablePayment(BigDecimal amount){
        super(amount);
    }
    public abstract void refund(BigDecimal refundAmount);

}
