package coffeemachine.exception;

public class NoSuchSupplyTypeException extends RuntimeException {

    public NoSuchSupplyTypeException() {
        super("No or null such supply type in system(DB imitation)");
    }
}
