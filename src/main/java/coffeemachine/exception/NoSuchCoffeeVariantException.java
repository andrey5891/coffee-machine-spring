package coffeemachine.exception;

public class NoSuchCoffeeVariantException extends RuntimeException{
    public NoSuchCoffeeVariantException() {
        super("No such coffee variant exception");
    }
}
