package coffeemachine.component.impl;


import coffeemachine.component.SupplyRemainingService;
import coffeemachine.dto.RemainingSupply;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplyRemainingServiceImplTest {
    private static final SupplyRemainingService supplyRemainingService = new SupplyRemainingServiceImpl();
    private static final RemainingSupply remainingSupplyTemplate = RemainingSupply.builder()
            .availableWaterVolume(500)
            .availableMilkVolume(300)
            .availableCoffeeWeight(150)
            .availableCupNumber(10)
            .availableCashAmount(1500L)
            .build();

    @Test
    public void getSupplyRemaining() {
        assertEquals(remainingSupplyTemplate, supplyRemainingService.getRemainingSupply());
    }
}
