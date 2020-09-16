package coffeemachine.repository.psql;

import coffeemachine.entity.CoffeeType;
import coffeemachine.repository.CoffeeTypeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Primary
public interface CoffeeTypeRepositoryPsql extends CoffeeTypeRepository, CrudRepository<CoffeeType, Long> {
    @Override
    default Optional<CoffeeType> getCoffeeTypeByName(String name) {
        return findFirstByName(name);
    }

    Optional<CoffeeType> findFirstByName(String name);
}
