package coffeemachine.component.impl;

import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.entity.Supply;
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
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FillCoffeeMachineOfSupplyComponentImplTest {
    @Mock
    private SupplyRepository supplyRepository;
    @Mock
    private SupplyTypeRepository supplyTypeRepository;
    @Mock
    private SupplyRemainingComponent supplyRemainingComponent;
    @InjectMocks
    private FillCoffeeMachineOfSupplyComponentImpl fillCoffeeMachineOfSupplyComponentImpl;

    private static final Long WATER_SUPPLY_TYPE_ID = 1L;
    private static final Long MILK_SUPPLY_TYPE_ID = 2L;
    private static final Long COFFEE_SUPPLY_TYPE_ID = 3L;
    private static final Long CUP_SUPPLY_TYPE_ID = 4L;

    private static final Integer WATER_AMOUNT = 500;
    private static final Integer MILK_AMOUNT = 300;
    private static final Integer COFFEE_AMOUNT = 20;
    private static final Integer CUP_AMOUNT = 10;

    private static final Integer WATER_AMOUNT_BEFORE_FILL = 300;
    private static final Integer MILK_AMOUNT_BEFORE_FILL = 270;
    private static final Integer COFFEE_AMOUNT_BEFORE_FILL = 15;
    private static final Integer CUP_AMOUNT_BEFORE_FILL = 7;

    private static final Integer WATER_AMOUNT_AFTER_FILL = WATER_AMOUNT + WATER_AMOUNT_BEFORE_FILL;
    private static final Integer MILK_AMOUNT_AFTER_FILL = MILK_AMOUNT + MILK_AMOUNT_BEFORE_FILL;
    private static final Integer COFFEE_AMOUNT_AFTER_FILL = COFFEE_AMOUNT + COFFEE_AMOUNT_BEFORE_FILL;
    private static final Integer CUP_AMOUNT_AFTER_FILL = CUP_AMOUNT + CUP_AMOUNT_BEFORE_FILL;

    public static final SupplyModel WATER_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .amount(WATER_AMOUNT)
            .build();

    public static final SupplyModel MILK_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .amount(MILK_AMOUNT)
            .build();

    public static final SupplyModel COFFEE_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .amount(COFFEE_AMOUNT)
            .build();

    public static final SupplyModel CUP_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .amount(CUP_AMOUNT)
            .build();

    private static final List<SupplyModel> supplyModelList = List.of(
            WATER_SUPPLY_MODEL,
            MILK_SUPPLY_MODEL,
            COFFEE_SUPPLY_MODEL,
            CUP_SUPPLY_MODEL
    );

    public static final SupplyModel WATER_SUPPLY_MODEL_WITH_ADD = SupplyModel.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .amount(WATER_AMOUNT_AFTER_FILL)
            .build();

    public static final SupplyModel MILK_SUPPLY_MODEL_WITH_ADD = SupplyModel.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .amount(MILK_AMOUNT_AFTER_FILL)
            .build();

    public static final SupplyModel COFFEE_SUPPLY_MODEL_WITH_ADD = SupplyModel.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .amount(COFFEE_AMOUNT_AFTER_FILL)
            .build();

    public static final SupplyModel CUP_SUPPLY_MODEL_WITH_ADD = SupplyModel.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .amount(CUP_AMOUNT_AFTER_FILL)
            .build();

    private static final List<SupplyModel> supplyModelListAfterFilling = List.of(
            WATER_SUPPLY_MODEL_WITH_ADD,
            MILK_SUPPLY_MODEL_WITH_ADD,
            COFFEE_SUPPLY_MODEL_WITH_ADD,
            CUP_SUPPLY_MODEL_WITH_ADD
    );

    Supply waterSupplyAfterFill = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .amount(WATER_AMOUNT_AFTER_FILL)
            .build();

    Supply milkSupplyAfterFill = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .amount(MILK_AMOUNT_AFTER_FILL)
            .build();

    Supply coffeeSupplyAfterFill = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .amount(COFFEE_AMOUNT_AFTER_FILL)
            .build();

    Supply cupSupplyAfterFill = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .amount(CUP_AMOUNT_AFTER_FILL)
            .build();

    Supply waterSupplyBeforeFill = Supply.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .amount(WATER_AMOUNT_BEFORE_FILL)
            .build();

    Supply milkSupplyBeforeFill = Supply.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .amount(MILK_AMOUNT_BEFORE_FILL)
            .build();

    Supply coffeeSupplyBeforeFill = Supply.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .amount(COFFEE_AMOUNT_BEFORE_FILL)
            .build();

    Supply cupSupplyBeforeFill = Supply.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .amount(CUP_AMOUNT_BEFORE_FILL)
            .build();

    @Test
    public void mocksNotNull() {
        assertNotNull(supplyRepository);
        assertNotNull(supplyRemainingComponent);
    }

    @Test
    public void fillAllSupplyTest() {
        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE_ID));

        when(supplyRepository.getLastBySupplyTypeId(WATER_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(waterSupplyBeforeFill));

        when(supplyRepository.getLastBySupplyTypeId(MILK_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(milkSupplyBeforeFill));

        when(supplyRepository.getLastBySupplyTypeId(COFFEE_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(coffeeSupplyBeforeFill));

        when(supplyRepository.getLastBySupplyTypeId(CUP_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(cupSupplyBeforeFill));

        when(supplyRepository.create(waterSupplyAfterFill))
                .thenReturn(waterSupplyAfterFill);

        when(supplyRepository.create(milkSupplyAfterFill))
                .thenReturn(milkSupplyAfterFill);

        when(supplyRepository.create(coffeeSupplyAfterFill))
                .thenReturn(coffeeSupplyAfterFill);

        when(supplyRepository.create(cupSupplyAfterFill))
                .thenReturn(cupSupplyAfterFill);

        when(supplyRemainingComponent.getRemainingSupply())
                .thenReturn(supplyModelListAfterFilling);

        List<SupplyModel> filledSupplyModelList = fillCoffeeMachineOfSupplyComponentImpl.fillAllSupply(supplyModelList);

        assertArrayEquals(supplyModelListAfterFilling.toArray(), filledSupplyModelList.toArray());
    }
}
