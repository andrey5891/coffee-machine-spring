package coffeemachine.component.impl;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.entity.CoffeeType;
import coffeemachine.exception.NoSuchCoffeeTypeException;
import coffeemachine.exception.NoSuchSupplyTypeException;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import coffeemachine.repository.psql.CoffeeTypeRepositoryPsql;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static coffeemachine.enumeration.SupplyTypeEnum.COFFEE;
import static coffeemachine.enumeration.SupplyTypeEnum.CUP;
import static coffeemachine.enumeration.SupplyTypeEnum.MILK;
import static coffeemachine.enumeration.SupplyTypeEnum.WATER;

@Component
@RequiredArgsConstructor
public class CheckSupplyForCoffeeTypeComponentImpl implements CheckSupplyForCoffeeTypeComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final CoffeeTypeRepositoryPsql coffeeTypeRepositoryPsql;

    @Override
    public String checkAvailableSupplyAndGetMessage(String coffeeVariantName) {
        Integer availableWaterVolume = supplyRepository.getLastBySupplyTypeId(
                supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER).orElseThrow(NoSuchSupplyTypeException::new))
                .orElseThrow()
                .getAmount();

        Integer availableMilkVolume = supplyRepository.getLastBySupplyTypeId(
                supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK).orElseThrow(NoSuchSupplyTypeException::new))
                .orElseThrow()
                .getAmount();

        Integer availableCoffeeWeight = supplyRepository.getLastBySupplyTypeId(
                supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE).orElseThrow(NoSuchSupplyTypeException::new))
                .orElseThrow()
                .getAmount();

        Integer availableCupNumber = supplyRepository.getLastBySupplyTypeId(
                supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP).orElseThrow(NoSuchSupplyTypeException::new))
                .orElseThrow()
                .getAmount();

        String resultMessage = "Sorry, not enough ";

        CoffeeType coffeeType = coffeeTypeRepositoryPsql
                .getCoffeeTypeByName(coffeeVariantName)
                .orElseThrow(NoSuchCoffeeTypeException::new);

        if (availableWaterVolume < coffeeType.getWaterAmount()) {
            resultMessage += "water!";
        } else if (availableMilkVolume < coffeeType.getMilkAmount()) {
            resultMessage += "milk!";
        } else if (availableCoffeeWeight < coffeeType.getCoffeeBeanAmount()) {
            resultMessage += "coffee beans!";
        } else if (availableCupNumber < 1) {
            resultMessage += "cups!";
        } else {
            return "OK";
        }
        return resultMessage;
    }
}