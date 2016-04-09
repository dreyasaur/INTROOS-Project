import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class FileAccess
{
    private JTable table;
    private Object[][] container = new Object[0][];
    private String[] header = new String[]
    {
        "Image Name",
        "PID",
        "Session Name",
        "Session Number",
        "Memory Usage",
        "Status",
        "Username",
        "CPU Time",
        "Windows Title"
    };
        
    public FileAccess()
    {
    	;
    }
    
    public JTable getList(Process P)
    {
        try
    	{
    		Process p = P;

            table = new JTable(new DefaultTableModel(getTable(p), header)
            {
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            });
            
            table.setAutoCreateRowSorter(true);
            table.getRowSorter().toggleSortOrder(table.getColumn("Image Name").getModelIndex());
            table.getRowSorter().toggleSortOrder(table.getColumn("Image Name").getModelIndex());
            
            //table.removeColumn(table.getColumnModel().getColumn(1));
            ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setPreferredWidth(120);
            table.getColumnModel().getColumn(3).setPreferredWidth(120);
            table.getColumnModel().getColumn(4).setPreferredWidth(120);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(200);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setPreferredWidth(300);
            table.setRowHeight(15);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setSelectionForeground(Color.WHITE);
            table.setSelectionBackground(Color.GRAY);
            table.setSelectionMode(0);
            table.setRowSelectionInterval(0, 0);
            
            return table;
    	}
        catch(Exception err)
        {
        	err.printStackTrace();
        }
        
        return null;
    }
    
    public Object[][] getTable(Process P)
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(P.getInputStream()));
        Object[][] temp;
    	String process;

        try
        {
            Object[] line = new Object[9];
            int row = 0;
            while((process = input.readLine()) != null)
            {
                row++;
                
                int i = 0,
                    ii = 0,
                    col = 0;
                do
                {
                    col++;
                    ii = process.indexOf(',', i);
                    if(ii != -1)
                    {
                        if(col == 5 && process.charAt(process.indexOf(',', (ii))-1) != 34)
                            ii = process.indexOf(',', (ii+1));
                        line[col-1] = process.substring(i+1, ii-1);
                    }
                    else
                        line[8] = process.substring(i+1, process.length()-1);
                    i = ii+1;
                } while(i != 0);
                
                temp = new Object[row][];
                for(int x = 0; x < row-1; x++)
                    temp[x] = container[x].clone();
                temp[row-1] = line;
                container = new Object[row][];
                for(int x = 0; x < row; x++)
                    container[x] = temp[x].clone();
            }
            input.close();
            
            return container;
        }
        catch(Exception err)
        {
        	err.printStackTrace();
        }
        return null;
    }
    
    public int getRowCount(Process P)
    {
        int count = 0;
        try
    	{
            BufferedReader input = new BufferedReader(new InputStreamReader(P.getInputStream()));
            String process;
            while((process = input.readLine()) != null)
                count++;
        }
        catch(Exception err)
        {
        	err.printStackTrace();
        }
        return count;
    }
    
    public boolean isProcessRunning(Process P, String servicePID) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(P.getInputStream()));
        String process;
 
        while((process = reader.readLine()) != null)
        {
            if(process.contains(",\""+servicePID+"\","))
                return true;
        }

        return false;
    }

    public static void killProcess(String servicePID) throws Exception
    {
        Runtime.getRuntime().exec("taskkill /F /PID "+servicePID);
    }
    
    public Process getProcess()
    {
        try
        {
            return Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /v /nh");
        }
        catch(Exception err)
        {
        	err.printStackTrace();
        }
        return null;
    }
}