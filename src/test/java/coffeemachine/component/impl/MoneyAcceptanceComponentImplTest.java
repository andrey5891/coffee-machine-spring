package coffeemachine.component.impl;

import coffeemachine.entity.Money;
import coffeemachine.enumeration.CoffeeVariantEnum;
import coffeemachine.enumeration.MoneyLocationEnum;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MoneyAcceptanceComponentImplTest {

    private static final Long BANK_MONEY_LOCATION_ID = 1L;
    private static final Long RECEIVER_MONEY_LOCATION_ID = 2L;
    public static final Long MONEY_AMOUNT_IN_RECEIVER = 6L;
    public static final Long MONEY_AMOUNT_IN_BANK = 90L;


    @Mock
    private MoneyRepository moneyRepository;

    @Mock
    private MoneyLocationRepository moneyLocationRepository;

    @InjectMocks
    private MoneyAcceptanceComponentImpl moneyAcceptanceComponentImpl;
    Money moneyInReceiver = Money.builder()
            .moneyLocationId(RECEIVER_MONEY_LOCATION_ID)
            .amount(MONEY_AMOUNT_IN_RECEIVER)
            .build();


    Money moneyInBank = Money.builder()
            .moneyLocationId(BANK_MONEY_LOCATION_ID)
            .amount(MONEY_AMOUNT_IN_BANK)
            .build();

    Money moneyForCreateMock = Money.builder()
            .moneyLocationId(BANK_MONEY_LOCATION_ID)
            .amount(MONEY_AMOUNT_IN_RECEIVER + MONEY_AMOUNT_IN_BANK)
            .build();

    @Test
    public void mocksNotNull() {
        assertNotNull(moneyRepository);
        assertNotNull(moneyLocationRepository);
    }

    @Test
    public void moneyInReceiverTest() {
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum.RECEIVER)).thenReturn(Optional.of(RECEIVER_MONEY_LOCATION_ID));
        when(moneyRepository.getLastByMoneyLocationId(RECEIVER_MONEY_LOCATION_ID)).thenReturn(Optional.of(moneyInReceiver));

        assertEquals(true, moneyAcceptanceComponentImpl.isMoneyReceived(CoffeeVariantEnum.CAPPUCCINO));
    }

    @Test
    public void moveMoneyFromReceiverToBankTest() {
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum.RECEIVER)).thenReturn(Optional.of(RECEIVER_MONEY_LOCATION_ID));
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(MoneyLocationEnum.BANK)).thenReturn(Optional.of(BANK_MONEY_LOCATION_ID));

        when(moneyRepository.getLastByMoneyLocationId(RECEIVER_MONEY_LOCATION_ID)).thenReturn(Optional.of(moneyInReceiver));
        when(moneyRepository.getLastByMoneyLocationId(BANK_MONEY_LOCATION_ID)).thenReturn(Optional.of(moneyInBank));

        when(moneyRepository.create(moneyForCreateMock)).thenReturn(moneyForCreateMock);

        assertEquals(MONEY_AMOUNT_IN_BANK + MONEY_AMOUNT_IN_RECEIVER, moneyAcceptanceComponentImpl.moveMoneyFromReceiverToBank().getAmount());
    }
}
