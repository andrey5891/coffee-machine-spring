package coffeemachine.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Supply {
    private Long id;

    private Long supplyTypeId;

    private Integer amount;
}