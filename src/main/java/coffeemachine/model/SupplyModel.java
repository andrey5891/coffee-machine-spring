package coffeemachine.model;

import coffeemachine.enumeration.SupplyTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplyModel {
    private SupplyTypeEnum supplyTypeEnum;
    private Long amount;
}
