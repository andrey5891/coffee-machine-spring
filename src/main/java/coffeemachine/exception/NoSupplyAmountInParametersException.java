package coffeemachine.exception;

public class NoSupplyAmountInParametersException extends RuntimeException {

    public NoSupplyAmountInParametersException() {
        super("No or null supply amount parameter in request");
    }
}
