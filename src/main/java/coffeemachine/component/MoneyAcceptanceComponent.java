package coffeemachine.component;

import coffeemachine.entity.Money;
import coffeemachine.enumeration.CoffeeVariantEnum;

public interface MoneyAcceptanceComponent {
    Boolean isMoneyReceived(CoffeeVariantEnum coffeeVariant);

    Money moveMoneyFromReceiverToBank();
}
