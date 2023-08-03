public class Player {
	protected Card[] hand = new Card[5];
	protected int handSize = 0;
	
	private boolean decisionIsDone = false;

	// calculate score of player's hand
	protected int score;
	public int calcScore() {
		score = 0;
		for (int i = 0; i < handSize; i++) {
			score += hand[i].getValue();
		}
		return score;
	}

	// add card to player's hand
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
