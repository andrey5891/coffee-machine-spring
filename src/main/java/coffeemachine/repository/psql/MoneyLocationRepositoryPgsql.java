package coffeemachine.repository.psql;

import coffeemachine.entity.MoneyLocation;
import coffeemachine.enumeration.MoneyLocationEnum;
import coffeemachine.repository.MoneyLocationRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Primary
public interface MoneyLocationRepositoryPgsql extends MoneyLocationRepository, CrudRepository<MoneyLocation, Long> {

    @Override
    default Optional<MoneyLocation> getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum moneyLocation) {
        return findFirstByName(moneyLocation.getName());
    }

    Optional<MoneyLocation> findFirstByName(String name);
}
