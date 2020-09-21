package coffeemachine.component;

/**
 * Interface for working with coffee machine receiver
 * @author Andrey Korchenkov
 * @version 1.0
 */
public interface MoneyAcceptanceComponent {
    /**
     * Methods Checks that there is money for coffee in the receiver
     * @param coffeeVariantName coffee variant in string
     * @return true if nedeed money in receiver
     */
    Boolean isMoneyReceived(String coffeeVariantName);

    /**
     * Method moves money from receiver to bank
     */
    void moveMoneyFromReceiverToBank();
}
