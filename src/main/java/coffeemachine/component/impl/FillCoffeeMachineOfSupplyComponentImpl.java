package coffeemachine.component.impl;

import coffeemachine.component.FillCoffeeMachineOfSupplyComponent;
import coffeemachine.entity.Supply;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FillCoffeeMachineOfSupplyComponentImpl implements FillCoffeeMachineOfSupplyComponent {

    private final SupplyRepository supplyRepository;

    @Override
    public void fillAllSupply(List<SupplyModel> supplyModelList) {
        for (int i = 0; i < 4; i++) {
            SupplyModel supplyModel = supplyModelList.get(i);
            Supply supply = Supply.builder().supplyTypeId(supplyModel.getSupplyTypeId()).amount(supplyModel.getAmount()).build();
            supplyRepository.create(supply);
        }
    }
}
