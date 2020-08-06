package coffeemachine.enumeration;

public enum MoneyLocationEnum {
    BANK("BANK"),
    RECEIVER("RECEIVER");

    private final String name;

    MoneyLocationEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
