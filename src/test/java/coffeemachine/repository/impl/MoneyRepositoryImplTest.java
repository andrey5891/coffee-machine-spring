package coffeemachine.repository.impl;

import coffeemachine.entity.Money;
import coffeemachine.exception.NoMoneyAmountInParametersException;
import coffeemachine.repository.MoneyRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyRepositoryImplTest {

    private final static MoneyRepository moneyRepository = new MoneyRepositoryImpl();

    private static Money createdMoney;

    private static Optional<Money> lastMoneyByMoneyLocation;

    private static Long SOME_AMOUNT_1200 = 1200L;
    private static Long SOME_AMOUNT_1800 = 1800L;
    private static Long BANK_MONEY_LOCATION_ID = 1L;
    private static Long WRONG_MONEY_LOCATION_ID = 7L;

    @Test
    void createSuccessful() {
        Money templateMoney = Money
                .builder()
                .moneyLocationId(BANK_MONEY_LOCATION_ID)
                .amount(SOME_AMOUNT_1200)
                .build();

        createdMoney = moneyRepository.create(Money
                .builder()
                .moneyLocationId(BANK_MONEY_LOCATION_ID)
                .amount(SOME_AMOUNT_1200)
                .build()
        );

        assertNotNull(createdMoney.getId());
        assertEquals(templateMoney.getMoneyLocationId(), createdMoney.getMoneyLocationId());
        assertEquals(templateMoney.getAmount(), createdMoney.getAmount());
    }

    @Test()
    void createFailed() {
        assertThrows(NoMoneyAmountInParametersException.class,
                () -> moneyRepository.create(Money
                        .builder()
                        .moneyLocationId(BANK_MONEY_LOCATION_ID)
                        .build())
        );
    }

    @Test
    void getLastByMoneyLocation() {
        createdMoney = moneyRepository.create(Money
                .builder()
                .moneyLocationId(BANK_MONEY_LOCATION_ID)
                .amount(SOME_AMOUNT_1800)
                .build());

        lastMoneyByMoneyLocation = moneyRepository.getLastByMoneyLocation(BANK_MONEY_LOCATION_ID);

        assertTrue(lastMoneyByMoneyLocation.isPresent());
        assertEquals(createdMoney, lastMoneyByMoneyLocation.get());
    }

    @Test
    void getLastByNonexistentMoneyLocation() {
        lastMoneyByMoneyLocation = moneyRepository.getLastByMoneyLocation(WRONG_MONEY_LOCATION_ID);

        assertTrue(lastMoneyByMoneyLocation.isEmpty());
    }
}