package coffeemachine.enumeration;

public enum CoffeeMachineStateEnum {
    ACTION_CHOOSING("Write action (buy, fill, take, remaining, exit):"),
    COFFEE_VARIANT_CHOOSING("What do you want to buy? 1 - espresso," +
            " 2 - latte, 3 - cappuccino, back - to main menu:"),
    WATER_FILLING("Write how many ml of water do you want to add:"),
    MILK_FILLING("Write how many ml of milk do you want to add:"),
    COFFEE_FILLING("Write how many grams of coffee beans do you want to add:"),
    CUPS_FILLING("Write how many disposable cups of coffee do you want to add:"),
    SWITCH_OFF("");

    private final String message;

    CoffeeMachineStateEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
