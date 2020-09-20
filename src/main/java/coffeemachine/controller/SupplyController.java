package coffeemachine.controller;

import coffeemachine.dto.MoneyDto;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;

    @GetMapping("/remaining")
    public SupplyListDto getRemainingSupply() {
        return supplyService.getRemainingSupplyWithCash();
    }

    @PostMapping("/take-cash")
    public MoneyDto takeCashFromBank() {
        return supplyService.takeCashFromBank();
    }

    @PostMapping("/fill")
    public void fillAllSupply(@Valid @RequestBody SupplyListDto supplyListDto) {
        supplyService.fillAllSupply(supplyListDto);
    }
}
