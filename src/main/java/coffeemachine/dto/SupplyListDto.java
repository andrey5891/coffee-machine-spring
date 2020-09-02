package coffeemachine.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplyListDto {

    private Integer availableWaterVolume;

    private Integer availableMilkVolume;

    private Integer availableCoffeeWeight;

    private Integer availableCupNumber;

    private Integer availAbleCash;
}
