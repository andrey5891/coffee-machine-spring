package coffeemachine.component;

import coffeemachine.model.SupplyModel;

import java.util.List;

public interface FillCoffeeMachineOfSupplyComponent {
    List<SupplyModel> fillAllSupply(List<SupplyModel> supplyModelList);
}
