public class Money {
    private int value = 1000;

    public int getValue() {
        return value;
    }

    public void updatePlayerMoney(int money) {
        value += money;
    }
}
