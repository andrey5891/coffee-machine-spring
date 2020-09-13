package coffeemachine.repository.psql;

import coffeemachine.entity.MoneyLocation;
import coffeemachine.enumeration.MoneyLocationEnum;
import coffeemachine.exception.NoSuchMoneyLocationTypeException;
import coffeemachine.repository.MoneyLocationRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Primary
public interface MoneyLocationRepositoryPgsql extends MoneyLocationRepository, CrudRepository<MoneyLocation, Long> {

    @Override
    default Optional<Long> getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum moneyLocation) {
        return Optional.ofNullable(findFirstByName(moneyLocation.getName())
                .orElseThrow(NoSuchMoneyLocationTypeException::new)
                .getId());
    }

    Optional<MoneyLocation> findFirstByName(String name);
}
