package coffeemachine.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

@Data
@Builder
public class SupplyListDto {
    @Min(value = 1, message = "volume of water must be at least 1")
    @Max(value = 2000, message = "volume of water should be no more than 2000")
    private Integer availableWaterVolume;

    @Min(value = 1, message = "volume of milk must be at least 1")
    @Max(value = 1000, message = "volume of milk should be no more than 1000")
    private Integer availableMilkVolume;

    @Min(value = 1, message = "weight of coffee must be at least 1")
    @Max(value = 500, message = "weight of coffee should be no more than 500")
    private Integer availableCoffeeWeight;

    @Min(value = 1, message = "number of cups must be at least 1")
    @Max(value = 50, message = "number of cups should be no more than 50")
    private Integer availableCupNumber;

    @Null(message = "cash must be null")
    private Integer availableCash;
}
