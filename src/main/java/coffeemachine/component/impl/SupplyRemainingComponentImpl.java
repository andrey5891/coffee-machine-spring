package coffeemachine.component.impl;

import coffeemachine.component.SupplyRemainingComponent;
import coffeemachine.converter.Converter;
import coffeemachine.entity.Supply;
import coffeemachine.enumeration.SupplyTypeEnum;
import coffeemachine.exception.NoSuchSupplyException;
import coffeemachine.exception.NoSuchSupplyTypeException;
import coffeemachine.model.SupplyModel;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.SupplyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static coffeemachine.enumeration.SupplyTypeEnum.*;

@Component
@RequiredArgsConstructor
public class SupplyRemainingComponentImpl implements SupplyRemainingComponent {
    private final SupplyRepository supplyRepository;
    private final SupplyTypeRepository supplyTypeRepository;
    private final Converter<Supply, SupplyModel> converter;

    @Override
    public List<SupplyModel> getRemainingSupply() {
        List<SupplyModel> list = new ArrayList<>();
        list.add(getSupplyModelOfType(WATER));
        list.add(getSupplyModelOfType(MILK));
        list.add(getSupplyModelOfType(COFFEE));
        list.add(getSupplyModelOfType(CUP));
        return list;
    }

    private SupplyModel getSupplyModelOfType(SupplyTypeEnum supplyTypeEnum) {
        Long supplyTypeId = supplyTypeRepository
                .getSupplyTypeIdBySupplyTypeEnum(supplyTypeEnum)
                .orElseThrow(NoSuchSupplyTypeException::new).getId();

        Supply supply = supplyRepository.getLastBySupplyTypeId(supplyTypeId).orElseThrow(NoSuchSupplyException::new);

        SupplyModel supplyModel = converter.convert(supply);
        supplyModel.setSupplyTypeEnum(supplyTypeEnum);
        return supplyModel;
    }
}
