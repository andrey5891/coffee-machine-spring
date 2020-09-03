package coffeemachine.repository;

import coffeemachine.entity.Money;
import coffeemachine.exception.NoMoneyAmountInParametersException;
import coffeemachine.repository.impl.MoneyRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyRepositoryImplTest {
    private static final Long BANK_MONEY_LOCATION_ID = 1L;
    private static final Long SOME_AMOUNT_1200 = 1200L;
    private static final Long SOME_AMOUNT_1800 = 1800L;
    private static final Long SOME_AMOUNT_1500 = 1500L;

    private final static MoneyRepository moneyRepository = new MoneyRepositoryImpl(
            new ArrayList<Money>(
                    Arrays.asList(Money.builder()
                            .moneyLocationId(BANK_MONEY_LOCATION_ID)
                            .amount(SOME_AMOUNT_1500)
                            .build())
            ));

    private static Money createdMoney;

    private static Optional<Money> lastMoneyByMoneyLocation;

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

        lastMoneyByMoneyLocation = moneyRepository.getLastByMoneyLocationId(BANK_MONEY_LOCATION_ID);

        assertTrue(lastMoneyByMoneyLocation.isPresent());
        assertEquals(createdMoney, lastMoneyByMoneyLocation.get());
    }
}