public class Card {
	private final int suit, value;

	public Card(int suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	public int getValue() {
		if (value > 9 && value <= 13)
			return 10;
		else
			return value;
	}

	public String getValueAsString() {
		switch (value) {
			case (1):
				return "ace";
			case (2):
				return "2";
			case (3):
				return "3";
			case (4):
				return "4";
			case (5):
				return "5";
			case (6):
				return "6";
			case (7):
				return "7";
			case (8):
				return "8";
			case (9):
				return "9";
			case (10):
				return "10";
			case (11):
				return "jack";
			case (12):
				return "queen";
			case (13): // original game has jack=queen=king=10
				return "king";
		}
		return null;
	}

	public int getSuit() {
		return suit;
	}

	public String getSuitAsString() {
		switch (suit) {
			case (1):
				return "hearts";
			case (2):
				return "spades";
			case (3):
				return "diamonds";
			default:
				return "clubs";
		}
	}

	public String getValueAndSuitAsString() {
		return getValueAsString() + "_of_" + getSuitAsString();
	}
}