package coffeemachine.exception;

public class NoSuchMoneyException extends RuntimeException{
    public NoSuchMoneyException() {
        super("No or null money by this money location id in system(DB imitation)");
    }
}
