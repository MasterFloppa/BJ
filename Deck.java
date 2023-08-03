class Deck 
{
	private Card[] deck;
	private int cardsUsed;

	private int card_number=0;
	Deck() 
	{
		deck = new Card[52];
		for (int i = 1; i<=4; i++) 					// i-> suit
		{
			for (int j = 1; j < 14; j++) 			//-> j value
			{
				deck[card_number++] = new Card(i, j);
			}
		}
	}

	public void Shuffle()
	{
		for(int i=0; i<deck.length; i++)
		{
			int r = (int)(Math.random() * 13) + 1;
			Card temp=deck[i];
			deck[i]=deck[r];
			deck[r]=temp;
		}
	}

	public Card giveCard()
	{
		if(cardsUsed==52)
			throw new IllegalArgumentException("No cards left in the deck."); 		//can be changed to start new deck
		else
			return deck[cardsUsed++];
	}
}


