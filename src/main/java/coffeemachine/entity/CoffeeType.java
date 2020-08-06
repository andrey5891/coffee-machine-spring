package coffeemachine.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoffeeType {
    private Long id;

    private String name;

    private Long waterAmount;

    private Long milkAmount;

    private Long coffeeBeanAmount;

    private Long price;
}
