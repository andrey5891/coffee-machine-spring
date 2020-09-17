package coffeemachine.repository.impl;

import coffeemachine.entity.SupplyType;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.repository.SupplyTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class SupplyTypeRepositoryImpl implements SupplyTypeRepository {
    private Map<Long, SupplyType> supplyTypeMap = new HashMap<>(Map.of(
            1L, SupplyType.builder()
                    .id(1L)
                    .name("WATER")
                    .build(),

            2L, SupplyType.builder()
                    .id(2L)
                    .name("MILK")
                    .build(),

            3L, SupplyType.builder()
                    .id(3L)
                    .name("COFFEE")
                    .build(),

            4L, SupplyType.builder()
                    .id(4L)
                    .name("CUP")
                    .build()
    ));

    @Override
    public Optional<SupplyType> getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum supplyType) {
        return supplyTypeMap.values().stream()
                .filter(e -> e.getName().equals(supplyType.getName()))
                .findFirst();
    }
}
