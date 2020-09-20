package coffeemachine.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeTypeDto {
    @NotBlank(message = "coffee type must have not blank value")
    private String coffeeType;
}
