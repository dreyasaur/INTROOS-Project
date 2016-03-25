import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class FileAccess
{
    private JTable table;

    public FileAccess()
    {
    	;
    }
    
    public JTable getList()
    {
        String[] header = new String[]
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
        
        try
    	{
    		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fi \"username eq alicbusanR\" /fo csv /v /nh");
            return new JTable(new DefaultTableModel(getTable(p), header)
            {
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            });
    	}
        catch(Exception err)
        {
        	err.printStackTrace();
        }
        
        return null;
    }
    
    public Object[][] getTable(Process p)
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	Object[][] container = new Object[0][],
                   temp;
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
                        if(col == 5)
                            ii = process.indexOf(',', (ii+1));
                        line[col-1] = process.substring(i+1, ii-1);
                    }
                    else
                        line[8] = process.substring(i+1, process.length()-1);
                    i = ii+1;
                } while(i != 0);
                
                //Marker: Incremental Problem
                temp = new Object[row][];
                if(row > 1)
                    for(int x = 0; x < container.length; x++)
                        for(int y = 0; y < 9; y++)
                            System.out.println(">x: "+x+" y: "+y+" : "+container[x][y]);
                if(temp.length != 0)
                    System.arraycopy(container, 0, temp, 0, row-1);
                temp[row-1] = line;
                container = temp;
                for(int x = 0; x < temp.length; x++)
                    for(int y = 0; y < 9; y++)
                        System.out.println("<x: "+x+" y: "+y+" : "+temp[x][y]);
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
}