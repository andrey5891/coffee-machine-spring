package coffeemachine.service.impl;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.converter.Converter;
import coffeemachine.converter.impl.CoffeeTypeDtoToCoffeeTypeEnumConverter;
import coffeemachine.dto.BuyCoffeeMessageDto;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.entity.CoffeeType;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.service.BuyCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyCoffeeServiceImpl implements BuyCoffeeService {

    private final CheckSupplyForCoffeeTypeComponent checkSupplyForCoffeeTypeComponent;
    private final MoneyAcceptanceComponent moneyAcceptanceComponent;
    private final SupplyManagerComponent supplyManagerComponent;
    private final Converter<CoffeeTypeDto, CoffeeVariantEnum> converter;

    @Override
    public BuyCoffeeMessageDto makeCoffeeIfAvailableAndGetMessage(CoffeeTypeDto coffeeTypeDto) {
        CoffeeVariantEnum coffeeVariant = converter.convert(coffeeTypeDto);
        String message = checkSupplyForCoffeeTypeComponent.checkAvailableSupplyAndGetMessage(coffeeVariant);
        if (message.equals("OK")) {
            message = "I have enough resources, making you a coffee!";

            if (!moneyAcceptanceComponent.isMoneyReceived(coffeeVariant)) {
                message = "You put not enough money!";
            } else {
                moneyAcceptanceComponent.moveMoneyFromReceiverToBank();
                supplyManagerComponent.reduceAllSupply(coffeeVariant);
            }
        }

        return BuyCoffeeMessageDto.builder().message(message).build();
    }
}