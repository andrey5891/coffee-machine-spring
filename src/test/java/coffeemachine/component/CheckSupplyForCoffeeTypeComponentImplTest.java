package coffeemachine.component;

import coffeemachine.component.impl.CheckSupplyForCoffeeTypeComponentImpl;
import coffeemachine.entity.Supply;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static coffeemachine.enumeration.CoffeeVariantEnum.CAPPUCCINO;
import static coffeemachine.enumeration.SupplyTypeEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckSupplyForCoffeeTypeComponentImplTest {
    @Mock
    private SupplyRepository supplyRepository;
    @Mock
    private SupplyTypeRepository supplyTypeRepository;
    @InjectMocks
    private CheckSupplyForCoffeeTypeComponentImpl checkSupplyForCoffeeTypeComponentImpl;

    private static final long WATER_SUPPLY_TYPE_ID = 1L;
    private static final long MILK_SUPPLY_TYPE_ID = 2L;
    private static final long COFFEE_SUPPLY_TYPE_ID = 3L;
    private static final long CUP_SUPPLY_TYPE_ID = 4L;

    private Supply waterSupply = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .amount(550)
            .build();

    private Supply milkSupply = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .amount(250)
            .build();

    private Supply coffeeSupply = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .amount(120)
            .build();

    private Supply cupSupply = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .amount(10)
            .build();

    private final String OK = "OK";
    private final String NOT_ENOUGH_MILK = "Sorry, not enough milk!";


    @Test
    public void mocksNotNull() {
        assertNotNull(supplyRepository);
        assertNotNull(supplyTypeRepository);
    }

    @Test
    public void checkAvailableSupplyAndGetMessageWhenOkTest() {
        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE_ID));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER).get()))
                .thenReturn(Optional.of(waterSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK).get()))
                .thenReturn(Optional.of(milkSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE).get()))
                .thenReturn(Optional.of(coffeeSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP).get()))
                .thenReturn(Optional.of(cupSupply));

        assertEquals(OK, checkSupplyForCoffeeTypeComponentImpl.checkAvailableSupplyAndGetMessage(CAPPUCCINO));
    }

    @Test
    public void checkAvailableSupplyAndGetMessageWhenNotEnoughMilkTest() {
        milkSupply.setAmount(50);

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE_ID));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER).get()))
                .thenReturn(Optional.of(waterSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK).get()))
                .thenReturn(Optional.of(milkSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE).get()))
                .thenReturn(Optional.of(coffeeSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP).get()))
                .thenReturn(Optional.of(cupSupply));

        assertEquals(NOT_ENOUGH_MILK, checkSupplyForCoffeeTypeComponentImpl.checkAvailableSupplyAndGetMessage(CAPPUCCINO));
    }
}