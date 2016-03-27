//C:\Users\alicbusanR\Documents\GitHub\INTROOS-Project\INTROOS1\INTROOS\src\introos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ProcessTab extends JPanel implements ActionListener
{
    private JPanel tabCon = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));        //Process Table Container
    private JTable processTable = new JTable();                         //Process Tab Table
    private JTextField startField = new JTextField("Open");             //Start Process Text Field
    private JButton refButton = new JButton("Refresh"),                 //Table Refresh Button
                    startButton = new JButton("New Task (Run...)"),     //Start Process Button
                    endButton = new JButton("End Process");             //End Process Button
    private FileAccess aFile;
    
    public ProcessTab(FileAccess file)
    {
        aFile = file;
        //Tab Settings
        setPreferredSize(new Dimension(440, 350));
        setLayout(new FlowLayout(1, 5, 3));
        setBackground(Color.WHITE);
        setVisible(true);
        //ScrollPane Settings
            tabCon.setPreferredSize(new Dimension(430, 300));
            tabCon.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            tabCon.setBackground(Color.LIGHT_GRAY);
            tabCon.setVisible(true);
        add(tabCon);
        //Table Refresh Settings
            refButton.setPreferredSize(new Dimension(110, 20));
            refButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            refButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            refButton.addActionListener(this);
            refButton.setVisible(true);
        add(refButton);
        //Start Process TextField Settings
            startField.setPreferredSize(new Dimension(200, 20));
            startField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            startField.setHorizontalAlignment(JLabel.CENTER);
            startField.setForeground(Color.LIGHT_GRAY);
            startField.setBackground(Color.WHITE);
            startField.setVisible(true);
        add(startField);
        //End Process Settings
            endButton.setPreferredSize(new Dimension(110, 20));
            endButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            endButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            endButton.addActionListener(this);
            endButton.setVisible(true);
        add(endButton);
        //Start Process Button Settings
            startButton.setPreferredSize(new Dimension(200, 20));
            startButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            startButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            startButton.addActionListener(this);
            startButton.setVisible(true);
        add(startButton);
        //Table Auto-Refresh Settings
        tickTock();
       
        Timer timer = new Timer(20000, new ActionListener()
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
    }
    
    public void tickTock()
    {
        tabCon.removeAll();
        processTable = aFile.getList(aFile.getProcess());
        tabCon.add(new JScrollPane(processTable));
        tabCon.getComponent(0).setPreferredSize(new Dimension(430, 300));
        ((JScrollPane)tabCon.getComponent(0)).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tabCon.getComponent(0).setBackground(Color.WHITE);
        tabCon.getComponent(0).setVisible(true);
        tabCon.repaint();
        tabCon.revalidate();
        repaint();
        revalidate();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == endButton)
        {
            if(processTable.getSelectedRow() != -1)
            {
                String item = (String)(processTable.getModel().getValueAt(processTable.getSelectedRow(), processTable.getColumn("PID").getModelIndex()));
                try
                {
                    if(aFile.isProcessRunning(aFile.getProcess(), item))
                    {
                        try
                        {
                            aFile.killProcess(item);
                            tickTock();
                        }
                        catch(Exception err)
                        {
                            err.printStackTrace();
                        }
                    }
                }
                catch(Exception err)
                {
                    err.printStackTrace();
                }
            }
            else
                ;
        }
        
        if(e.getSource() == refButton)
        {
            tickTock();
        }
        
        if(e.getSource() == startButton)
        {
            try
            {
                Process process = new ProcessBuilder("C:\\Windows\\Explorer.exe").start();
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;

                while ((line = br.readLine()) != null)
                {
                    System.out.println(line);
                }
            }
            catch(Exception err)
            {
                err.printStackTrace();
            }
        }
    }
}
