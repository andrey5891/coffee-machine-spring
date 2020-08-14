package coffeemachine.enumeration;

public enum SupplyTypeEnum {
    WATER("WATER"),
    MILK("MILK"),
    COFFEE("COFFEE"),
    CUP("CUP");

    private final String name;

    SupplyTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
