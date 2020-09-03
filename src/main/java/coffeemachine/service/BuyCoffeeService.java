package coffeemachine.service;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.enumeration.CoffeeVariantEnum;

public interface BuyCoffeeService {
    BuyCoffeeMessageDto makeCoffeeIfAvailableAndGetMessage(CoffeeVariantEnum coffeeVariant);
}
