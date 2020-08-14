package coffeemachine.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MoneyLocation {
    private Long id;

    private String name;
}
