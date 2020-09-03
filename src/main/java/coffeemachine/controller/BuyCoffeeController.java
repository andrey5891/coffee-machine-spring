package coffeemachine.controller;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuyCoffeeController {
    private final BuyCoffeeService buyCoffeeService;

    public BuyCoffeeMessageDto buyCoffee(CoffeeTypeDto coffeeTypeDto) {
        return buyCoffeeService.makeCoffeeIfAvailableAndGetMessage(coffeeTypeDto);
    }
}
