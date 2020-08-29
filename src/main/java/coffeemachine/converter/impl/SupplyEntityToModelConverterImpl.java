package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.entity.Supply;
import coffeemachine.model.SupplyModel;

public class SupplyEntityToModelConverterImpl implements Converter<Supply, SupplyModel> {
    @Override
    public SupplyModel convert(Supply source) {
        return SupplyModel.builder()
                .id(source.getId())
                .supplyTypeId(source.getSupplyTypeId())
                .amount(source.getAmount())
                .build();
    }
}
