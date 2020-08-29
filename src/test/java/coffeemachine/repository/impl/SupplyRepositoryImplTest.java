package coffeemachine.repository.impl;

import coffeemachine.entity.Supply;
import coffeemachine.exception.NoSupplyAmountInParametersException;
import coffeemachine.repository.SupplyRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static coffeemachine.enumeration.SupplyTypeEnum.MILK;
import static coffeemachine.enumeration.SupplyTypeEnum.WATER;
import static org.junit.jupiter.api.Assertions.*;

class SupplyRepositoryImplTest {

    private final SupplyRepository supplyRepository = new SupplyRepositoryImpl();

    private static Supply createdSupply;

    private static Optional<Supply> lastSupplyBySupplyType;

    private static final Long WATER_SUPPLY_TYPE_ID = 1L;
    private static final Long MILK_SUPPLY_TYPE_ID = 2L;
    private static final Integer SOME_AMOUNT_500 = 500;
    private static final Integer SOME_AMOUNT_600 = 600;
    private static final Integer SOME_AMOUNT_1900 = 1900;

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

        lastSupplyBySupplyType = supplyRepository.getLastBySupplyTypeId(MILK_SUPPLY_TYPE_ID);

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
        lastSupplyBySupplyType = supplyRepository.getLastBySupplyTypeId(WATER_SUPPLY_TYPE_ID);

        assertTrue(lastSupplyBySupplyType.isPresent());
        assertEquals(createdSupply, lastSupplyBySupplyType.get());
    }
}