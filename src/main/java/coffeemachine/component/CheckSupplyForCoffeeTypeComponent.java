package coffeemachine.component;

/**
 * Interface for checking the required amount of coffee supplies
 * @author Andrey Korchenkov
 * @version 1.0
 */
public interface CheckSupplyForCoffeeTypeComponent {
    /**
     * Method for checking available supply for specified coffee variant
     * @param coffeeVariantName coffee variant in string
     * @return DTO with string message about successful purchase
     */
    String checkAvailableSupplyAndGetMessage(String coffeeVariantName);
}
