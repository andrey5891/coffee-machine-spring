package coffeemachine.component.impl;

import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.entity.Money;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.enumeration.MoneyLocationEnum;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MoneyAcceptanceComponentImpl implements MoneyAcceptanceComponent {

    private final MoneyRepository moneyRepository;
    private final MoneyLocationRepository moneyLocationRepository;

    @Override
    public Boolean isMoneyReceived(CoffeeVariantEnum coffeeVariant) {
        Long moneyInReceiverLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum.RECEIVER).get();
        Optional<Money> moneyInReceiver = moneyRepository.getLastByMoneyLocationId(moneyInReceiverLocationId);

        if (moneyInReceiver.isEmpty() || moneyInReceiver.get().getAmount().intValue() < coffeeVariant.getPrice()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Money moveMoneyFromReceiverToBank() {
        Long moneyInReceiverLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum.RECEIVER).get();
        Long moneyInBankLocationId = moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum.BANK).get();

        Long amountInReceiver = moneyRepository.getLastByMoneyLocationId(moneyInReceiverLocationId).get().getAmount();
        Long amountInBank = moneyRepository.getLastByMoneyLocationId(moneyInBankLocationId).get().getAmount();

        return moneyRepository.create(Money.builder().moneyLocationId(moneyInBankLocationId).amount(amountInBank + amountInReceiver).build());
    }
}
