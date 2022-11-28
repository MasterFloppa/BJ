import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import java.util.concurrent.TimeUnit;

public class BJ {
	private static final int Height = 195;
	private static final int Width = 139;
	// JFrame uiManager.f = new JFrame();
	public static Boolean isGameOver = false;
	public static Boolean newGame = true;
	static Game game = new Game();
	private Money playerMoney = new Money(1000);
	private UIManager uiManager = new UIManager();

	BJ() {
		newGame = false;
		// get bet
		uiManager.getBet(game, playerMoney);

		/*------------------ Setting first two cards. ------------------ */
		// Set first two cards
		setFirstCards();

		// Calculate the initial score for the player
		uiManager.p_Count.setText(Integer.toString(game.player.calcScore()));

		/* hit button */
		uiManager.hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!game.player.getdecisionIsDone()) {
					game.player.addCard(game.deck.giveCard());

					ImageIcon cardIcon = OnGUI.getImageIcon(
							game.player.hand[game.player.handSize - 1].getValueAndSuitAsString(), Width, Height,
							"png");

					uiManager.PLcl[game.player.handSize - 1].setIcon(cardIcon);
					uiManager.p_Count.setText(Integer.toString(game.player.calcScore()));
				} else {
					JOptionPane.showMessageDialog(uiManager.f, "You can't pick more cards now");
				}

				if (!game.aI.getdecisionIsDone()) {
					if (game.aI.aiDecide()) {
						game.aI.addCard(game.deck.giveCard());
						// String s = "Images\\PNG-cards-1.3\\card_bg.jpg";
						uiManager.AIcl[game.aI.handSize - 1]
								.setIcon(OnGUI.getImageIcon("card_bg", Width, Height, "jpg"));
					}
				}

				/*---------Game loop termination---------- */
				if (game.aI.getdecisionIsDone() && game.player.getdecisionIsDone()) {

					// Flip AI's cards
					for (int i = 0; i < 5; i++) {
						if (game.aI.hand[i] != null) {
							String s = game.aI.hand[i].getValueAndSuitAsString();
							ImageIcon cardIcon = OnGUI.getImageIcon(s, Width, Height, "png");
							uiManager.AIcl[i].setIcon(cardIcon);
						}
					}
					uiManager.AI_Count.setText(Integer.toString(game.aI.calcScore()));

					JOptionPane.showMessageDialog(uiManager.f,
							"Your score was " + game.player.calcScore() + " and AI's score was " + game.aI.calcScore());
					game.decideWinner(playerMoney);
					if (isGameOver) {
						JOptionPane.showMessageDialog(uiManager.f, "Game Over");
						System.exit(0);
					} else {

						// TODO: terminate prv loop
						JOptionPane.showMessageDialog(uiManager.f,
								game.getWinner() + " won this round\n" + "You have " + playerMoney.getValue()
										+ " left");

						// String s = JOptionPane.showInputDialog("Do you want to play again?");
						// if (s == "yes" || s == "YES" || s == "Y" || s == "y" || s == "Yes")
						newGame = true;
						// else
						// System.exit(0);
					}
				}
			}
		});
		/*---------------------------------------------------------- */

		/* stand button */
		uiManager.stand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.player.setdecisionIsDone(true);
				JOptionPane.showMessageDialog(uiManager.f, "You can't pick more cards now");

				// AI deciding what to do
				if (!game.aI.getdecisionIsDone()) {

					if (game.aI.aiDecide()) {
						game.aI.addCard(game.deck.giveCard());
						uiManager.AIcl[game.aI.handSize - 1].setIcon(OnGUI.getImageIcon("card_bg", Width, Height, "jpg"));
					}
				}
				/*---------Game loop termination---------- */
				if (game.aI.getdecisionIsDone() && game.player.getdecisionIsDone()) {

					for (int i = 0; i < 5; i++) {
						if (game.aI.hand[i] != null) {
							String s = game.aI.hand[i].getValueAndSuitAsString();
							uiManager.AIcl[i].setIcon(OnGUI.getImageIcon(s, Width, Height, "jpg"));
						}
					}
					uiManager.AI_Count.setText(Integer.toString(game.aI.calcScore()));

					JOptionPane.showMessageDialog(uiManager.f,
							"Your score was " + game.player.calcScore() + " and AI's score was " + game.aI.calcScore());
					game.decideWinner(playerMoney);
					if (isGameOver) {
						JOptionPane.showMessageDialog(uiManager.f, "Game Over, you lost all your money.");
						System.exit(0);
					} else {

						// TODO: terminate prv loop
						JOptionPane.showMessageDialog(uiManager.f,
								game.getWinner() + " won this round\n" + "You have " + playerMoney.getValue()
										+ " left");

						// String s = JOptionPane.showInputDialog("Do you want to play again?");
						// if (s == "yes" || s == "YES" || s == "Y" || s == "y" || s == "Yes")
						newGame = true;
						// else
						// System.exit(0);
					}
				}
			}
		});
		/*---------------------------------------------------------- */
	}

	/**
	 * Sets first 2 cards for player and AI
	 */
	private void setFirstCards() {
		game.dealCards();

		// clearing the GUI
		for (int i = 0; i < 5; i++) {
			uiManager.AIcl[i].setIcon(null);
			uiManager.PLcl[i].setIcon(null);
		}



		ImageIcon cardIcon = OnGUI.getImageIcon(game.player.hand[0].getValueAndSuitAsString(), Width, Height,
				"png");
		uiManager.PLcl[0].setIcon(cardIcon);

		cardIcon = OnGUI.getImageIcon(game.player.hand[1].getValueAndSuitAsString(), Width, Height, "png");
		uiManager.PLcl[1].setIcon(cardIcon);

		uiManager.AIcl[0].setIcon(OnGUI.getImageIcon("card_bg", Width, Height, "jpg"));
		uiManager.AIcl[1].setIcon(OnGUI.getImageIcon("card_bg", Width, Height, "jpg"));

	}

	public static void main(String args[]) {
		new BJ();

		while (!isGameOver) {
			if (newGame) {
				game.resetGame();
			}
		}
	}
}
