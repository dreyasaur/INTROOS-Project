//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;

public class MainTaskManager extends JFrame
{
    private ImageIcon mainIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\main_icon.png"),
                      descIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\home_icon.png"),
                      processIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\process_icon.png"),
                      performIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\performance_icon.png");    
    private JPanel menuPane = new JPanel(new FlowLayout(1, 0, 0));      //--Filler-- Top Bar
    private JTabbedPane mainPane = new JTabbedPane(1);                   //Tab Container
    private int processCount = 0,                                       //Number of Processes
                usageCount = 0,                                         //CPU Usage Percentage
                memoryCount = 0;                                        //Physical Memory Percentage
    private JPanel statusPane = new JPanel(new FlowLayout(1, 0, 0));    //Bottom Bar
    private JLabel processLabel = new JLabel(),                         //Process Count Display
                   usageLabel = new JLabel(),                           //CPU Usage Display
                   memoryLabel = new JLabel();                          //Physical Memory Display
 
    public MainTaskManager()
    {
        
        //Frame Settings
        setTitle("Task Manager");
        setSize(470, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(mainIcon.getImage());
        setLayout(new FlowLayout(1, 0, 0));
        setResizable(false);
        setVisible(true);
        setLocation(0, 0);
        //Top Bar
            menuPane.setPreferredSize(new Dimension(470, 20));
            menuPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            menuPane.setBackground(Color.LIGHT_GRAY);
            menuPane.setVisible(true);
        add(menuPane);
        //Tab Container
            mainPane.setPreferredSize(new Dimension(470, 385));
            mainPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            mainPane.setVisible(true);
            mainPane.addTab("Processes", processIcon, new ProcessTab(), "Processes");
            mainPane.addTab("Performance", performIcon, new PerformanceTab(), "Performance");
            mainPane.addTab("Description", descIcon, new DescriptionTab(), "Home");
        add(mainPane);
        //StatusBar
            statusPane.setPreferredSize(new Dimension(470, 20));
            statusPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            statusPane.setBackground(Color.LIGHT_GRAY);
            statusPane.setVisible(true);
        add(statusPane);
        
        //Refresh
        revalidate();
    }
}
