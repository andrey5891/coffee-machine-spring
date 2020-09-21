package coffeemachine.component;

import coffeemachine.model.SupplyModel;

import java.util.List;

/**
 * Interface for showing remaining supplies in coffee machine
 * @author Andrey Korchenkov
 * @version 1.0
 */
public interface SupplyRemainingComponent {
    /**
     * Method for getting info about supplies in coffee machine
     * @return list of supply model different types
     */
    List<SupplyModel> getRemainingSupply();
}