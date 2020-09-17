package coffeemachine.component.impl;

import coffeemachine.component.MoneyReceivingComponent;
import coffeemachine.entity.Money;
import coffeemachine.exception.NoSuchMoneyException;
import coffeemachine.exception.NoSuchMoneyLocationTypeException;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static coffeemachine.enumeration.MoneyLocationEnum.BANK;

@Component
@RequiredArgsConstructor
public class MoneyReceivingComponentImpl implements MoneyReceivingComponent {
    private final MoneyRepository moneyRepository;

    private final MoneyLocationRepository moneyLocationRepository;

    @Override
    public Long getAvailableCashAmount() {
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK)
                .orElseThrow(NoSuchMoneyLocationTypeException::new).getId();

        return moneyRepository.getLastByMoneyLocationId(moneyInBankLocationId)
                .orElseThrow(NoSuchMoneyException::new).getAmount();
    }

    @Override
    public void setAvailAbleCashAMountToZero() {
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK)
                .orElseThrow(NoSuchMoneyLocationTypeException::new).getId();

        moneyRepository.create(Money.builder()
                .moneyLocationId(moneyInBankLocationId)
                .amount(0L)
                .build());
    }
}
