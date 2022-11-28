public class Player {
	protected Card[] hand = new Card[5];
	protected int handSize;
	protected int score;
	private boolean decisionIsDone = false;

	private int playerMoney = 1000;

	// get playerMoney
	public int getPlayerMoney() {
		return playerMoney;
	}

	// Update Player's Money
	public void updatePlayerMoney(int money) {
		playerMoney += money;
	}

	// calculate score of player's hand
	public int calcScore() {
		score = 0;
		for (int i = 0; i < handSize; i++) {
			score += hand[i].getValue();
		}
		return score;
	}

	public void addCard(Card card) {
		hand[handSize++] = card;
		if (calcScore() >= 21)
			decisionIsDone = true;
	}

	public boolean getdecisionIsDone() {
		return decisionIsDone;
	}

	public void setdecisionIsDone(boolean decisionIsDone) {
		this.decisionIsDone = decisionIsDone;
	}
}
