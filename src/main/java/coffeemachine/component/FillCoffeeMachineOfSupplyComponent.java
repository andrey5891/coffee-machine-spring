package coffeemachine.component;

import coffeemachine.model.SupplyModel;

import java.util.List;

public interface FillCoffeeMachineOfSupplyComponent {
    void fillAllSupply(List<SupplyModel> supplyModelList);
}
