package coffeemachine.service;

import coffeemachine.component.CheckSupplyForCoffeeTypeComponent;
import coffeemachine.component.MoneyAcceptanceComponent;
import coffeemachine.component.SupplyManagerComponent;
import coffeemachine.service.impl.BuyCoffeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static coffeemachine.enumeration.CoffeeVariantEnum.CAPPUCCINO;
import static coffeemachine.enumeration.CoffeeVariantEnum.ESPRESSO;
import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuyCoffeeServiceImplTest {
    @Mock
    private CheckSupplyForCoffeeTypeComponent checkSupplyForCoffeeTypeComponent;
    @Mock
    private MoneyAcceptanceComponent moneyAcceptanceComponent;
    @Mock
    private SupplyManagerComponent supplyManagerComponent;

    @InjectMocks
    private BuyCoffeeServiceImpl buyCoffeeServiceImpl;

    private final String OK_MESSAGE = "OK";
    private final String SUCCESS_MESSAGE = "I have enough resources, making you a coffee!";
    private final String NOT_ENOUGH_BEANS = "Sorry, not enough coffee beans!";

    @Test
    public void makeCoffeeTest() {
        when(checkSupplyForCoffeeTypeComponent.checkAvailableSupplyAndGetMessage(CAPPUCCINO)).thenReturn(OK_MESSAGE);
        when(moneyAcceptanceComponent.isMoneyReceived(CAPPUCCINO)).thenReturn(TRUE);

        Assertions.assertEquals(SUCCESS_MESSAGE, buyCoffeeServiceImpl.makeCoffeeIfAvailableAndGetMessage(CAPPUCCINO));

        verify(supplyManagerComponent, times(1)).reduceAllSupply(CAPPUCCINO);
    }

    @Test
    public void makeCoffeesWithoutNeededMilkTest() {
        when(checkSupplyForCoffeeTypeComponent.checkAvailableSupplyAndGetMessage(ESPRESSO)).thenReturn(NOT_ENOUGH_BEANS);
        when(moneyAcceptanceComponent.isMoneyReceived(ESPRESSO)).thenReturn(TRUE);

        Assertions.assertEquals(NOT_ENOUGH_BEANS, buyCoffeeServiceImpl.makeCoffeeIfAvailableAndGetMessage(ESPRESSO));

        verify(supplyManagerComponent, times(0)).reduceAllSupply(ESPRESSO);
    }
}
