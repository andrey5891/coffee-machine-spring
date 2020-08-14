package coffeemachine.repository;

import coffeemachine.enumeration.MoneyLocationEnum;

import java.util.Optional;

public interface MoneyLocationRepository {
    Optional<Long> getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum moneyLocation);
}
