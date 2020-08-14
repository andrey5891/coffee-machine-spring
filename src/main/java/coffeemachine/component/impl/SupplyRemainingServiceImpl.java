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

public class SupplyRemainingServiceImpl implements SupplyRemainingService {
    private final SupplyRepository supplyRepository = new SupplyRepositoryImpl();
    private final MoneyRepository moneyRepository = new MoneyRepositoryImpl();

    @Override
    public RemainingSupply getRemainingSupply() {
        RemainingSupply remainingSupply = new RemainingSupply();
        Supply supplyWithZeroAmount = Supply.builder().amount(0).build();
        Money moneyWithZeroAmount = Money.builder().amount(0L).build();

        remainingSupply.setAvailableWaterVolume(supplyRepository.getLastBySupplyType(1L).orElse(supplyWithZeroAmount).getAmount());
        remainingSupply.setAvailableMilkVolume(supplyRepository.getLastBySupplyType(2L).orElse(supplyWithZeroAmount).getAmount());
        remainingSupply.setAvailableCoffeeWeight(supplyRepository.getLastBySupplyType(3L).orElse(supplyWithZeroAmount).getAmount());
        remainingSupply.setAvailableCupNumber(supplyRepository.getLastBySupplyType(4L).orElse(supplyWithZeroAmount).getAmount());
        remainingSupply.setAvailableCashAmount(moneyRepository.getLastByMoneyLocation(BANK).orElse(moneyWithZeroAmount).getAmount());

        return remainingSupply;
    }
}
