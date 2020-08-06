package coffeemachine.exception;

public class NoMoneyAmountInParametersException extends RuntimeException {

    public NoMoneyAmountInParametersException() {
        super("No or null money amount parameter in request");
    }
}
