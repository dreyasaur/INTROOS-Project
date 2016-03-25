//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PerformanceTab extends JPanel implements ActionListener
{
    private JPanel cpuBar = new JPanel(),
                   cpuGraph = new JPanel(),
                   memBar = new JPanel(),
                   memGraph = new JPanel();
    public PerformanceTab()
    {
        //Tab Settings
        setPreferredSize(new Dimension(440, 350));
        setLayout(new FlowLayout(1, 5, 10));
        setBackground(Color.WHITE);
        setVisible(true);
            cpuBar.setPreferredSize(new Dimension(100, 100));
            cpuBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "CPU Usage", 1, 1, UIManager.getFont("Label.font").deriveFont(4)));
            cpuBar.setBackground(Color.LIGHT_GRAY);
            cpuBar.setVisible(true);
        add(cpuBar);
            cpuGraph.setPreferredSize(new Dimension(320, 100));
            cpuGraph.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            cpuGraph.setBackground(Color.LIGHT_GRAY);
            cpuGraph.setVisible(true);
        add(cpuGraph);
            memBar.setPreferredSize(new Dimension(100, 100));
            memBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            memBar.setBackground(Color.LIGHT_GRAY);
            memBar.setVisible(true);
        add(memBar);
            memGraph.setPreferredSize(new Dimension(320, 100));
            memGraph.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            memGraph.setBackground(Color.LIGHT_GRAY);
            memGraph.setVisible(true);
        add(memGraph);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        ;
    }
}
