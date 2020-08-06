package coffeemachine.exception;

public class NoSuchCoffeeTypeException extends RuntimeException {

    public NoSuchCoffeeTypeException() {
        super("No such coffee type in system(DB imitation)");
    }
}
