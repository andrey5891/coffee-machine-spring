package coffeemachine.repository.impl;

import coffeemachine.entity.Supply;
import coffeemachine.exception.NoSupplyAmountInParametersException;
import coffeemachine.repository.SupplyRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class SupplyRepositoryImpl implements SupplyRepository {
    private Long currentId = 1L;
    private Map<Long, Supply> supplyMap;

    public SupplyRepositoryImpl() {
        fillDbSimulationByBeginValues();
    }

    @Override
    public Supply create(Supply supply) {
        if (supply.getAmount() == null || supply.getAmount() == 0) {
            throw new NoSupplyAmountInParametersException();
        }
        supply.setId(currentId++);
        supplyMap.put(supply.getId(), supply);
        return supply;
    }

    public Optional<Supply> getLastBySupplyTypeId(Long supplyTypeId) {
        return supplyMap.entrySet().stream()
                .filter(e -> e.getValue().getSupplyTypeId().equals(supplyTypeId))
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    private Long getNextIdValue() {
        return currentId++;
    }

    private void fillDbSimulationByBeginValues() {
        this.supplyMap = new HashMap<>(
                Map.of(
                        currentId, Supply.builder()
                                .id(getNextIdValue())
                                .supplyTypeId(1L)
                                .amount(500L)
                                .build(),

                        currentId, Supply.builder()
                                .id(getNextIdValue())
                                .supplyTypeId(2L)
                                .amount(300L)
                                .build(),

                        currentId, Supply.builder()
                                .id(getNextIdValue())
                                .supplyTypeId(3L)
                                .amount(18L)
                                .build(),

                        currentId, Supply.builder()
                                .id(getNextIdValue())
                                .supplyTypeId(4L)
                                .amount(10L)
                                .build()
                ));
    }
}
