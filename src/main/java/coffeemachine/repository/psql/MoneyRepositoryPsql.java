package coffeemachine.repository.psql;

import coffeemachine.entity.Money;
import coffeemachine.repository.MoneyRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Primary
public interface MoneyRepositoryPsql extends MoneyRepository, CrudRepository<Money, Long> {
    @Override
    default Money create(Money money) {
        return save(money);
    }

    @Override
    default Optional<Money> getLastByMoneyLocationId(Long locationId) {
        return findFirstByMoneyLocationIdOrderByIdDesc(locationId);
    }

    Optional<Money> findFirstByMoneyLocationIdOrderByIdDesc(Long moneyLocationId);

    Money save(Money money);
}
