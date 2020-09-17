package coffeemachine.component;

import coffeemachine.component.impl.CheckSupplyForCoffeeTypeComponentImpl;
import coffeemachine.entity.Supply;
import coffeemachine.entity.SupplyType;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static coffeemachine.enumeration.SupplyTypeEnum.COFFEE;
import static coffeemachine.enumeration.SupplyTypeEnum.CUP;
import static coffeemachine.enumeration.SupplyTypeEnum.MILK;
import static coffeemachine.enumeration.SupplyTypeEnum.WATER;
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

    private static final SupplyType WATER_SUPPLY_TYPE = SupplyType.builder()
            .id(1L)
            .name("WATER")
            .build();

    private static final SupplyType MILK_SUPPLY_TYPE = SupplyType.builder()
            .id(2L)
            .name("MILK")
            .build();

    private static final SupplyType COFFEE_SUPPLY_TYPE = SupplyType.builder()
            .id(3L)
            .name("COFFEE")
            .build();

    private static final SupplyType CUP_SUPPLY_TYPE = SupplyType.builder()
            .id(4L)
            .name("CUP")
            .build();

    private static final String CAPPUCCINO = "CAPPUCCINO";

    private Supply waterSupply = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE.getId())
            .amount(550)
            .build();

    private Supply milkSupply = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE.getId())
            .amount(250)
            .build();

    private Supply coffeeSupply = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE.getId())
            .amount(120)
            .build();

    private Supply cupSupply = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE.getId())
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
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER).get().getId()))
                .thenReturn(Optional.of(waterSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK).get().getId()))
                .thenReturn(Optional.of(milkSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE).get().getId()))
                .thenReturn(Optional.of(coffeeSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP).get().getId()))
                .thenReturn(Optional.of(cupSupply));

        assertEquals(OK, checkSupplyForCoffeeTypeComponentImpl.checkAvailableSupplyAndGetMessage(CAPPUCCINO));
    }

    @Test
    public void checkAvailableSupplyAndGetMessageWhenNotEnoughMilkTest() {
        milkSupply.setAmount(50);

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER).get().getId()))
                .thenReturn(Optional.of(waterSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK).get().getId()))
                .thenReturn(Optional.of(milkSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE).get().getId()))
                .thenReturn(Optional.of(coffeeSupply));

        when(supplyRepository.getLastBySupplyTypeId(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP).get().getId()))
                .thenReturn(Optional.of(cupSupply));

        assertEquals(NOT_ENOUGH_MILK, checkSupplyForCoffeeTypeComponentImpl.checkAvailableSupplyAndGetMessage(CAPPUCCINO));
    }
}