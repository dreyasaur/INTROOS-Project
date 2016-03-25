//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;

public class DescriptionTab extends JPanel
{
    private JTextArea display = new JTextArea();
    
    public DescriptionTab()
    {
        //Tab Settings
        setPreferredSize(new Dimension(440, 350));
        setLayout(new FlowLayout(1, 5, 10));
        setBackground(Color.WHITE);
        setVisible(true);
            display.setText("\n\n\nThis is a Process Manager which allows the user to do the following:\n - View Current Processes\n - View Computer Status\n - Start Processes\n - End Processes\n\n------------------------------------\nDeveloped By:\nOlan, Andrea\nPunzalan, Alexander\nTejada, Mima\nVivo, Roi");
            display.setEditable(false);
            display.setPreferredSize(new Dimension(400, 310));
            display.setVisible(true);
        add(display);
    }
}