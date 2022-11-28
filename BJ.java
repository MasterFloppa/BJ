
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.concurrent.TimeUnit;

public class BJ {
	private static final int Height = 195;
	private static final int Width = 139;
	JFrame f = new JFrame();
	public static Boolean isGameOver = false;
	public static Boolean newGame = true;
	static Game game;
	private Money playerMoney = new Money(1000);

	BJ() {
		newGame = false;
		game = new Game();
		game.dealCards();
		String s; // to fetch address's of images

		JLabel pcl[] = new JLabel[5]; // player card layout
		JLabel AIcl[] = new JLabel[5]; // AI card layout
		JButton hit = new JButton("Hit");
		JButton stand = new JButton("Stand");

		JLabel p_Count = new JLabel("My count"); // player card layout
		JLabel p_Count_txt = new JLabel("Your Score");
		JLabel AI_Count = new JLabel("???"); // AI card layout
		JLabel AI_Count_txt = new JLabel("AI's Score");

		JLabel Pile = new JLabel(); // pile of cards
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images\\PNG-cards-1.3\\card_bg_h.jpg")
				.getImage().getScaledInstance(195, 139, Image.SCALE_DEFAULT));
		Pile.setIcon(imageIcon);

		for (int i = 0; i < 5; i++) {
			pcl[i] = new JLabel("Your Card");
			pcl[i].setBounds(170 + (i * 149), 500, 139, 195);
		}
		for (int i = 0; i < 5; i++) {
			AIcl[i] = new JLabel("AI's Card");
			AIcl[i].setBounds(170 + (i * 149), 30, 139, 195);
		}

		p_Count_txt.setBounds(930, 550, 100, 50);
		p_Count.setBounds(945, 590, 167, 50);
		AI_Count_txt.setBounds(930, 70, 100, 50);
		AI_Count.setBounds(945, 105, 167, 50); // 195 139

		hit.setBounds(400, 450, 80, 30);
		stand.setBounds(600, 450, 80, 30);

		Pile.setBounds(50, 300, 195, 139);

		for (int i = 0; i < 5; i++) {
			f.add(AIcl[i]);
			f.add(pcl[i]);
		}
		f.add(hit);
		f.add(stand);
		f.add(p_Count);
		f.add(p_Count_txt);
		f.add(AI_Count);
		f.add(AI_Count_txt);
		f.add(Pile);

		f.setSize(1100, 800);
		f.setLayout(null);
		f.getContentPane().setBackground(new Color(20, 153, 10));
		f.setVisible(true);
		f.setTitle("Black Jack");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//get bet
		int x = playerMoney.getValue() + 1;
		while (x > playerMoney.getValue() || x <= 0) {
			x = Integer.parseInt(JOptionPane.showInputDialog("You have " + playerMoney.getValue() + "\nEnter your bet"));
			if (x > playerMoney.getValue() || x <= 0) {
				JOptionPane.showMessageDialog(f, "Enter a valid amount");
			}
		}
		game.playerBet(x, playerMoney);
		playerMoney.updatePlayerMoney(-game.getPlayerBet());
		/*------------------------------------ */

		s = String.format("Images\\PNG-cards-1.3\\%s.png",
				game.player.hand[0].getValueAndSuitAsString());
		ImageIcon cardIcon0 = new ImageIcon(
				new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
		pcl[0].setIcon(cardIcon0);
		s = String.format("Images\\PNG-cards-1.3\\%s.png", game.player.hand[1].getValueAndSuitAsString());
		ImageIcon cardIcon1 = new ImageIcon(
				new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
		pcl[1].setIcon(cardIcon1);

		s = "Images\\PNG-cards-1.3\\card_bg.jpg";
		AIcl[0].setIcon(
				new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
		AIcl[1].setIcon(
				new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));

		p_Count.setText(Integer.toString(game.player.calcScore()));

		/* hit button */
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!game.player.getdecisionIsDone()) {
					game.player.addCard(game.deck.giveCard());

					String s = String.format("Images\\PNG-cards-1.3\\%s.png",
							game.player.hand[game.player.handSize - 1].getValueAndSuitAsString());
					ImageIcon cardIcon = new ImageIcon(
							new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));

					pcl[game.player.handSize - 1].setIcon(cardIcon);
					p_Count.setText(Integer.toString(game.player.calcScore()));
				} else {
					JOptionPane.showMessageDialog(f, "You can't pick more cards now");
				}

				if (!game.aI.getdecisionIsDone()) {
					if (game.aI.aiDecide()) {
						game.aI.addCard(game.deck.giveCard());
						String s = "Images\\PNG-cards-1.3\\card_bg.jpg";
						AIcl[game.aI.handSize - 1].setIcon(new ImageIcon(
								new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
					}
				}

				/*---------Game loop termination---------- */
				if (game.aI.getdecisionIsDone() && game.player.getdecisionIsDone()) {

					for (int i = 0; i < 5; i++) {
						if (game.aI.hand[i] != null) {
							String s = String.format("Images\\PNG-cards-1.3\\%s.png",
									game.aI.hand[i].getValueAndSuitAsString());
							ImageIcon cardIcon = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(Width,
									Height, Image.SCALE_DEFAULT));
							AIcl[i].setIcon(cardIcon);
						}
					}
					AI_Count.setText(Integer.toString(game.aI.calcScore()));

					JOptionPane.showMessageDialog(f,
							"Your score was " + game.player.calcScore() + " and AI's score was " + game.aI.calcScore());
					game.decideWinner(playerMoney);
					if (isGameOver) {
						JOptionPane.showMessageDialog(f, "Game Over");
						System.exit(0);
					} else {

						// TODO: terminate prv loop
						JOptionPane.showMessageDialog(f,
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
		stand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.player.setdecisionIsDone(true);
				JOptionPane.showMessageDialog(f, "You can't pick more cards now");

				// AI deciding what to do
				if (!game.aI.getdecisionIsDone()) {

					if (game.aI.aiDecide()) {
						game.aI.addCard(game.deck.giveCard());
						String s = "Images\\PNG-cards-1.3\\card_bg.jpg";
						AIcl[game.aI.handSize - 1].setIcon(new ImageIcon(
								new ImageIcon(s).getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT)));
					}
				}
				/*---------Game loop termination---------- */
				if (game.aI.getdecisionIsDone() && game.player.getdecisionIsDone()) {

					for (int i = 0; i < 5; i++) {
						if (game.aI.hand[i] != null) {
							String s = String.format("Images\\PNG-cards-1.3\\%s.png",
									game.aI.hand[i].getValueAndSuitAsString());
							ImageIcon cardIcon = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(Width,
									Height, Image.SCALE_DEFAULT));
							AIcl[i].setIcon(cardIcon);
						}
					}
					AI_Count.setText(Integer.toString(game.aI.calcScore()));

					JOptionPane.showMessageDialog(f,
							"Your score was " + game.player.calcScore() + " and AI's score was " + game.aI.calcScore());
					game.decideWinner(playerMoney);
					if (isGameOver) {
						JOptionPane.showMessageDialog(f, "Game Over, you lost all your money.");
						System.exit(0);
					} else {

						// TODO: terminate prv loop
						JOptionPane.showMessageDialog(f,
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

	public static void main(String args[]) {
		new BJ();

		while (!isGameOver)
			if (newGame) 
				game.resetGame();
	}
}
