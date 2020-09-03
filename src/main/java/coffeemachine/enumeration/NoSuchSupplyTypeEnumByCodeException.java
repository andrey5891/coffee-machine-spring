package coffeemachine.enumeration;

public class NoSuchSupplyTypeEnumByCodeException extends RuntimeException{
    public NoSuchSupplyTypeEnumByCodeException() {
        super("No such supply type or such code exception");
    }
}
