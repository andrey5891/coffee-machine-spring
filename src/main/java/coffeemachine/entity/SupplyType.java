package coffeemachine.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SupplyType {
    private Long id;

    private String name;

    private String measurementTypeId;
}
