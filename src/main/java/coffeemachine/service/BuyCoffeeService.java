package coffeemachine.service;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;

/**
 * Interface for buying coffee by DTO with string of coffee type
 * @author Andrey Korchenkov
 * @version 1.0
 */
public interface BuyCoffeeService {
    /**
     * Method attempts to buy coffee by coffee type DTO with string of coffee type
     * @param coffeeTypeDto - DTO with coffee type
     * @return DTO with string message about successful purchase
     */
    BuyCoffeeMessageDto makeCoffeeIfAvailableAndGetMessage(CoffeeTypeDto coffeeTypeDto);
}
