package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;

public interface CheckSupplyForCoffeeTypeComponent {
    String checkAvailableSupplyAndGetMessage(CoffeeVariantEnum coffeeVariant);
}
