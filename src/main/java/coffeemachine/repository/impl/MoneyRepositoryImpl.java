package coffeemachine.repository.impl;

import coffeemachine.entity.Money;
import coffeemachine.exception.NoMoneyAmountInParametersException;

import java.util.*;

public class MoneyRepositoryImpl implements coffeemachine.repository.MoneyRepository {

    private Long currentId = 1L;

    private Map<Long, Money> moneyMap;

    public MoneyRepositoryImpl() {
        fillDbSimulationByBeginValues();
    }

    public MoneyRepositoryImpl(List<Money> moneyList) { //для тестов

    }

    @Override
    public Money create(Money money) {
        if (money.getAmount() == null || money.getAmount() < 0) {
            throw new NoMoneyAmountInParametersException();
        }
        money.setId(getNextIdValue());
        moneyMap.put(money.getId(), money);

        return money;
    }

    @Override
    public Optional<Money> getLastByMoneyLocation(Long locationId) {
        return moneyMap.entrySet().stream()
                .filter(n -> n.getValue().getMoneyLocationId().equals(locationId))
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    private Long getNextIdValue() {
        return currentId++;
    }

    private void fillDbSimulationByBeginValues() {
        this.moneyMap = new HashMap<>(
                Map.of(
                        currentId, Money.builder().id(getNextIdValue()).moneyLocationId(1L).amount(1500L).build()
                )
        );
    }
}
