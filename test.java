
import javax.swing.*;
import java.awt.*;

public class test 
{
	JFrame f = new JFrame();

	test()
	{
	f.getContentPane().setBackground(new Color(20,153,10));
	f.setSize(1200,800);
	f.setVisible(true);
	f.setTitle("Test");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public static void main(String args[]) 
	{
		Game game = new Game();

		game.play();
	}

	
}
