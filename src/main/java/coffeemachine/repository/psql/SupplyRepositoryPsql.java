package coffeemachine.repository.psql;

import coffeemachine.entity.Supply;
import coffeemachine.repository.SupplyRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Primary
public interface SupplyRepositoryPsql extends SupplyRepository, CrudRepository<Supply, Long> {
    @Override
    default Supply create(Supply supply) {
        return save(supply);
    }

    @Override
    default Optional<Supply> getLastBySupplyTypeId(Long supplyTypeId) {
        return findFirstBySupplyTypeIdOrderByIdDesc(supplyTypeId);
    }

    Optional<Supply> findFirstBySupplyTypeIdOrderByIdDesc(Long supplyTypeId);

    Supply save(Supply supply);
}
