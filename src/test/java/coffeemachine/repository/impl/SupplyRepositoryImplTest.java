package coffeemachine.repository.impl;

import coffeemachine.entity.Supply;
import coffeemachine.exception.NoSupplyAmountInParametersException;
import coffeemachine.repository.SupplyRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SupplyRepositoryImplTest {

    private final SupplyRepository supplyRepository = new SupplyRepositoryImpl();

    private static Supply createdSupply;

    private static Optional<Supply> lastSupplyBySupplyType;

    private static Long WATER_SUPPLY_TYPE_ID = 1L;
    private static Long MILK_SUPPLY_TYPE_ID = 2L;
    private static Long WRONG_SUPPLY_TYPE_ID = 7L;
    private static Integer SOME_AMOUNT_500 = 500;
    private static Integer SOME_AMOUNT_600 = 600;
    private static Integer SOME_AMOUNT_1900 = 1900;


    @Test
    void createSuccessful() {
        Supply expectedSupply =
                Supply
                        .builder()
                        .supplyTypeId(WATER_SUPPLY_TYPE_ID)
                        .amount(SOME_AMOUNT_600)
                        .build();

        createdSupply = supplyRepository.create(
                Supply
                        .builder()
                        .supplyTypeId(WATER_SUPPLY_TYPE_ID)
                        .amount(SOME_AMOUNT_600)
                        .build()
        );

        assertNotNull(createdSupply.getId());
        assertEquals(expectedSupply.getSupplyTypeId(), createdSupply.getSupplyTypeId());
        assertEquals(expectedSupply.getAmount(), createdSupply.getAmount());
    }

    @Test
    void createSupplyWithoutAmount() {
        assertThrows(NoSupplyAmountInParametersException.class,
                () -> supplyRepository.create(Supply.builder().supplyTypeId(WATER_SUPPLY_TYPE_ID).build()));
    }

    @Test
    void getLastBySupplyType() {
        createdSupply = supplyRepository.create(
                Supply
                        .builder()
                        .supplyTypeId(MILK_SUPPLY_TYPE_ID)
                        .amount(SOME_AMOUNT_1900)
                        .build()
        );

        lastSupplyBySupplyType = supplyRepository.getLastBySupplyType(MILK_SUPPLY_TYPE_ID);

        assertTrue(lastSupplyBySupplyType.isPresent());
        assertEquals(createdSupply, lastSupplyBySupplyType.get());
    }

    @Test
    void getLastBySupplyTypeWhenTwo() {
        createdSupply = supplyRepository.create(
                Supply
                        .builder()
                        .supplyTypeId(WATER_SUPPLY_TYPE_ID)
                        .amount(SOME_AMOUNT_500)
                        .build()
        );
        lastSupplyBySupplyType = supplyRepository.getLastBySupplyType(WATER_SUPPLY_TYPE_ID);

        assertTrue(lastSupplyBySupplyType.isPresent());
        assertEquals(createdSupply, lastSupplyBySupplyType.get());
    }

    @Test
    void getLastByNonexistentSupplyType() {
        lastSupplyBySupplyType = supplyRepository.getLastBySupplyType(WRONG_SUPPLY_TYPE_ID);

        assertTrue(lastSupplyBySupplyType.isEmpty());
    }
}