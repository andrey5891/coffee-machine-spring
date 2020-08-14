package coffeemachine.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RemainingSupply {

    private Integer availableWaterVolume;

    private Integer availableMilkVolume;

    private Integer availableCoffeeWeight;

    private Integer availableCupNumber;

    private Long availableCashAmount;
}
