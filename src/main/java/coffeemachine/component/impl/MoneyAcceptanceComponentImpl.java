package coffeemachine.component.impl;

import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.entity.Money;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.exception.NoSuchMoneyException;
import coffeemachine.exception.NoSuchMoneyLocationTypeException;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static coffeemachine.enumeration.MoneyLocationEnum.*;

@Component
@RequiredArgsConstructor
public class MoneyAcceptanceComponentImpl implements MoneyAcceptanceComponent {
    private final MoneyRepository moneyRepository;
    private final MoneyLocationRepository moneyLocationRepository;
    private final Long ZERO_CASH = 0L;

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
    public void moveMoneyFromReceiverToBank() {
        Long moneyInReceiverLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(RECEIVER)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK)
                .orElseThrow(NoSuchMoneyLocationTypeException::new);

        Long amountInReceiver = moneyRepository.getLastByMoneyLocationId(moneyInReceiverLocationId)
                .orElseThrow(NoSuchMoneyException::new).getAmount();

        Long amountInBank = moneyRepository.getLastByMoneyLocationId(moneyInBankLocationId)
                .orElseThrow(NoSuchMoneyException::new).getAmount();

        moneyRepository.create(Money.builder()
                .moneyLocationId(moneyInBankLocationId)
                .amount(amountInBank + amountInReceiver)
                .build());

        moneyRepository.create(Money.builder()
                .moneyLocationId(moneyInReceiverLocationId)
                .amount(ZERO_CASH)
                .build());
    }
}
