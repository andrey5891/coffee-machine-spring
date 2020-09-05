package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.enumeration.CoffeeVariantEnum;
import org.springframework.stereotype.Component;

@Component
public class CoffeeTypeDtoToCoffeeTypeEnumConverter implements Converter<CoffeeTypeDto, CoffeeVariantEnum> {

    @Override
    public CoffeeVariantEnum convert(CoffeeTypeDto source) {
        return CoffeeVariantEnum.valueOf(source.getCoffeeType());
    }
}
