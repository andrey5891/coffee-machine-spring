package coffeemachine.repository;

import coffeemachine.entity.Money;
import coffeemachine.enumeration.MoneyLocationEnum;
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
     * @param moneyLocation - money location enum
     * @return last value money amount by money location ID
     */
    Optional<Money> getLastByMoneyLocation(MoneyLocationEnum moneyLocation);
}
