package coffeemachine.component;

import coffeemachine.model.SupplyModel;

import java.util.List;

/**
 * Interface for working with supplies in coffee machine
 * @author Andrey Korchenkov
 * @version 1.0
 */
public interface SupplyManagerComponent {
    /**
     * Method fills coffee machine by specified supply amounts
     * @param supplyModelList
     */
    void fillAllSupply(List<SupplyModel> supplyModelList);

    /**
     * Method for reducing supplies after buying coffee
     * @param coffeeVariantName coffee variant in string
     */
    void reduceAllSupply(String coffeeVariantName);
}
