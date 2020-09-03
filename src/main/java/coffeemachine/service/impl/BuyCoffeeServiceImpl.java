package coffeemachine.service.impl;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuyCoffeeServiceImpl implements BuyCoffeeService {

    private final CheckSupplyForCoffeeTypeComponent checkSupplyForCoffeeTypeComponent;
    private final MoneyAcceptanceComponent moneyAcceptanceComponent;
    private final SupplyManagerComponent supplyManagerComponent;

    @Override
    public String makeCoffeeIfAvailableAndGetMessage(CoffeeVariantEnum coffeeVariant) {
        String message = checkSupplyForCoffeeTypeComponent.checkAvailableSupplyAndGetMessage(coffeeVariant);
        if (message.equals("OK") & moneyAcceptanceComponent.isMoneyReceived(coffeeVariant)) {
            message = "I have enough resources, making you a coffee!";
            moneyAcceptanceComponent.moveMoneyFromReceiverToBank();
            supplyManagerComponent.reduceAllSupply(coffeeVariant);
        }
        return message;
    }
}