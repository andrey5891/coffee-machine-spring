package coffeemachine.repository.impl;

import coffeemachine.entity.MoneyLocation;
import coffeemachine.enumeration.MoneyLocationEnum;
import coffeemachine.repository.MoneyLocationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MoneyLocationRepositoryImpl implements MoneyLocationRepository {

    private Map<Long, MoneyLocation> moneyLocationMap = new HashMap<>(Map.of(
            1L, MoneyLocation.builder().id(1L).name("BANK").build(),
            2L, MoneyLocation.builder().id(2L).name("RECEIVER").build()
            ));

    @Override
    public Optional<Long> getIdMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum moneyLocation) {
        return moneyLocationMap.values().stream()
                .filter(e -> e.getName().equals(moneyLocation.getName()))
                .map(MoneyLocation::getId)
                .findFirst();
    }
}