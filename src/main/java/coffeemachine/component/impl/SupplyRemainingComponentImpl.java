package coffeemachine.component.impl;

import coffeemachine.component.SupplyRemainingService;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.MoneyRepository;
import coffeemachine.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class SupplyRemainingServiceImpl implements SupplyRemainingService {

    private final SupplyRepository supplyRepository;
    private final MoneyRepository moneyRepository;


    @Override
    public List<SupplyModel> getRemainingSupply() {
        return null;
    }
}
