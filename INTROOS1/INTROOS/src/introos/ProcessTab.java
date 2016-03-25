//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProcessTab extends JPanel implements ActionListener
{
    private JTable processTable = new JTable();                         //Process Tab Table
    private JScrollPane processScroll;  //Process Tab Scroll Pane
    private JTextField startField = new JTextField("Open");             //Start Process Text Field
    private JButton startButton = new JButton("Start Process"),         //Start Process Button
                    endButton = new JButton("End Process");             //End Process Button
    private FileAccess aFile = new FileAccess();
    public ProcessTab()
    {
        //Tab Settings
        setPreferredSize(new Dimension(440, 350));
        setLayout(new FlowLayout(1, 5, 10));
        setBackground(Color.WHITE);
        setVisible(true);
        //ScrollPane Settings
                processTable = aFile.getList();
                processTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            processScroll = new JScrollPane(processTable);
            processScroll.setPreferredSize(new Dimension(430, 300));
            processScroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            processScroll.setBackground(Color.LIGHT_GRAY);
            processScroll.setVisible(true);
        add(processScroll);
        //Table Settings
            tickTock();
            
            Timer timer = new Timer(500, new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    tickTock();
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            timer.start();
        //Start Process Settings
            startField.setPreferredSize(new Dimension(200, 20));
            startField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            startField.setBackground(Color.WHITE);
            startField.setVisible(true);
        add(startField);
            startButton.setPreferredSize(new Dimension(110, 20));
            startButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            startButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            startButton.addActionListener(this);
            startButton.setVisible(true);
        add(startButton);
        //End Process Settings
            endButton.setPreferredSize(new Dimension(110, 20));
            endButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            endButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            endButton.addActionListener(this);
            endButton.setVisible(true);
        add(endButton);
    }
    
    public void tickTock()
    {
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startButton)
        {
            ;
        }
    }
}
