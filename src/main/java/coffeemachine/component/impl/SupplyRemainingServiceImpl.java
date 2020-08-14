package coffeemachine.component.impl;

import coffeemachine.component.SupplyRemainingService;
import coffeemachine.dto.RemainingSupply;
import coffeemachine.entity.Money;
import coffeemachine.entity.Supply;
import coffeemachine.repository.MoneyRepository;
import coffeemachine.repository.SupplyRepository;
import coffeemachine.repository.impl.MoneyRepositoryImpl;
import coffeemachine.repository.impl.SupplyRepositoryImpl;

import static coffeemachine.enumeration.MoneyLocationEnum.BANK;
import static coffeemachine.enumeration.SupplyTypeEnum.*;

public class SupplyRemainingServiceImpl implements SupplyRemainingService {
    private final SupplyRepository supplyRepository = new SupplyRepositoryImpl();
    private final MoneyRepository moneyRepository = new MoneyRepositoryImpl();

    @Override
    public RemainingSupply getRemainingSupply() {
        Supply supplyWithZeroAmount = Supply.builder().amount(0).build();
        Money moneyWithZeroAmount = Money.builder().amount(0L).build();

        return RemainingSupply.builder()
                .availableWaterVolume(supplyRepository.getLastBySupplyType(WATER).orElse(supplyWithZeroAmount).getAmount())
                .availableMilkVolume(supplyRepository.getLastBySupplyType(MILK).orElse(supplyWithZeroAmount).getAmount())
                .availableCoffeeWeight(supplyRepository.getLastBySupplyType(COFFEE).orElse(supplyWithZeroAmount).getAmount())
                .availableCupNumber(supplyRepository.getLastBySupplyType(CUP).orElse(supplyWithZeroAmount).getAmount())
                .availableCashAmount(moneyRepository.getLastByMoneyLocation(BANK).orElse(moneyWithZeroAmount).getAmount())
                .build();
    }
}
