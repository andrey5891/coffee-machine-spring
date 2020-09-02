package coffeemachine.service.impl;

import coffeemachine.component.MoneyReceivingComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.converter.Converter;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;
import coffeemachine.service.SupplyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {
    private final SupplyManagerComponent supplyManagerComponent;
    private final Converter<SupplyListDto, List<SupplyModel>> supplyListDtoToSupplyModelListConverter;
    private final Converter<List<SupplyModel>, SupplyListDto> supplyModelListToSupplyListDtoConverter;
    private final SupplyRemainingComponent supplyRemainingComponent;
    private final MoneyReceivingComponent moneyReceivingComponent;

    @Override
    public void fillAllSupply(SupplyListDto supplyListDto) {
        supplyManagerComponent.fillAllSupply(supplyListDtoToSupplyModelListConverter
                .convert(supplyListDto));
    }

    @Override
    public SupplyListDto getRemainingSupplyWithCash() {
        SupplyListDto supplyListDto = supplyModelListToSupplyListDtoConverter
                .convert(supplyRemainingComponent.getRemainingSupply());

        supplyListDto.setAvailAbleCash(moneyReceivingComponent.getAvailableCashAmount().intValue());
        return supplyListDto;
    }
}
