package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.model.SupplyModel;

public class SupplyEntityToModelConverterImpl implements Converter<Supply, SupplyModel> {
    @Override
    public SupplyModel convert(Supply source) {
        return SupplyModel.builder()
                .supplyTypeEnum(SupplyTypeEnum.getSupplyTypeEnumByCode(source.getCode()))
                .amount(source.getAmount())
                .build();
    }
}
