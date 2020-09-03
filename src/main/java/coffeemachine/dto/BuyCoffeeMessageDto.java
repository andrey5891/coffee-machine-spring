package coffeemachine.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyCoffeeMessageDto {
    private String message;
}
