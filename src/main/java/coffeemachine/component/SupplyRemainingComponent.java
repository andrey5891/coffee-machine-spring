package coffeemachine.component;

import coffeemachine.model.SupplyModel;

import java.util.List;

public interface SupplyRemainingComponent {
    List<SupplyModel> getRemainingSupply();
}