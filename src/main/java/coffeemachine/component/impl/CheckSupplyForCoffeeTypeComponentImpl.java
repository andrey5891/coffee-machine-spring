package coffeemachine.component.impl;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.exception.NoSuchSupplyTypeException;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;

import static coffeemachine.enumeration.SupplyTypeEnum.*;

@RequiredArgsConstructor
public class CheckSupplyForCoffeeTypeComponentImpl implements CheckSupplyForCoffeeTypeComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;

    @Override
    public String checkAvailableSupplyAndGetMessage(CoffeeVariantEnum coffeeVariant) {
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

        if (availableWaterVolume < coffeeVariant.getWaterVolume()) {
            resultMessage += "water!";
        } else if (availableMilkVolume < coffeeVariant.getMilkVolume()) {
            resultMessage += "milk!";
        } else if (availableCoffeeWeight < coffeeVariant.getCoffeeWeight()) {
            resultMessage += "coffee beans!";
        } else if (availableCupNumber < 1) {
            resultMessage += "cups!";
        } else {
            return "OK";
        }
        return resultMessage;
    }
}