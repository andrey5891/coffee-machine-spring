package coffeemachine.component.impl;

import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.exception.NoSuchSupplyException;
import coffeemachine.exception.NoSuchSupplyTypeException;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static coffeemachine.enumeration.SupplyTypeEnum.*;

@RequiredArgsConstructor
public class SupplyManagerComponentImpl implements SupplyManagerComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final SupplyRemainingComponent supplyRemainingComponent;

    @Override
    public List<SupplyModel> fillAllSupply(List<SupplyModel> supplyModelList) {
        for (int i = 0; i < supplyModelList.size(); i++) {
            Long supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.values()[i])
                    .orElseThrow(NoSuchSupplyTypeException::new);

            Integer amount = supplyRepository
                    .getLastBySupplyTypeId(supplyTypeId)
                    .orElseThrow(NoSuchSupplyTypeException::new)
                    .getAmount();

            SupplyModel supplyModel = supplyModelList.get(i);
            Supply supply = Supply.builder()
                    .supplyTypeId(supplyModel.getSupplyTypeId())
                    .amount(amount + supplyModel.getAmount())
                    .build();
            supplyRepository.create(supply);
        }
        return supplyRemainingComponent.getRemainingSupply();
    }

    @Override
    public List<SupplyModel> reduceAllSupply(CoffeeVariantEnum coffeeVariantEnum) {
        List<SupplyModel> supplyModelList = getSupplyModelListFromCoffeeVariant(coffeeVariantEnum);
        for (int i = 0; i < supplyModelList.size(); i++) {
            Long id = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.values()[i])
                    .orElseThrow(NoSuchSupplyTypeException::new);

            Integer amount = supplyRepository.getLastBySupplyTypeId(id)
                    .orElseThrow(NoSuchSupplyException::new).getAmount();

            SupplyModel supplyModel = supplyModelList.get(i);
            Supply supply = Supply.builder()
                    .supplyTypeId(supplyModel.getSupplyTypeId())
                    .amount(amount - supplyModel.getAmount())
                    .build();
            supplyRepository.create(supply);
        }
        return supplyRemainingComponent.getRemainingSupply();
    }

    private List<SupplyModel> getSupplyModelListFromCoffeeVariant(CoffeeVariantEnum coffeeVariantEnum) {
        return List.of(
                SupplyModel.builder()
                        .supplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER)
                                .orElseThrow(NoSuchSupplyTypeException::new))
                        .amount(coffeeVariantEnum.getWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK)
                                .orElseThrow(NoSuchSupplyTypeException::new))
                        .amount(coffeeVariantEnum.getMilkVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE)
                                .orElseThrow(NoSuchSupplyTypeException::new))
                        .amount(coffeeVariantEnum.getCoffeeWeight())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP)
                                .orElseThrow(NoSuchSupplyTypeException::new))
                        .amount(1)
                        .build()
        );
    }
}