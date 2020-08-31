package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.model.SupplyModel;

import java.util.List;

public interface SupplyManagerComponent {
    List<SupplyModel> fillAllSupply(List<SupplyModel> supplyModelList);

    List<SupplyModel> reduceAllSupply(CoffeeVariantEnum coffeeVariantEnum);
}
