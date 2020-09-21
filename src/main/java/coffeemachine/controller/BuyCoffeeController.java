package coffeemachine.controller;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BuyCoffeeController {
    private final BuyCoffeeService buyCoffeeService;

    @PostMapping("/buy")
    public BuyCoffeeMessageDto buyCoffee(@Valid @RequestBody CoffeeTypeDto coffeeTypeDto) {
        return buyCoffeeService.makeCoffeeIfAvailableAndGetMessage(coffeeTypeDto);
    }
}
