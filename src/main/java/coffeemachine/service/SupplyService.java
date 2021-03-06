package coffeemachine.service;

import coffeemachine.dto.MoneyDto;
import coffeemachine.dto.SupplyListDto;

/**
 * Interface for working with supply and money in coffee machine
 * @author Andrey Korchenkov
 * @version 1.0
 */
public interface SupplyService {
    /**
     * Method fot filling coffee machine by specified supplies
     * @param supplyListDto - DTO with four supply type and their amount
     */
    void fillAllSupply(SupplyListDto supplyListDto);

    /**
     * Method for getting info about supply amount list with cash amount in coffee machine
     * @return DTO with amount of four supply types and cash
     */
    SupplyListDto getRemainingSupplyWithCash();

    MoneyDto takeCashFromBank();
}
