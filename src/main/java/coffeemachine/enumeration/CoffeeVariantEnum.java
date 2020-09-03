package coffeemachine.enumeration;

public enum CoffeeVariantEnum {
    ESPRESSO(250, 0, 16, 14, "1"),
    LATTE(350, 75, 20, 5, "2"),
    CAPPUCCINO(200, 100, 12, 6, "3");

    private final int waterVolume;
    private final int milkVolume;
    private final int coffeeWeight;
    private final int price;
    private final String codeFromInput;

    CoffeeVariantEnum(int waterVolume, int milkVolume, int coffeeWeight, int price, String codeFromInput) {
        this.waterVolume = waterVolume;
        this.milkVolume = milkVolume;
        this.coffeeWeight = coffeeWeight;
        this.price = price;
        this.codeFromInput = codeFromInput;
    }

    public int getWaterVolume() {
        return waterVolume;
    }

    public int getMilkVolume() {
        return milkVolume;
    }

    public int getCoffeeWeight() {
        return coffeeWeight;
    }

    public int getPrice() {
        return price;
    }

    public String getCodeFromInput() {
        return codeFromInput;
    }
}