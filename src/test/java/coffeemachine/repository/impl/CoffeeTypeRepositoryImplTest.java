package coffeemachine.repository.impl;

import coffeemachine.entity.CoffeeType;
import coffeemachine.repository.CoffeeTypeRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoffeeTypeRepositoryImplTest {

    private static final CoffeeTypeRepository coffeeTypeRepository = new CoffeeTypeRepositoryImpl();
    private static CoffeeType espressoCoffeeTypeTemplate = CoffeeType.builder()
            .name("ESPRESSO").waterAmount(250L).milkAmount(0L).coffeeBeanAmount(16L).price(4L).build();

    private static Optional<CoffeeType> coffeeTypeByName;

    private static final String ESPRESSO = "Espresso";
    private static final String DOPPIO = "Doppio";

    @Test
    void getCoffeeTypeByName() {
        coffeeTypeByName = coffeeTypeRepository.getCoffeeTypeByName(ESPRESSO);

        assertTrue(coffeeTypeByName.isPresent());
        espressoCoffeeTypeTemplate.setId(coffeeTypeByName.get().getId());
        assertEquals(espressoCoffeeTypeTemplate,coffeeTypeByName.get());
    }

    @Test
    void getCoffeeTypeByNonexistentName() {
        coffeeTypeByName = coffeeTypeRepository.getCoffeeTypeByName(DOPPIO);

        assertTrue(coffeeTypeByName.isEmpty());
    }
}