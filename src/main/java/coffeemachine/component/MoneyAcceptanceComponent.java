package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;

public interface MoneyAcceptanceComponent {
    Boolean isMoneyReceived(String coffeeVariantName);

    void moveMoneyFromReceiverToBank();
}
