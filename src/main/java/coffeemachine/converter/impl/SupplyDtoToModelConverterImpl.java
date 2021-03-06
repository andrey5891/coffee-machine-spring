package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static coffeemachine.enumeration.SupplyTypeEnum.*;

@Component
public class SupplyDtoToModelConverterImpl implements Converter<SupplyListDto, List<SupplyModel>> {
    @Override
    public List<SupplyModel> convert(SupplyListDto source) {
        return List.of(
                SupplyModel.builder()
                        .supplyTypeEnum(WATER)
                        .amount(source.getAvailableWaterVolume().longValue())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(MILK)
                        .amount(source.getAvailableMilkVolume().longValue())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(COFFEE)
                        .amount(source.getAvailableCoffeeWeight().longValue())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(CUP)
                        .amount(source.getAvailableCupNumber().longValue())
                        .build()
        );
    }
}
