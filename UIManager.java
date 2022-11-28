import javax.swing.*;
import java.awt.*;

public class UIManager {
	public JFrame f = new JFrame();
	public String s; // to fetch address's of images

	public JLabel PLcl[] = new JLabel[5]; // player card layout
	public JLabel AIcl[] = new JLabel[5]; // AI card layout
	public JButton hit = new JButton("Hit");
	public JButton stand = new JButton("Stand");

	public JLabel p_Count = new JLabel("My count"); // player card layout
	public JLabel p_Count_txt = new JLabel("Your Score");
	public JLabel AI_Count = new JLabel("???"); // AI card layout
	public JLabel AI_Count_txt = new JLabel("AI's Score");

	public JLabel Pile = new JLabel(); // pile of cards
	public ImageIcon imageIcon = OnGUI.getImageIcon("card_bg_h", 195, 139, "jpg");

	public UIManager() {
		setBoundsLabels();
		initGUI();
	}

	/**
	 * Sets the image, bounds, and labels of the card.
	 */
	public void setBoundsLabels() {
		for (int i = 0; i < 5; i++) {
			PLcl[i] = new JLabel("Your Card");
			PLcl[i].setBounds(170 + (i * 149), 500, 139, 195);
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
	}

	public void initGUI() {
		for (int i = 0; i < 5; i++) {
			f.add(AIcl[i]);
			f.add(PLcl[i]);
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
	}

	/**
	 * Get bet.
	 * 
	 * @param game        The game object.
	 * @param playerMoney The player's money.
	 */
	public void getBet(Game game, Money playerMoney) {
		int x = playerMoney.getValue() + 1;
		while (x > playerMoney.getValue() || x <= 0) {
			x = OnGUI.getBet(playerMoney);
			if (x > playerMoney.getValue() || x <= 0) {
				JOptionPane.showMessageDialog(f, "Enter a valid amount");
			}
		}
		game.playerBet(x, playerMoney);
	}
}