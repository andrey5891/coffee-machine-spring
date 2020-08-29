package coffeemachine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplyModel {
    private Long id;

    private Long supplyTypeId;

    private Integer amount;
}
