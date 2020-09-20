package coffeemachine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoSuchCoffeeTypeException extends RuntimeException {
    public NoSuchCoffeeTypeException() {
        super("No such coffee type");
    }
}
