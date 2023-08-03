public class Money {
    // Initialize player's money to 1000.
    private int value = 1000;

    public int getValue() {
        return value;
    }

    public Money(int value) {
        this.value = value;
    }

    // Add money to the player's money.
    public void updatePlayerMoney(int money) {
        value += money;
    }
}
