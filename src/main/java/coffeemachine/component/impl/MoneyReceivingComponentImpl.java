package coffeemachine.component.impl;

import coffeemachine.component.MoneyReceivingComponent;
import coffeemachine.entity.Money;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;

import static coffeemachine.enumeration.MoneyLocationEnum.BANK;

@RequiredArgsConstructor
public class MoneyReceivingComponentImpl implements MoneyReceivingComponent {
    private final MoneyRepository moneyRepository;
    private final MoneyLocationRepository moneyLocationRepository;

    @Override
    public Long getAvailableCashAmount() {
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK).get();
        return moneyRepository.getLastByMoneyLocationId(moneyInBankLocationId).get().getAmount();
    }

    @Override
    public Money setAvailAbleCashAMountToZero() {
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK).get();
        return moneyRepository.create(Money.builder()
                .moneyLocationId(moneyInBankLocationId)
                .amount(0L)
                .build());
    }
}
