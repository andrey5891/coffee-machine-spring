package coffeemachine.repository;

import coffeemachine.entity.Supply;

import java.util.Optional;

public interface SupplyRepository {
    Supply create(Supply supply);

    Optional<Supply> getLastBySupplyType(Long supplyTypeId);
}
