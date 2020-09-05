package coffeemachine.controller;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class BuyCoffeeController {
    private final BuyCoffeeService buyCoffeeService;

    @PostMapping
    public BuyCoffeeMessageDto buyCoffee(@RequestBody CoffeeTypeDto coffeeTypeDto) {
        return buyCoffeeService.makeCoffeeIfAvailableAndGetMessage(coffeeTypeDto);
    }

    @GetMapping
    public String buyCoffee() {
        return "list";
    }

    @GetMapping("/{id}")
    public String buyCoffee(@PathVariable Integer id) {
        return "list";
    }
}
