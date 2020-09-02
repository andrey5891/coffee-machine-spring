package coffeemachine.component;

import coffeemachine.entity.Money;

public interface MoneyReceivingComponent {
    Long getAvailableCashAmount();

    void setAvailAbleCashAMountToZero();
}
