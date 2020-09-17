package coffeemachine.repository;

import coffeemachine.entity.SupplyType;
import coffeemachine.enumeration.SupplyTypeEnum;

import java.util.Optional;

public interface SupplyTypeRepository {
    Optional<SupplyType> getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum supplyType);
}
