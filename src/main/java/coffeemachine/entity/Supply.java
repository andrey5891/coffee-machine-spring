package coffeemachine.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Supply {
    private Long id;

    private Long code;

    private Long supplyTypeId;

    private Integer amount;
}
