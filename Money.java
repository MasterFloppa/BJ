public class Money {
    private int value = 1000;

    /*
     * Constructor.
     */
    public int getValue() {
        return value;
    }
    /*
     * Constructor with value.
     */
    public Money(int value) {
        this.value = value;
    }

    /*
     * Add money to the player's money.
     */
    public void updatePlayerMoney(int money) {
        value += money;
    }
}
