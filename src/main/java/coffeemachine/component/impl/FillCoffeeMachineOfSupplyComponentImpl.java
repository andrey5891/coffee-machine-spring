package coffeemachine.component.impl;

import coffeemachine.component.FillCoffeeMachineOfSupplyComponent;
import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FillCoffeeMachineOfSupplyComponentImpl implements FillCoffeeMachineOfSupplyComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final SupplyRemainingComponent supplyRemainingComponent;

    @Override
    public List<SupplyModel> fillAllSupply(List<SupplyModel> supplyModelList) {
        for (int i = 0; i < 4; i++) {
            Long id = supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum.values()[i]).get();
            Integer amount = supplyRepository.getLastBySupplyTypeId(id).orElseThrow().getAmount();
            SupplyModel supplyModel = supplyModelList.get(i);
            Supply supply = Supply.builder()
                    .supplyTypeId(supplyModel.getSupplyTypeId())
                    .amount(amount + supplyModel.getAmount())
                    .build();
            supplyRepository.create(supply);
        }
        return supplyRemainingComponent.getRemainingSupply();
    }
}
