import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class UIManager {
	public JFrame frame = new JFrame();
	public String s; // to fetch address's of images

	public JLabel PLcl[] = new JLabel[5]; // player card layout
	public JLabel AIcl[] = new JLabel[5]; // AI card layout
	public JButton hit = new JButton("Hit");
	public JButton stand = new JButton("Stand");

	public JLabel p_Count = new JLabel("Your Score: ");
	public JLabel AI_Count = new JLabel("AI's Score:" + "\n ???");

	Font font = new FontUIResource("Town 10 Display Medium", Font.PLAIN, 16);

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
			PLcl[i].setFont(font);
			PLcl[i].setForeground(Color.WHITE);
		}
		for (int i = 0; i < 5; i++) {
			AIcl[i] = new JLabel("AI's Card");
			AIcl[i].setBounds(170 + (i * 149), 30, 139, 195);
			AIcl[i].setFont(font);
			AIcl[i].setForeground(Color.WHITE);
		}

		p_Count.setBounds(945, 570, 167, 50);
		p_Count.setFont(new FontUIResource("Town 10 Display Medium", Font.PLAIN, 20));
		p_Count.setForeground(Color.WHITE);
		AI_Count.setBounds(945, 100, 167, 50); // 195 139
		AI_Count.setFont(new FontUIResource("Town 10 Display Medium", Font.PLAIN, 20));
		AI_Count.setForeground(Color.WHITE);

		hit.setBounds(400, 450, 80, 30);
		stand.setBounds(600, 450, 80, 30);
	}

	public void initGUI() {
		for (int i = 0; i < 5; i++) {
			frame.add(AIcl[i]);
			frame.add(PLcl[i]);
		}
		frame.add(hit);
		frame.add(stand);
		frame.add(p_Count);
		frame.add(AI_Count);

		frame.setSize(1280, 800);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(20, 153, 10));
		frame.setVisible(true);
		frame.setTitle("Black Jack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Get bet popup.
	 * 
	 * @param game        - The game object.
	 * @param playerMoney - The player's money.
	 */
	public void getBet(Game game, Money playerMoney) {
		int x = playerMoney.getValue() + 1;
		while (x > playerMoney.getValue() || x <= 0) {
			x = Integer
					.parseInt(JOptionPane.showInputDialog("You have " + playerMoney.getValue() + "$\nEnter your bet"));
			if (x > playerMoney.getValue() || x <= 0) {
				JOptionPane.showMessageDialog(frame, "Enter a valid amount");
			}
		}
		game.playerBet(x, playerMoney);
	}
}