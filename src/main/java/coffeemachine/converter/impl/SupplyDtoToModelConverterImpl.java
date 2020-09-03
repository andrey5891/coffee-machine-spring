package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;

import java.util.List;

import static coffeemachine.enumeration.SupplyTypeEnum.*;

public class SupplyDtoToModelConverterImpl implements Converter<SupplyListDto, List<SupplyModel>> {
    @Override
    public List<SupplyModel> convert(SupplyListDto source) {
        return List.of(
                SupplyModel.builder()
                        .supplyTypeEnum(WATER)
                        .amount(source.getAvailableWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(MILK)
                        .amount(source.getAvailableWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(COFFEE)
                        .amount(source.getAvailableWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeEnum(CUP)
                        .amount(source.getAvailableWaterVolume())
                        .build()
        );
    }
}
