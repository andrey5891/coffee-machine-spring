package coffeemachine.component.impl;

import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.converter.Converter;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SupplyRemainingComponentImpl implements SupplyRemainingComponent {

    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final Converter<Supply, SupplyModel> converter;

    @Override
    public List<SupplyModel> getRemainingSupply() {
        List<SupplyModel> list = new ArrayList<>();
        list.add(getSupplyModelOfType(SupplyTypeEnum.WATER));
        list.add(getSupplyModelOfType(SupplyTypeEnum.MILK));
        list.add(getSupplyModelOfType(SupplyTypeEnum.COFFEE));
        list.add(getSupplyModelOfType(SupplyTypeEnum.CUP));
        return  list;
    }

    private SupplyModel getSupplyModelOfType(SupplyTypeEnum supplyTypeEnum) {
        return converter.convert(supplyRepository.getLastBySupplyTypeId(
                supplyTypeRepository.getSupplyTypeIdBySupplyTypeEnum(supplyTypeEnum).get()).get()); //продумать опшинл
    }
}
