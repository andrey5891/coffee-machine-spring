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
        Long supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER)
                .orElseThrow(NoSuchSupplyTypeException::new)
                .getId();

        Long availableWaterVolume = supplyRepository.getLastBySupplyTypeId(supplyTypeId)
                .orElseThrow()
                .getAmount();

        supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK)
                .orElseThrow(NoSuchSupplyTypeException::new)
                .getId();

        Long availableMilkVolume = supplyRepository.getLastBySupplyTypeId(supplyTypeId)
                .orElseThrow()
                .getAmount();

        supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE)
                .orElseThrow(NoSuchSupplyTypeException::new)
                .getId();

        Long availableCoffeeWeight = supplyRepository.getLastBySupplyTypeId(supplyTypeId)
                .orElseThrow()
                .getAmount();

        supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP)
                .orElseThrow(NoSuchSupplyTypeException::new)
                .getId();

        Long availableCupNumber = supplyRepository.getLastBySupplyTypeId(supplyTypeId)
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