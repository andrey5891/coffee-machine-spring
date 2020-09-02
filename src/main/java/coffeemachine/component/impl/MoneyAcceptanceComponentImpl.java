package coffeemachine.component.impl;

import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.entity.Money;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.exception.NoSuchMoneyException;
import coffeemachine.exception.NoSuchMoneyLocationTypeException;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static coffeemachine.enumeration.MoneyLocationEnum.*;

@RequiredArgsConstructor
public class MoneyAcceptanceComponentImpl implements MoneyAcceptanceComponent {
    private final MoneyRepository moneyRepository;
    private final MoneyLocationRepository moneyLocationRepository;

    @Override
    public Boolean isMoneyReceived(CoffeeVariantEnum coffeeVariant) {
        Long moneyInReceiverLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(RECEIVER)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        Optional<Money> moneyInReceiver = moneyRepository.getLastByMoneyLocationId(moneyInReceiverLocationId);

        if (moneyInReceiver.isEmpty() || moneyInReceiver.get().getAmount().intValue() < coffeeVariant.getPrice()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Money moveMoneyFromReceiverToBank() {
        Long moneyInReceiverLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(RECEIVER)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        Long amountInReceiver = moneyRepository.getLastByMoneyLocationId(moneyInReceiverLocationId)
                .orElseThrow(NoSuchMoneyException::new).getAmount();

        Long amountInBank = moneyRepository.getLastByMoneyLocationId(moneyInBankLocationId)
                .orElseThrow(NoSuchMoneyException::new).getAmount();

        return moneyRepository.create(Money.builder()
                .moneyLocationId(moneyInBankLocationId)
                .amount(amountInBank + amountInReceiver)
                .build());
    }
}
