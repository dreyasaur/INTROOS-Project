//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainTaskManager extends JFrame
{
    private ImageIcon mainIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\main_icon.png"),
                      descIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\home_icon.png"),
                      processIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\process_icon.png"),
                      performIcon = new ImageIcon("C:\\Users\\alicbusanR\\Documents\\GitHub\\INTROOS-Project\\INTROOS1\\INTROOS\\others\\performance_icon.png");    
    private JPanel menuPane = new JPanel(new FlowLayout(1, 0, 0));      //--Filler-- Top Bar
    private JTabbedPane mainPane = new JTabbedPane(1);                  //Tab Container
    private StatusPane statusPane = new StatusPane();                   //Bottom Bar
    private FileAccess aFile = new FileAccess();
    
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
            mainPane.setPreferredSize(new Dimension(470, 380));
            mainPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            mainPane.setVisible(true);
            mainPane.addTab("Processes", processIcon, new ProcessTab(aFile), "Processes");
            mainPane.addTab("Performance", performIcon, new PerformanceTab(), "Performance");
            mainPane.addTab("Description", descIcon, new DescriptionTab(), "Home");
        add(mainPane);
        //StatusBar
            statusPane.setPreferredSize(new Dimension(470, 25));
            statusPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            statusPane.setBackground(Color.LIGHT_GRAY);
            statusPane.setVisible(true);
        add(statusPane);
    }
}