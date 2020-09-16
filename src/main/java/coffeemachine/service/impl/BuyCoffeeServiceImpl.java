package coffeemachine.service.impl;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyCoffeeServiceImpl implements BuyCoffeeService {

    private final CheckSupplyForCoffeeTypeComponent checkSupplyForCoffeeTypeComponent;
    private final MoneyAcceptanceComponent moneyAcceptanceComponent;
    private final SupplyManagerComponent supplyManagerComponent;

    @Override
    public BuyCoffeeMessageDto makeCoffeeIfAvailableAndGetMessage(CoffeeTypeDto coffeeTypeDto) {
        String coffeeVariantName = coffeeTypeDto.getCoffeeType().toUpperCase();
        String message = checkSupplyForCoffeeTypeComponent.checkAvailableSupplyAndGetMessage(coffeeVariantName);
        if (message.equals("OK")) {
            message = "I have enough resources, making you a coffee!";

            if (!moneyAcceptanceComponent.isMoneyReceived(coffeeVariantName)) {
                message = "You put not enough money!";
            } else {
                moneyAcceptanceComponent.moveMoneyFromReceiverToBank();
                supplyManagerComponent.reduceAllSupply(coffeeVariantName);
            }
        }
        return BuyCoffeeMessageDto.builder().message(message).build();
    }
}