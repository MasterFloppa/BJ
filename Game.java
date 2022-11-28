
//Black Jack Main Game Class
public class Game {
	// Game Variables
	public Deck deck = new Deck();

	public Player player = new Player();
	public AI aI = new AI();

	private int playerScore = 0;
	private int aiScore = 0;

	private int playerBet = 0;

	private int winner = 0; // 1-player 2-AI

	// game constructor
	public Game() {
		deck.Shuffle();
	}

	/**
	 * Game Reset
	 */
	public void resetGame() {
		player = new Player();
		aI = new AI();
		playerScore = 0;
		aiScore = 0;
		playerBet = 0;
		winner = 0;
		
	}

	/**
	 * Dealing of cards to player and AI.
	 */
	public void dealCards() {
		player.addCard(deck.giveCard());
		player.addCard(deck.giveCard());
		aI.addCard(deck.giveCard());
		aI.addCard(deck.giveCard());
	}

	//get winner
	public String getWinner()
	{
		if (winner == 1)
			return "You";
		else if (winner == 2)
			return "AI";
		return null;
	}

	
	/**
	 * set player's bet.
	 * @param bet - player's bet which is deducted from player's money.
	 * @param money - player's money object.
	 */
	public void playerBet(int bet, Money money) {
		playerBet = bet;
		money.updatePlayerMoney(-bet);
	}

	// get player's bet
	public int getPlayerBet() {
		return playerBet;
	}

	// get pot
	public int getPot() {
		return playerBet * 2;
	}

	// get Player's Scores
	public int playerScore() {
		playerScore = player.hand[0].getValue() + player.hand[1].getValue();
		return playerScore;
	}

	// get AI's Scores
	public int aiScore() {
		aiScore = aI.hand[0].getValue() + aI.hand[1].getValue();
		return aiScore;
	}

	// get Player's Hand
	public Card[] playerHand() {
		return player.hand;
	}

	// get AI's Hand
	public Card[] aiHand() {
		return aI.hand;
	}

	/**
	 * Compare scores to determine winner.
	 * @param money - player's money object.
	 */
	public void decideWinner(Money money) {
		aiScore = aI.calcScore();
		playerScore = player.calcScore();
		if ((aiScore > 21) && (playerScore > 21)) {
			if (aiScore > playerScore) {
				winner = 1;
				money.updatePlayerMoney(getPot());
			} else {
				winner = 2;
				if (money.getValue() == 0)
					BJ.isGameOver = true;
			}
		}

		else if ((aiScore <= 21) && (playerScore > 21)) {
			winner = 2;
			if (money.getValue() == 0)
				BJ.isGameOver = true;
		}

		else if ((aiScore > 21) && (playerScore <= 21)) {
			winner = 1;
			money.updatePlayerMoney(getPot());
		}

		else {
			int a = 21 - aiScore;
			int b = 21 - playerScore;

			if (a > b) {
				winner = 1;
				money.updatePlayerMoney(getPot());
			} else {
				winner = 2;
				if (money.getValue() == 0)
					BJ.isGameOver = true;
			}
		}
	}
}