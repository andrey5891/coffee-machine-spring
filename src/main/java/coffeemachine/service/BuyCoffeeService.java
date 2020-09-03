package coffeemachine.service;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;

public interface BuyCoffeeService {
    BuyCoffeeMessageDto makeCoffeeIfAvailableAndGetMessage(CoffeeTypeDto coffeeTypeDto);
}
