package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;

public interface MoneyAcceptanceService {
    Boolean isMoneyReceived(CoffeeVariantEnum coffeeVariant);

    void moveMoneyFromReceiverToBank();
}
