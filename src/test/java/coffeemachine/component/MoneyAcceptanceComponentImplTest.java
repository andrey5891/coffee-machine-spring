package coffeemachine.component;

import coffeemachine.component.impl.MoneyAcceptanceComponentImpl;
import coffeemachine.entity.Money;
import coffeemachine.entity.MoneyLocation;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static coffeemachine.enumeration.MoneyLocationEnum.BANK;
import static coffeemachine.enumeration.MoneyLocationEnum.RECEIVER;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoneyAcceptanceComponentImplTest {
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

    private static final Long BANK_MONEY_LOCATION_ID = 1L;
    private static final Long RECEIVER_MONEY_LOCATION_ID = 2L;
    public static final Long MONEY_AMOUNT_IN_RECEIVER = 6L;
    public static final Long MONEY_AMOUNT_IN_BANK = 90L;
    private static final String CAPPUCCINO = "CAPPUCCINO";

    private static final MoneyLocation RECEIVER_MONEY_LOCATION = MoneyLocation.builder()
            .id(RECEIVER_MONEY_LOCATION_ID)
            .name(RECEIVER.getName())
            .build();

    private static final MoneyLocation BANK_MONEY_LOCATION = MoneyLocation.builder()
            .id(BANK_MONEY_LOCATION_ID)
            .name(BANK.getName())
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
    public void moneyInReceiverTest() {
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(RECEIVER))
                .thenReturn(Optional.of(RECEIVER_MONEY_LOCATION));

        when(moneyRepository.getLastByMoneyLocationId(RECEIVER_MONEY_LOCATION_ID))
                .thenReturn(Optional.of(moneyInReceiver));

        assertEquals(TRUE, moneyAcceptanceComponentImpl.isMoneyReceived(CAPPUCCINO));
    }

    @Test
    public void moveMoneyFromReceiverToBankTest() {
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(RECEIVER))
                .thenReturn(Optional.of(RECEIVER_MONEY_LOCATION));

        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK))
                .thenReturn(Optional.of(BANK_MONEY_LOCATION));

        when(moneyRepository.getLastByMoneyLocationId(RECEIVER_MONEY_LOCATION_ID))
                .thenReturn(Optional.of(moneyInReceiver));

        when(moneyRepository.getLastByMoneyLocationId(BANK_MONEY_LOCATION_ID))
                .thenReturn(Optional.of(moneyInBank));

        when(moneyRepository.create(moneyForCreateMock))
                .thenReturn(moneyForCreateMock);

        moneyAcceptanceComponentImpl.moveMoneyFromReceiverToBank();

        verify(moneyLocationRepository, times(2)).getMoneyLocationIdByMoneyLocationEnum(any());
        verify(moneyRepository, times(2)).getLastByMoneyLocationId(any());
        verify(moneyRepository, times(2)).create(any());
    }
}
