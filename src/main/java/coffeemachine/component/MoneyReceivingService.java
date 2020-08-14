package coffeemachine.component;

public interface MoneyReceivingService {
    Long getAvailableCashAmount();

    void setAvailAbleCashAMountToZero();
}
