package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;

public interface BuyCoffeeService {
    String makeCoffeeIfAvailableAndGetMessage(CoffeeVariantEnum coffeeVariant);
}
