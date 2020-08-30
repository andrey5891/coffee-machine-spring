package coffeemachine.repository.impl;

import coffeemachine.entity.CoffeeType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CoffeeTypeRepositoryImpl implements coffeemachine.repository.CoffeeTypeRepository {
    private Map<Long, CoffeeType> coffeeTypeMap;

    public CoffeeTypeRepositoryImpl() {
        fillDbSimulationByBeginValues();
    }

    @Override
    public Optional<CoffeeType> getCoffeeTypeByName(String name) {
        return coffeeTypeMap.values().stream()
                .filter(e -> name.toUpperCase().equals(e.getName()))
                .findFirst();
    }

    private void fillDbSimulationByBeginValues() {
        this.coffeeTypeMap = new HashMap<>(
                Map.of(
                        1L, CoffeeType.builder()
                                .id(1L)
                                .name("ESPRESSO")
                                .waterAmount(250L)
                                .milkAmount(0L)
                                .coffeeBeanAmount(16L)
                                .price(4L)
                                .build(),

                        2L, CoffeeType.builder()
                                .id(2L)
                                .name("LATTE")
                                .waterAmount(350L)
                                .milkAmount(75L)
                                .coffeeBeanAmount(20L)
                                .price(7L)
                                .build(),

                        3L, CoffeeType.builder()
                                .id(1L)
                                .name("CAPPUCCINO")
                                .waterAmount(200L)
                                .milkAmount(100L)
                                .coffeeBeanAmount(12L)
                                .price(6L)
                                .build()
                ));
    }
}