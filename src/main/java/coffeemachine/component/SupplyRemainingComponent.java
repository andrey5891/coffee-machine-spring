package coffeemachine.component;

import coffeemachine.model.SupplyModel;

import java.util.List;

public interface SupplyRemainingService {
    List<SupplyModel> getRemainingSupply();
}