package coffeemachine.repository;

import coffeemachine.enumeration.SupplyTypeEnum;

import java.util.Optional;

public interface SupplyTypeRepository {
    Optional<Long> getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum supplyType);
}
