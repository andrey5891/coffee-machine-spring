package coffeemachine.converter.impl;

import coffeemachine.converter.Converter;
import coffeemachine.dto.CoffeeTypeDto;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.exception.NoSuchCoffeeVariantException;
import org.springframework.stereotype.Component;

@Component
public class CoffeeTypeDtoToCoffeeTypeEnumConverter implements Converter<CoffeeTypeDto, CoffeeVariantEnum> {

    @Override
    public CoffeeVariantEnum convert(CoffeeTypeDto source) {
        try {
            return CoffeeVariantEnum.valueOf(source.getCoffeeType());
        } catch (IllegalArgumentException e) {
            throw new NoSuchCoffeeVariantException();
        }
    }
}
