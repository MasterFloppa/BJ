import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import java.util.concurrent.TimeUnit;

public class BJ {
	private static final int Height = 195;
	private static final int Width = 139;
	public static Boolean isGameOver = false;
	static Game game = new Game();
	private Money playerMoney = new Money(1000);
	private UIManager uiManager = new UIManager();

	BJ() {
		update();
	}

	public void update() {
		// Clear the cards
		clearCards();
		uiManager.AI_Count.setText("AI's Score:" + "\n???");
		uiManager.p_Count.setText("Your Score: \n");

		// Get bet
		uiManager.getBet(game, playerMoney);

		/*------------------ Setting first two cards. ------------------ */
		// Set first two cards
		setFirstCards();

		// Calculate the initial score for the player
		uiManager.p_Count.setText("Your Score: \n" + game.player.calcScore());

		/* hit button */
		uiManager.hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!game.player.getdecisionIsDone()) {
					game.player.addCard(game.deck.giveCard());

					ImageIcon cardIcon = getImageIcon(
							game.player.hand[game.player.handSize - 1].getValueAndSuitAsString(), Width, Height,
							"png");

					uiManager.PLcl[game.player.handSize - 1].setIcon(cardIcon);
					uiManager.p_Count.setText("Your Score: \n" + game.player.calcScore());
				} else {
					JOptionPane.showMessageDialog(uiManager.frame, "You can't pick more cards now");
				}

				if (!game.aI.getdecisionIsDone()) {
					if (game.aI.aiDecide()) {
						game.aI.addCard(game.deck.giveCard());
						uiManager.AIcl[game.aI.handSize - 1]
								.setIcon(getImageIcon("card_bg", Width, Height, "jpg"));
					}
				}

				/*---------Game loop termination---------- */
				if (game.aI.getdecisionIsDone() && game.player.getdecisionIsDone()) 
					terminator();
			}
		});
		/*---------------------------------------------------------- */

		/* stand button */
		uiManager.stand.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				game.player.setdecisionIsDone(true);
				JOptionPane.showMessageDialog(uiManager.frame, "You can't pick more cards now");

				// AI deciding what to do
				if (!game.aI.getdecisionIsDone()) {

					if (game.aI.aiDecide()) {
						game.aI.addCard(game.deck.giveCard());
						uiManager.AIcl[game.aI.handSize - 1]
								.setIcon(getImageIcon("card_bg", Width, Height, "jpg"));
					}
				}
				/*---------Game loop termination---------- */
				if (game.aI.getdecisionIsDone() && game.player.getdecisionIsDone())
					terminator();
			}
		});
		/*---------------------------------------------------------- */
	}

	// Gets image icon from the file
	public static ImageIcon getImageIcon(String image, int pxWidth, int pxHeight, String format) {
		String location = String.format("Images\\PNG-cards-1.3\\%s.%s", image, format);
		return new ImageIcon(
				new ImageIcon(location).getImage().getScaledInstance(pxWidth, pxHeight, Image.SCALE_SMOOTH));
	}

	/**
	 * Sets first 2 cards for player and AI
	 */
	private void setFirstCards() {
		// Dealing the cards
		game.dealCards();

		ImageIcon cardIcon = getImageIcon(game.player.hand[0].getValueAndSuitAsString(), Width, Height,
				"png");
		uiManager.PLcl[0].setIcon(cardIcon);

		cardIcon = getImageIcon(game.player.hand[1].getValueAndSuitAsString(), Width, Height, "png");
		uiManager.PLcl[1].setIcon(cardIcon);

		uiManager.AIcl[0].setIcon(getImageIcon("card_bg", Width, Height, "jpg"));
		uiManager.AIcl[1].setIcon(getImageIcon("card_bg", Width, Height, "jpg"));
	}

	// Clears the cards from the table
	private void clearCards() {
		for (int i = 0; i < 5; i++) {
			uiManager.AIcl[i].setIcon(null);
			uiManager.PLcl[i].setIcon(null);
		}
	}

	private void terminator() {
		// Flip AI's cards
		for (int i = 0; i < 5; i++) {
			if (game.aI.hand[i] != null) {
				String s = game.aI.hand[i].getValueAndSuitAsString();
				ImageIcon cardIcon = getImageIcon(s, Width, Height, "png");
				uiManager.AIcl[i].setIcon(cardIcon);
			}
		}
		uiManager.AI_Count.setText(Integer.toString(game.aI.calcScore()));

		JOptionPane.showMessageDialog(uiManager.frame,
				"Your score was " + game.player.calcScore() + " and AI's score was " + game.aI.calcScore());
		game.decideWinner(playerMoney);
		if (isGameOver) {
			JOptionPane.showMessageDialog(uiManager.frame, "Game Over");
			System.exit(0);
		} else {

			// TODO: terminate prv loop
			JOptionPane.showMessageDialog(uiManager.frame,
					game.getWinner() + " won this round\n" + "You have " + playerMoney.getValue()
							+ " left");

			int choice = JOptionPane.showConfirmDialog(uiManager.frame, "Do you want to play again?",
					"Play again?", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				game.resetGame();
				uiManager.frame.setVisible(false);
				uiManager = new UIManager();
				update();
			} else
				System.exit(0);
		}
	}

	public static void main(String args[]) {
		new BJ();
	}
}
