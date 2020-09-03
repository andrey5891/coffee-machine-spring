package coffeemachine.service;

import coffeemachine.component.MoneyReceivingComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.converter.Converter;
import coffeemachine.dto.MoneyDto;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;
import coffeemachine.service.impl.SupplyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplyServiceImplTest {

    @Mock
    private SupplyManagerComponent supplyManagerComponent;
    @Mock
    private Converter<SupplyListDto, List<SupplyModel>> supplyListDtoToSupplyModelListConverter;
    @Mock
    private Converter<List<SupplyModel>, SupplyListDto> supplyModelListToSupplyListDtoConverter;
    @Mock
    private SupplyRemainingComponent supplyRemainingComponent;
    @Mock
    private MoneyReceivingComponent moneyReceivingComponent;

    @InjectMocks
    private SupplyServiceImpl supplyServiceImpl;

    private final Long AVAILABLE_AMOUNT_CASH = 350L;
    private final Integer WATER_VOLUME = 400;
    private final Integer MILK_VOLUME = 200;
    private final Integer COFFEE_VOLUME = 50;
    private final Integer CUP_VOLUME = 5;


    private final SupplyListDto supplyListDtoForFill = SupplyListDto.builder()
            .availableWaterVolume(WATER_VOLUME)
            .availableMilkVolume(MILK_VOLUME)
            .availableCoffeeWeight(COFFEE_VOLUME)
            .availableCupNumber(CUP_VOLUME)
            .build();

    private final SupplyListDto supplyListDtoForRemaining = SupplyListDto.builder()
            .availableWaterVolume(WATER_VOLUME)
            .availableMilkVolume(MILK_VOLUME)
            .availableCoffeeWeight(COFFEE_VOLUME)
            .availableCupNumber(CUP_VOLUME)
            .availAbleCash(AVAILABLE_AMOUNT_CASH.intValue())
            .build();

    private final List<SupplyModel> supplyModelList = List.of(
            SupplyModel.builder().supplyTypeId(1L).amount(WATER_VOLUME).build(),
            SupplyModel.builder().supplyTypeId(2L).amount(MILK_VOLUME).build(),
            SupplyModel.builder().supplyTypeId(3L).amount(COFFEE_VOLUME).build(),
            SupplyModel.builder().supplyTypeId(4L).amount(CUP_VOLUME).build()
    );

    MoneyDto moneyDto = MoneyDto.builder()
            .cashAmount(AVAILABLE_AMOUNT_CASH)
            .build();

    @Test
    public void fillAllSupplyTest() {
        when(supplyListDtoToSupplyModelListConverter.convert(supplyListDtoForFill)).thenReturn(new ArrayList<>());

        supplyServiceImpl.fillAllSupply(supplyListDtoForFill);

        verify(supplyListDtoToSupplyModelListConverter, times(1)).convert(supplyListDtoForFill);
    }

    @Test
    public void getRemainingSupplyWithCashTest() {
        when(supplyModelListToSupplyListDtoConverter.convert(supplyModelList)).thenReturn(supplyListDtoForFill);
        when(moneyReceivingComponent.getAvailableCashAmount()).thenReturn(AVAILABLE_AMOUNT_CASH);
        when(supplyRemainingComponent.getRemainingSupply()).thenReturn(supplyModelList);

        SupplyListDto supplyListDtoFromService = supplyServiceImpl.getRemainingSupplyWithCash();

        Assertions.assertEquals(supplyListDtoForRemaining, supplyListDtoFromService);
    }

    @Test
    public void takeCashFromBankTest() {
        when(moneyReceivingComponent.getAvailableCashAmount()).thenReturn(AVAILABLE_AMOUNT_CASH);

        MoneyDto moneyDtoFromService = supplyServiceImpl.takeCashFromBank();

        Assertions.assertEquals(moneyDto, moneyDtoFromService);
        verify(moneyReceivingComponent, times(1)).setAvailAbleCashAMountToZero();
    }
}
