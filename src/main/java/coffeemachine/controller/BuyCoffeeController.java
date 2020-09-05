package coffeemachine.controller;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BuyCoffeeController {
    private final BuyCoffeeService buyCoffeeService;

    @GetMapping("/buy")
    public BuyCoffeeMessageDto buyCoffee(@RequestBody CoffeeTypeDto coffeeTypeDto) {
        return buyCoffeeService.makeCoffeeIfAvailableAndGetMessage(coffeeTypeDto);
    }
    /*@GetMapping
    public String buyCoffee() {
        return "list";
    }

    @GetMapping("/{id}")
    public String buyCoffee(@PathVariable Integer id) {
        return "list";
    }*/
}
