package coffeemachine.repository;

import coffeemachine.entity.Money;
import coffeemachine.exception.NoMoneyAmountInParametersException;

import java.util.Optional;

public interface MoneyRepository {
    /**
     * @param money - money DTO for creating entry without ID
     * @return money DTO of created entry with ID
     * @throws NoMoneyAmountInParametersException
     */
    Money create(Money money);

    /**
     * @param locationId - money location ID
     * @return last value money amount by money location ID
     */
    Optional<Money> getLastByMoneyLocation(Long locationId);
}
