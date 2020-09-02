package coffeemachine.component.impl;

import coffeemachine.component.MoneyReceivingComponent;
import coffeemachine.entity.Money;
import coffeemachine.exception.NoSuchMoneyException;
import coffeemachine.exception.NoSuchMoneyLocationTypeException;
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
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        return moneyRepository.getLastByMoneyLocationId(moneyInBankLocationId)
                .orElseThrow(NoSuchMoneyException::new).getAmount();
    }

    @Override
    public void setAvailAbleCashAMountToZero() {
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        moneyRepository.create(Money.builder()
                .moneyLocationId(moneyInBankLocationId)
                .amount(0L)
                .build());
    }
}
