package coffeemachine.enumeration;

public enum SupplyTypeEnum {
    WATER("WATER"),
    MILK("MILK"),
    COFFEE("COFFEE"),
    CUPS("CUP");

    private final String name;

    SupplyTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
