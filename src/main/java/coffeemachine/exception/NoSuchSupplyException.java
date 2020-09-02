package coffeemachine.exception;

public class NoSuchSupplyException extends RuntimeException{
    public NoSuchSupplyException() {
        super("No or null supply by this supply location id in system(DB imitation)");
    }
}
