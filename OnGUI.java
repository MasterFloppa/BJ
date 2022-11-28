import javax.swing.*;
import java.awt.*;

public class OnGUI 
{
    public static enum imgExt {png, jpg}
    
    public static int getBet(Money playerMoney) {
        int x = Integer
                .parseInt(JOptionPane.showInputDialog("You have " + playerMoney.getValue() + "\nEnter your bet"));
        return x;
    }

    public static ImageIcon getImageIcon(String image, int pxWidth, int pxHeight, String format)
    {
        String location = String.format("Images\\PNG-cards-1.3\\%s.%s", image, format);
        return new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(pxWidth, pxHeight, Image.SCALE_SMOOTH));
    }


}
