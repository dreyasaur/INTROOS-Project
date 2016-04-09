//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StatusPane extends JPanel
{
    private JLabel processLabel = new JLabel("Processes: "),            //Process Count Display
                   usageLabel = new JLabel("CPU Usage: "),              //CPU Usage Display
                   memoryLabel = new JLabel("Physical Memory: ");       //Physical Memory Display
    private FileAccess aFile = new FileAccess();
    
    public StatusPane()
    {
        setLayout(new FlowLayout(1, 5, 0));
        //Components
            processLabel.setPreferredSize(new Dimension(150, 20));
            processLabel.setBorder(BorderFactory.createMatteBorder(1, 2, 3, 4, Color.DARK_GRAY));
            processLabel.setBackground(Color.LIGHT_GRAY);
            processLabel.setVisible(true);
            processLabel.setOpaque(true);
        add(processLabel);
            usageLabel.setPreferredSize(new Dimension(150, 20));
            usageLabel.setBorder(BorderFactory.createMatteBorder(1, 2, 3, 4, Color.DARK_GRAY));
            usageLabel.setBackground(Color.LIGHT_GRAY);
            usageLabel.setVisible(true);
            usageLabel.setOpaque(true);
        add(usageLabel);
            memoryLabel.setPreferredSize(new Dimension(150, 20));
            memoryLabel.setBorder(BorderFactory.createMatteBorder(1, 2, 3, 4, Color.DARK_GRAY));
            memoryLabel.setBackground(Color.LIGHT_GRAY);
            memoryLabel.setVisible(true);
            memoryLabel.setOpaque(true);
        add(memoryLabel);
        
        //Status Refresh Settings
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
             total = 0,
             cpuLoad = 0;
        double cpuTime = 0,
               sysLoad = 0;
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
                
                //if(level == 4)
                //    cpuload = (double)value;
                //if(level == 5)
                //    cpuTime = (long)value;
                //if(level == 6)
                //    sysLoad = (double)value;
                
                if(level == 2)
                    free = (long)value;
                if(level == 7)
                    total = (long)value;
                
                //System.out.println(method.getName()+": "+value);
            }
        }
        
        memoryLabel.setText("Physical Memory: "+(long)((float)(total - free)/(float)total*100)+"%");
        memoryLabel.revalidate();
        //usageLabel.setText("CPU Usage: "+(long)((float)(cpuTime*cpuLoad*sysLoad)/1000000)+"%");
        //usageLabel.revalidate();
    }
}