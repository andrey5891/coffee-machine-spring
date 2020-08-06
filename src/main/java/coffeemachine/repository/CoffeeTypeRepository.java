package coffeemachine.repository;

import coffeemachine.entity.CoffeeType;

import java.util.Optional;

public interface CoffeeTypeRepository {
    Optional<CoffeeType> getCoffeeTypeByName(String name);
}
