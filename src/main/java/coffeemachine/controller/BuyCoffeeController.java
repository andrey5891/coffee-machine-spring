package coffeemachine.controller;

import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.exception.NoSuchCoffeeTypeException;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class BuyCoffeeController {
    private final BuyCoffeeService buyCoffeeService;

    @PostMapping("/buy")
    public BuyCoffeeMessageDto buyCoffee(@Valid @RequestBody CoffeeTypeDto coffeeTypeDto) {
        return buyCoffeeService.makeCoffeeIfAvailableAndGetMessage(coffeeTypeDto);
    }

    /*@ExceptionHandler(NoSuchCoffeeTypeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleMethodArgumentNotValidException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }*/
}
