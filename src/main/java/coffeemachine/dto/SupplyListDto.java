package coffeemachine.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SupplyListDto {

    private Integer availableWaterVolume;

    private Integer availableMilkVolume;

    private Integer availableCoffeeWeight;

    private Integer availableCupNumber;

    private Integer availAbleCash;
}
