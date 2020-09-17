package coffeemachine.repository.psql;

import coffeemachine.entity.SupplyType;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.repository.SupplyTypeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Primary
public interface SupplyTypeRepositoryPsql extends SupplyTypeRepository, CrudRepository<SupplyType, Long> {
    @Override
    default Optional<SupplyType> getSupplyTypeIdBySupplyTypeEnum(SupplyTypeEnum supplyType) {
        return findFirstByName(supplyType.getName());
    }

    Optional<SupplyType> findFirstByName(String name);
}
