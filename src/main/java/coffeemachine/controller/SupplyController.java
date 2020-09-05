package coffeemachine.controller;

import coffeemachine.dto.SupplyListDto;
import coffeemachine.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;

    @GetMapping("/remaining")
    public SupplyListDto getRemainingSupply() {
        return supplyService.getRemainingSupplyWithCash();
    }



}
