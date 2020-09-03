package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.entity.Supply;
import coffeemachine.model.SupplyModel;

public class SupplyModelToEntityConverterImpl implements Converter<SupplyModel, Supply> {
    @Override
    public Supply convert(SupplyModel source) {
        return Supply.builder()
                .amount(source.getAmount())
                .build();
    }
}
