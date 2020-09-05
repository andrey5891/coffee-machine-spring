package coffeemachine.component.impl;

import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.exception.NoSuchSupplyException;
import coffeemachine.exception.NoSuchSupplyTypeException;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static coffeemachine.enumeration.SupplyTypeEnum.*;

@Component
@RequiredArgsConstructor
public class SupplyManagerComponentImpl implements SupplyManagerComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final Integer ONE_CUP_AMOUNT = 1;

    @Override
    public void fillAllSupply(List<SupplyModel> supplyModelList) {
        for (SupplyModel supplyModel : supplyModelList) {
            Long supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(supplyModel.getSupplyTypeEnum())
                    .orElseThrow(NoSuchSupplyTypeException::new);

            Integer amount = supplyRepository
                    .getLastBySupplyTypeId(supplyTypeId)
                    .orElseThrow(NoSuchSupplyTypeException::new)
                    .getAmount();

            Supply supply = Supply.builder()
                    .supplyTypeId(supplyTypeId)
                    .amount(amount + supplyModel.getAmount())
                    .build();

            supplyRepository.create(supply);
        }
    }

    @Override
    public void reduceAllSupply(CoffeeVariantEnum coffeeVariantEnum) {
        List<SupplyModel> supplyModelList = getSupplyModelListFromCoffeeVariant(coffeeVariantEnum);
        for (SupplyModel supplyModel : supplyModelList) {
            Long supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(supplyModel.getSupplyTypeEnum())
                    .orElseThrow(NoSuchSupplyTypeException::new);

            Integer amount = supplyRepository.getLastBySupplyTypeId(supplyTypeId)
                    .orElseThrow(NoSuchSupplyException::new).getAmount();

            Supply supply = Supply.builder()
                    .supplyTypeId(supplyTypeId)
                    .amount(amount - supplyModel.getAmount())
                    .build();
            supplyRepository.create(supply);
        }
    }

    private List<SupplyModel> getSupplyModelListFromCoffeeVariant(CoffeeVariantEnum coffeeVariantEnum) {
        return List.of(
                SupplyModel.builder()
                        .supplyTypeEnum(WATER)
                        .amount(coffeeVariantEnum.getWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(MILK)
                        .amount(coffeeVariantEnum.getMilkVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(COFFEE)
                        .amount(coffeeVariantEnum.getCoffeeWeight())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(CUP)
                        .amount(ONE_CUP_AMOUNT)
                        .build()
        );
    }
}