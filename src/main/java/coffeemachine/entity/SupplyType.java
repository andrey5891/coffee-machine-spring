package coffeemachine.entity;

import lombok.Data;

@Data
public class SupplyType {
    private Long id;

    private String name;

    private String measurementTypeId;
}
