package coffeemachine.component;

import coffeemachine.component.impl.SupplyManagerComponentImpl;
import coffeemachine.entity.Supply;
import coffeemachine.entity.SupplyType;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static coffeemachine.enumeration.SupplyTypeEnum.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplyManagerComponentImplTest {
    @Mock
    private SupplyRepository supplyRepository;
    @Mock
    private SupplyTypeRepository supplyTypeRepository;
    @InjectMocks
    private SupplyManagerComponentImpl fillCoffeeMachineOfSupplyComponentImpl;

    private static final String CAPPUCCINO = "CAPPUCCINO";
    private static final CoffeeVariantEnum CAPPUCCINO_ENUM = CoffeeVariantEnum.CAPPUCCINO;

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

    private static final Integer WATER_AMOUNT = 500;
    private static final Integer MILK_AMOUNT = 300;
    private static final Integer COFFEE_AMOUNT = 20;
    private static final Integer CUP_AMOUNT = 10;

    private static final Integer WATER_AMOUNT_BEFORE_FILL = 300;
    private static final Integer MILK_AMOUNT_BEFORE_FILL = 200;
    private static final Integer COFFEE_AMOUNT_BEFORE_FILL = 15;
    private static final Integer CUP_AMOUNT_BEFORE_FILL = 7;

    private static final Integer WATER_AMOUNT_AFTER_FILL = WATER_AMOUNT + WATER_AMOUNT_BEFORE_FILL;
    private static final Integer MILK_AMOUNT_AFTER_FILL = MILK_AMOUNT + MILK_AMOUNT_BEFORE_FILL;
    private static final Integer COFFEE_AMOUNT_AFTER_FILL = COFFEE_AMOUNT + COFFEE_AMOUNT_BEFORE_FILL;
    private static final Integer CUP_AMOUNT_AFTER_FILL = CUP_AMOUNT + CUP_AMOUNT_BEFORE_FILL;

    private static final Integer WATER_AMOUNT_AFTER_BUY = WATER_AMOUNT_AFTER_FILL - CAPPUCCINO_ENUM.getWaterVolume();
    private static final Integer MILK_AMOUNT_AFTER_BUY = MILK_AMOUNT_AFTER_FILL - CAPPUCCINO_ENUM.getMilkVolume();
    private static final Integer COFFEE_AMOUNT_AFTER_BUY = COFFEE_AMOUNT_AFTER_FILL - CAPPUCCINO_ENUM.getCoffeeWeight();
    private static final Integer CUP_AMOUNT_AFTER_BUY = CUP_AMOUNT_AFTER_FILL - 1;

    public static final SupplyModel WATER_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeEnum(WATER)
            .amount(WATER_AMOUNT)
            .build();

    public static final SupplyModel MILK_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeEnum(MILK)
            .amount(MILK_AMOUNT)
            .build();

    public static final SupplyModel COFFEE_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeEnum(COFFEE)
            .amount(COFFEE_AMOUNT)
            .build();

    public static final SupplyModel CUP_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeEnum(CUP)
            .amount(CUP_AMOUNT)
            .build();

    private static final List<SupplyModel> supplyModelList = List.of(
            WATER_SUPPLY_MODEL,
            MILK_SUPPLY_MODEL,
            COFFEE_SUPPLY_MODEL,
            CUP_SUPPLY_MODEL
    );

    Supply waterSupplyAfterFill = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE.getId())
            .amount(WATER_AMOUNT_AFTER_FILL)
            .build();

    Supply milkSupplyAfterFill = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE.getId())
            .amount(MILK_AMOUNT_AFTER_FILL)
            .build();

    Supply coffeeSupplyAfterFill = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE.getId())
            .amount(COFFEE_AMOUNT_AFTER_FILL)
            .build();

    Supply cupSupplyAfterFill = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE.getId())
            .amount(CUP_AMOUNT_AFTER_FILL)
            .build();

    Supply waterSupplyBeforeFill = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE.getId())
            .amount(WATER_AMOUNT_BEFORE_FILL)
            .build();

    Supply milkSupplyBeforeFill = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE.getId())
            .amount(MILK_AMOUNT_BEFORE_FILL)
            .build();

    Supply coffeeSupplyBeforeFill = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE.getId())
            .amount(COFFEE_AMOUNT_BEFORE_FILL)
            .build();

    Supply cupSupplyBeforeFill = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE.getId())
            .amount(CUP_AMOUNT_BEFORE_FILL)
            .build();

    Supply waterSupplyAfterBuy = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE.getId())
            .amount(WATER_AMOUNT_AFTER_BUY)
            .build();

    Supply milkSupplyAfterBuy = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE.getId())
            .amount(MILK_AMOUNT_AFTER_BUY)
            .build();

    Supply coffeeSupplyAfterBuy = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE.getId())
            .amount(COFFEE_AMOUNT_AFTER_BUY)
            .build();

    Supply cupSupplyAfterBuy = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE.getId())
            .amount(CUP_AMOUNT_AFTER_BUY)
            .build();

    @Test
    public void fillAllSupplyTest() {
        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE));

        when(supplyRepository.getLastBySupplyTypeId(WATER_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(waterSupplyBeforeFill));

        when(supplyRepository.getLastBySupplyTypeId(MILK_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(milkSupplyBeforeFill));

        when(supplyRepository.getLastBySupplyTypeId(COFFEE_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(coffeeSupplyBeforeFill));

        when(supplyRepository.getLastBySupplyTypeId(CUP_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(cupSupplyBeforeFill));

        when(supplyRepository.create(waterSupplyAfterFill))
                .thenReturn(waterSupplyAfterFill);

        when(supplyRepository.create(milkSupplyAfterFill))
                .thenReturn(milkSupplyAfterFill);

        when(supplyRepository.create(coffeeSupplyAfterFill))
                .thenReturn(coffeeSupplyAfterFill);

        when(supplyRepository.create(cupSupplyAfterFill))
                .thenReturn(cupSupplyAfterFill);

        fillCoffeeMachineOfSupplyComponentImpl.fillAllSupply(supplyModelList);

        verify(supplyTypeRepository, times(4)).getSupplyTypeIdBySupplyTypeEnum(any());
        verify(supplyRepository, times(4)).getLastBySupplyTypeId(any());
        verify(supplyRepository, times(4)).create(any());
    }

    @Test
    public void reduceAllSupplyTest() {
        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE));

        when(supplyRepository.getLastBySupplyTypeId(WATER_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(waterSupplyAfterFill));

        when(supplyRepository.getLastBySupplyTypeId(MILK_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(milkSupplyAfterFill));

        when(supplyRepository.getLastBySupplyTypeId(COFFEE_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(coffeeSupplyAfterFill));

        when(supplyRepository.getLastBySupplyTypeId(CUP_SUPPLY_TYPE.getId()))
                .thenReturn(Optional.of(cupSupplyAfterFill));

        when(supplyRepository.create(waterSupplyAfterBuy))
                .thenReturn(waterSupplyAfterBuy);

        when(supplyRepository.create(milkSupplyAfterBuy))
                .thenReturn(milkSupplyAfterBuy);

        when(supplyRepository.create(coffeeSupplyAfterBuy))
                .thenReturn(coffeeSupplyAfterBuy);

        when(supplyRepository.create(cupSupplyAfterBuy))
                .thenReturn(cupSupplyAfterBuy);

        fillCoffeeMachineOfSupplyComponentImpl.reduceAllSupply(CAPPUCCINO);

        verify(supplyTypeRepository, times(4)).getSupplyTypeIdBySupplyTypeEnum(any());
        verify(supplyRepository, times(4)).getLastBySupplyTypeId(any());
        verify(supplyRepository, times(4)).create(any());
    }
}