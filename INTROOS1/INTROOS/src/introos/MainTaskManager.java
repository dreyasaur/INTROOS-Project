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
    private int processCount = 0,                                       //Number of Processes
                usageCount = 0,                                         //CPU Usage Percentage
                memoryCount = 0;                                        //Physical Memory Percentage
    private JPanel statusPane = new JPanel(new FlowLayout(1, 5, 0));    //Bottom Bar
    private JLabel processLabel = new JLabel("Processes: "),            //Process Count Display
                   usageLabel = new JLabel("CPU Usage: "),              //CPU Usage Display
                   memoryLabel = new JLabel("Physical Memory: ");       //Physical Memory Display
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
                processLabel.setPreferredSize(new Dimension(150, 20));
                processLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                processLabel.setBackground(Color.LIGHT_GRAY);
                processLabel.setVisible(true);
                processLabel.setOpaque(true);
            statusPane.add(processLabel);
                usageLabel.setPreferredSize(new Dimension(150, 20));
                usageLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                usageLabel.setBackground(Color.LIGHT_GRAY);
                usageLabel.setVisible(true);
                usageLabel.setOpaque(true);
            statusPane.add(usageLabel);
                memoryLabel.setPreferredSize(new Dimension(150, 20));
                memoryLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                memoryLabel.setBackground(Color.LIGHT_GRAY);
                memoryLabel.setVisible(true);
                memoryLabel.setOpaque(true);
            statusPane.add(memoryLabel);
        add(statusPane);
        //Table Refresh Settings
        tickTock();
       
        Timer timer = new Timer(1000, new ActionListener()
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
        //Refresh
        revalidate();
    }
    
    //Refreshes Bottom Bar (Status Bar)
    public void tickTock()
    {
        processLabel.setText("Processes: "+aFile.getRowCount(aFile.getProcess()));
        processLabel.revalidate();
        
        OperatingSystemMXBean osmxbn = ManagementFactory.getOperatingSystemMXBean();
        long free = 0,
             total = 0;
        int level = 0;
        
        for (Method method : osmxbn.getClass().getDeclaredMethods())
        {
            method.setAccessible(true);
            if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers()))
            {   
                Object value;
                try
                {
                    value = method.invoke(osmxbn);
                    level++;
                }
                catch (Exception e)
                {
                    value = e;
                }
                if(level == 2)
                    free = (long)value;
                if(level == 7)
                    total = (long)value;
            }
        }
        
        memoryLabel.setText("Physical Memory: "+(long)((float)(total - free)/(float)total*100)+"%");
        memoryLabel.revalidate();
    }
}