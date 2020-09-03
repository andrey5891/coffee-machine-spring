package coffeemachine.component;

import coffeemachine.component.impl.SupplyRemainingComponentImpl;
import coffeemachine.converter.Converter;
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
public class SupplyRemainingComponentImplTest {
    @Mock
    private SupplyRepository supplyRepository;

    @Mock
    private Converter<Supply, SupplyModel> converter;

    @Mock
    private SupplyTypeRepository supplyTypeRepository;

    @InjectMocks
    private SupplyRemainingComponentImpl supplyRemainingService;

    public static final int AMOUNT = 150;

    public static final long WATER_SUPPLY_TYPE_ID = 1L;
    public static final long MILK_SUPPLY_TYPE_ID = 2L;
    public static final long COFFEE_SUPPLY_TYPE_ID = 3L;
    public static final long CUP_SUPPLY_TYPE_ID = 4L;

    private static final Supply WATER_SUPPLY = Supply.builder()
            .amount(AMOUNT)
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .build();

    private static final Supply MILK_SUPPLY = Supply.builder()
            .amount(AMOUNT)
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .build();

    private static final Supply COFFEE_SUPPLY = Supply.builder()
            .amount(AMOUNT)
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .build();

    private static final Supply CUP_SUPPLY = Supply.builder()
            .amount(AMOUNT)
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .build();

    public static final SupplyModel WATER_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(WATER_SUPPLY_TYPE_ID)
            .amount(AMOUNT)
            .build();

    public static final SupplyModel MILK_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(MILK_SUPPLY_TYPE_ID)
            .amount(AMOUNT)
            .build();

    public static final SupplyModel COFFEE_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(COFFEE_SUPPLY_TYPE_ID)
            .amount(AMOUNT)
            .build();

    public static final SupplyModel CUP_SUPPLY_MODEL = SupplyModel.builder()
            .supplyTypeId(CUP_SUPPLY_TYPE_ID)
            .amount(AMOUNT)
            .build();

    private static final List<SupplyModel> supplyModelList = List.of(
            WATER_SUPPLY_MODEL,
            MILK_SUPPLY_MODEL,
            COFFEE_SUPPLY_MODEL,
            CUP_SUPPLY_MODEL
    );

    @Test
    public void mocksNotNull() {
        assertNotNull(supplyRepository);
        assertNotNull(converter);
        assertNotNull(supplyRemainingService);
    }

    @Test
    public void getSupplyRemaining() {
        when(supplyRepository.getLastBySupplyTypeId(WATER_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(WATER_SUPPLY));

        when(supplyRepository.getLastBySupplyTypeId(MILK_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(MILK_SUPPLY));

        when(supplyRepository.getLastBySupplyTypeId(COFFEE_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(COFFEE_SUPPLY));

        when(supplyRepository.getLastBySupplyTypeId(CUP_SUPPLY_TYPE_ID))
                .thenReturn(Optional.of(CUP_SUPPLY));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(WATER))
                .thenReturn(Optional.of(WATER_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(MILK))
                .thenReturn(Optional.of(MILK_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(COFFEE))
                .thenReturn(Optional.of(COFFEE_SUPPLY_TYPE_ID));

        when(supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(CUP))
                .thenReturn(Optional.of(CUP_SUPPLY_TYPE_ID));

        when(converter.convert(WATER_SUPPLY))
                .thenReturn(WATER_SUPPLY_MODEL);

        when(converter.convert(MILK_SUPPLY))
                .thenReturn(MILK_SUPPLY_MODEL);

        when(converter.convert(COFFEE_SUPPLY))
                .thenReturn(COFFEE_SUPPLY_MODEL);

        when(converter.convert(CUP_SUPPLY))
                .thenReturn(CUP_SUPPLY_MODEL);

        assertArrayEquals(supplyModelList.toArray(), supplyRemainingService.getRemainingSupply().toArray());
    }
}
