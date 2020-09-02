package coffeemachine.service;

import coffeemachine.dto.SupplyListDto;

public interface SupplyService {
    void fillAllSupply(SupplyListDto supplyListDto);

    SupplyListDto getRemainingSupplyWithCash();
 }
