package coffeemachine.component;

import coffeemachine.component.impl.MoneyReceivingComponentImpl;
import coffeemachine.entity.Money;
import coffeemachine.repository.MoneyLocationRepository;
import coffeemachine.repository.MoneyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static coffeemachine.enumeration.MoneyLocationEnum.BANK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoneyReceivingComponentImplTest {
    @Mock
    private MoneyRepository moneyRepository;

    @Mock
    private MoneyLocationRepository moneyLocationRepository;

    @InjectMocks
    private MoneyReceivingComponentImpl moneyReceivingComponentImpl;

    public static final Long BANK_MONEY_LOCATION_ID = 1L;
    public static final Long MONEY_AMOUNT_IN_BANK = 500L;
    public static final long ZERO_VALUE = 0L;

    public Money moneyInBank = Money.builder()
            .moneyLocationId(BANK_MONEY_LOCATION_ID)
            .amount(MONEY_AMOUNT_IN_BANK)
            .build();

    public Money moneyInBankAfterSettingZero = Money.builder()
            .moneyLocationId(BANK_MONEY_LOCATION_ID)
            .amount(ZERO_VALUE)
            .build();

    @Test
    public void mocksNotNull() {
        assertNotNull(moneyRepository);
        assertNotNull(moneyLocationRepository);
    }

    @Test
    public void getAvailableCashAmountTest() {
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK))
                .thenReturn(Optional.of(BANK_MONEY_LOCATION_ID));

        when(moneyRepository.getLastByMoneyLocationId(BANK_MONEY_LOCATION_ID))
                .thenReturn(Optional.of(moneyInBank));

        assertEquals(MONEY_AMOUNT_IN_BANK, moneyReceivingComponentImpl.getAvailableCashAmount());
    }

    @Test
    public void setAvailAbleCashAMountToZeroTest() {
        when(moneyLocationRepository.getMoneyLocationIdByMoneyLocationEnum(BANK))
                .thenReturn(Optional.of(BANK_MONEY_LOCATION_ID));

        when(moneyRepository.create(moneyInBankAfterSettingZero))
                .thenReturn(moneyInBankAfterSettingZero);

        moneyReceivingComponentImpl.setAvailAbleCashAMountToZero();

        verify(moneyRepository, times(1)).create(moneyInBankAfterSettingZero);
    }
}
