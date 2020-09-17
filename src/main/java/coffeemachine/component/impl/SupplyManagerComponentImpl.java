package coffeemachine.component.impl;

import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.entity.CoffeeType;
import coffeemachine.entity.Supply;
import coffeemachine.exception.NoSuchCoffeeTypeException;
import coffeemachine.exception.NoSuchSupplyException;
import coffeemachine.exception.NoSuchSupplyTypeException;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import coffeemachine.repository.psql.CoffeeTypeRepositoryPsql;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static coffeemachine.enumeration.SupplyTypeEnum.COFFEE;
import static coffeemachine.enumeration.SupplyTypeEnum.CUP;
import static coffeemachine.enumeration.SupplyTypeEnum.MILK;
import static coffeemachine.enumeration.SupplyTypeEnum.WATER;

@Component
@RequiredArgsConstructor
public class SupplyManagerComponentImpl implements SupplyManagerComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final CoffeeTypeRepositoryPsql coffeeTypeRepositoryPsql;
    private final Integer ONE_CUP_AMOUNT = 1;

    @Override
    public void fillAllSupply(List<SupplyModel> supplyModelList) {
        for (SupplyModel supplyModel : supplyModelList) {
            Long supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(supplyModel.getSupplyTypeEnum())
                    .orElseThrow(NoSuchSupplyTypeException::new).getId();

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
    public void reduceAllSupply(String coffeeVariantName) {
        CoffeeType coffeeType = coffeeTypeRepositoryPsql
                .getCoffeeTypeByName(coffeeVariantName)
                .orElseThrow(NoSuchCoffeeTypeException::new);

        List<SupplyModel> supplyModelList = getSupplyModelListFromCoffeeVariant(coffeeType);
        for (SupplyModel supplyModel : supplyModelList) {
            Long supplyTypeId = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(supplyModel.getSupplyTypeEnum())
                    .orElseThrow(NoSuchSupplyTypeException::new).getId();

            Integer amount = supplyRepository.getLastBySupplyTypeId(supplyTypeId)
                    .orElseThrow(NoSuchSupplyException::new).getAmount();

            Supply supply = Supply.builder()
                    .supplyTypeId(supplyTypeId)
                    .amount(amount - supplyModel.getAmount())
                    .build();
            supplyRepository.create(supply);
        }
    }

    private List<SupplyModel> getSupplyModelListFromCoffeeVariant(CoffeeType coffeeType) {
        return List.of(
                SupplyModel.builder()
                        .supplyTypeEnum(WATER)
                        .amount(coffeeType.getWaterAmount().intValue())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(MILK)
                        .amount(coffeeType.getWaterAmount().intValue())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(COFFEE)
                        .amount(coffeeType.getCoffeeBeanAmount().intValue())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(CUP)
                        .amount(ONE_CUP_AMOUNT)
                        .build()
        );
    }
}