package coffeemachine.component;

import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.model.SupplyModel;

import java.util.List;

public interface SupplyManagerComponent {
    void fillAllSupply(List<SupplyModel> supplyModelList);

    void reduceAllSupply(CoffeeVariantEnum coffeeVariantEnum);
}
