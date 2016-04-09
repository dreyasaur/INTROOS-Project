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
                    startButton = new JButton("New Task (Run...)"),                      //Start Process Button
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
            tabCon.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.LIGHT_GRAY));
            tabCon.setLayout(new FlowLayout(1, 2, 2));
            tabCon.setBackground(Color.LIGHT_GRAY);
            tabCon.setVisible(true);
        add(tabCon);
        //Table Refresh Button Settings
            refButton.setPreferredSize(new Dimension(110, 20));
            refButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            refButton.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.LIGHT_GRAY));
            refButton.addActionListener(this);
            refButton.setVisible(true);
        add(refButton);
        //Start Process TextField Settings
            startField.setPreferredSize(new Dimension(200, 20));
            startField.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.LIGHT_GRAY));
            startField.setHorizontalAlignment(JLabel.CENTER);
            startField.addFocusListener(new FocusListener()
            {
                public void focusLost(FocusEvent e)
                {
                    if(startField.getText().isEmpty())
                    {
                        startField.setText("Open");
                        startField.setForeground(Color.LIGHT_GRAY);
                    }
                }

                public void focusGained(FocusEvent e)
                {
                    if(startField.getText().equals("Open"))
                    {
                        startField.setText("");
                        startField.setForeground(Color.BLACK);
                    }
                }
            });
            startField.setForeground(Color.LIGHT_GRAY);
            startField.setBackground(Color.WHITE);
            startField.addActionListener(this);
            startField.setVisible(true);
        add(startField);
        //End Process Button Settings
            endButton.setPreferredSize(new Dimension(110, 20));
            endButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            endButton.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.LIGHT_GRAY));
            endButton.addActionListener(this);
            endButton.setVisible(true);
        add(endButton);
        //Start Process Button Settings
            startButton.setPreferredSize(new Dimension(200, 20));
            startButton.setFont(UIManager.getFont("Label.font").deriveFont(4));
            startButton.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.LIGHT_GRAY));
            startButton.addActionListener(this);
            startButton.setVisible(true);
        add(startButton);
        //Table Auto-Refresh Settings
        Timer timer = new Timer(30000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tickTock();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        tickTock();
        //timer.start();
    }
    
    public void tickTock()
    {
        tabCon.removeAll();
        processTable = aFile.getList(aFile.getProcess());
        tabCon.add(new JScrollPane(processTable));
        tabCon.getComponent(0).setPreferredSize(new Dimension(430, 300));
        ((JScrollPane)tabCon.getComponent(0)).getVerticalScrollBar().setUnitIncrement(20);
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
            int row = processTable.convertRowIndexToModel(processTable.getSelectedRow());
            if(row != -1)
            {
                String pid = (String)(processTable.getModel().getValueAt(row, processTable.getColumn("PID").getModelIndex()));
                String name = (String)(processTable.getModel().getValueAt(row, processTable.getColumn("Image Name").getModelIndex()));
                try
                {
                    if(aFile.isProcessRunning(aFile.getProcess(), pid))
                    {
                        try
                        {
                            int reply = JOptionPane.showConfirmDialog(null, ("Are you sure you want to kill " + name + " (" + pid + ")?"), "Kill Process", JOptionPane.YES_NO_OPTION);
                            if (reply == JOptionPane.YES_OPTION)
                            {
                                aFile.killProcess(pid);
                                tickTock();
                            }
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
                String file = startField.getText();
                Process process = new ProcessBuilder(file).start();
                /*InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;

                while ((line = br.readLine()) != null)
                { 
                    System.out.println(line);
                }
                process.waitFor();*/
                tickTock();
            }
            catch(Exception err)
            {
                JOptionPane.showMessageDialog(null, "Sorry. The indicated task cannot be executed.", "InfoBox: Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if(e.getSource() == startField)
        {
            startButton.doClick();
        }
    }
}
