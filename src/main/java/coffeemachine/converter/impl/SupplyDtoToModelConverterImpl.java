package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.dto.SupplyListDto;
import coffeemachine.model.SupplyModel;

import java.util.List;

public class SupplyDtoToModelConverterImpl implements Converter<SupplyListDto, List<SupplyModel>> {
    @Override
    public List<SupplyModel> convert(SupplyListDto source) {
        return List.of(

                SupplyModel.builder()
                        .supplyTypeId(1L)
                        .amount(source.getAvailableWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeId(2L)
                        .amount(source.getAvailableWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeId(3L)
                        .amount(source.getAvailableWaterVolume())
                        .build(),

                SupplyModel.builder()
                        .supplyTypeId(4L)
                        .amount(source.getAvailableWaterVolume())
                        .build()
        );
    }
}
