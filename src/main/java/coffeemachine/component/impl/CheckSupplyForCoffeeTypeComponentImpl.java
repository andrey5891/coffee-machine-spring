package coffeemachine.component.impl;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckSupplyForCoffeeTypeComponentImpl implements CheckSupplyForCoffeeTypeComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;

    @Override
    public String checkAvailableSupplyAndGetMessage(CoffeeVariantEnum coffeeVariant) {
        Integer availableWaterVolume = supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.WATER).get()).get().getAmount();
        Integer availableMilkVolume = supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.MILK).get()).get().getAmount();
        Integer availableCoffeeWeight = supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.COFFEE).get()).get().getAmount();
        Integer availableCupNumber = supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.CUP).get()).get().getAmount();

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
