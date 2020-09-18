package coffeemachine.service.impl;

import coffeemachine.component.MoneyReceivingComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.converter.Converter;
import coffeemachine.dto.MoneyDto;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;
import coffeemachine.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {
    private final SupplyManagerComponent supplyManagerComponent;
    private final Converter<SupplyListDto, List<SupplyModel>> supplyListDtoToSupplyModelListConverter;
    private final Converter<List<SupplyModel>, SupplyListDto> supplyModelListToSupplyListDtoConverter;
    private final SupplyRemainingComponent supplyRemainingComponent;
    private final MoneyReceivingComponent moneyReceivingComponent;

    @Override
    public void fillAllSupply(SupplyListDto supplyListDto) {
        List<SupplyModel> supplyModelList = supplyListDtoToSupplyModelListConverter.convert(supplyListDto);
        supplyManagerComponent.fillAllSupply(supplyModelList);
    }

    @Override
    public SupplyListDto getRemainingSupplyWithCash() {

        List<SupplyModel> remainingSupply = supplyRemainingComponent.getRemainingSupply();
        SupplyListDto supplyListDto = supplyModelListToSupplyListDtoConverter
                .convert(remainingSupply);

        Long availableCashAmount = moneyReceivingComponent.getAvailableCashAmount();
        supplyListDto.setAvailableCash(availableCashAmount.intValue());
        return supplyListDto;
    }

    @Override
    public MoneyDto takeCashFromBank() {
        MoneyDto moneyDto = MoneyDto.builder()
                .cashAmount(moneyReceivingComponent.getAvailableCashAmount())
                .build();

        moneyReceivingComponent.setAvailAbleCashAMountToZero();
        return moneyDto;
    }
}
