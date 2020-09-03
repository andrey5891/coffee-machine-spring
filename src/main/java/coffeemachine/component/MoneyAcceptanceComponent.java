package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;

public interface MoneyAcceptanceComponent {
    Boolean isMoneyReceived(CoffeeVariantEnum coffeeVariant);

    void moveMoneyFromReceiverToBank();
}
