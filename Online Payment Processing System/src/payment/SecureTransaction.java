package payment;

import java.math.BigDecimal;

public interface SecureTransaction {
    boolean authenticateUser();
    boolean validateTransaction();
}
