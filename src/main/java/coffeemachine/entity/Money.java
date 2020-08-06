package coffeemachine.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Money {
    private Long id;

    private Long moneyLocationId;

    private Long amount;
}
