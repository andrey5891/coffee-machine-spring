package coffeemachine.repository;

import coffeemachine.entity.Supply;
import coffeemachine.exception.NoSupplyAmountInParametersException;

import java.util.Optional;

public interface SupplyRepository {
    /**
     * @param supply - supply DTO for creating entry without ID
     * @return supply DTO of created entry with ID
     * @throws NoSupplyAmountInParametersException
     */
    Supply create(Supply supply);

    /**
     * @param supplyTypeId - supply type id int supply type table
     * @return last value supply by supply type ID
     */
    Optional<Supply> getLastBySupplyTypeId(Long supplyTypeId);
}
