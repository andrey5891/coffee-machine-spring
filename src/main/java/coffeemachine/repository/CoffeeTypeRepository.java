package coffeemachine.repository;

import coffeemachine.entity.CoffeeType;

import java.util.Optional;

public interface CoffeeTypeRepository {

    /**
     * @param name - name of coffee type
     * @return coffee type found by name
     */
    Optional<CoffeeType> getCoffeeTypeByName(String name);
}
