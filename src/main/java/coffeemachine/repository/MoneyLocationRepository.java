package coffeemachine.repository;

import coffeemachine.entity.MoneyLocation;
import coffeemachine.enumeration.MoneyLocationEnum;

import java.util.Optional;

public interface MoneyLocationRepository {
    Optional<MoneyLocation> getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum moneyLocation);
}
