package coffeemachine.repository;

import coffeemachine.entity.Supply;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.exception.NoSupplyAmountInParametersException;

import java.util.Optional;

public interface SupplyRepository {
    /**
     * @param supply - supply DTO for creating entry without ID
     * @return  supply DTO of created entry with ID
     * @throws NoSupplyAmountInParametersException
     */
    Supply create(Supply supply);

    /**
     * @param supplyType - supply type enum
     * @return last value supply by supply type ID
     */
    Optional<Supply> getLastBySupplyType(SupplyTypeEnum supplyType);
}
