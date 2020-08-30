package coffeemachine.exception;

public class NoSuchMoneyLocationTypeException extends RuntimeException {

    public NoSuchMoneyLocationTypeException() {
        super("No or null such money location type in system(DB imitation)");
    }
}
