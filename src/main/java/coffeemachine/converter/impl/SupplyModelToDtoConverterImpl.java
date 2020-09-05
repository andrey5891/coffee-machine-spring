package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SupplyModelToDtoConverterImpl implements Converter<List<SupplyModel>, SupplyListDto> {
    @Override
    public SupplyListDto convert(List<SupplyModel> source) {
        return SupplyListDto.builder()
                .availableWaterVolume(source.get(0).getAmount())
                .availableMilkVolume(source.get(1).getAmount())
                .availableCoffeeWeight(source.get(2).getAmount())
                .availableCupNumber(source.get(3).getAmount())
                .build();
    }
}
