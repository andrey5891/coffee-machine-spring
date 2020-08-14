package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;

public interface CheckSupplyForCoffeeTypeService {
    String checkAvailableSupplyAndGetMessage(CoffeeVariantEnum coffeeVariant);
}
