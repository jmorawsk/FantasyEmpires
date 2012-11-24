package code;
import java.io.*;
import java.util.ArrayList;

public class Reader
{
    public static BufferedReader in(String fileName)
    {
        File inputFile = new File(fileName);
        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputStreamReader aReader = new InputStreamReader(inputStream);
        return new BufferedReader(aReader);
    }
    
    public static void Print(BufferedReader buffer)
    {
        String line = null;
        try
        {
            line = buffer.readLine();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(line != null)
        {
            System.out.println(line);
            Print(buffer);
        }
    }
    
    public static ArrayList<String> Store(BufferedReader buffer){
        ArrayList<String> list = new ArrayList<String>();
        String line = null;
        //boolean done = false;
        while(true)
        {
            try     
            {
                line = buffer.readLine();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (line == null)
                break;
            list.add(line);
        }
        
        return list;
    }
    
    public static int pickOption(int max)
    {
        int num = 0;
        try
        {
        BufferedReader input  = new BufferedReader(new InputStreamReader(System.in));
        String line = input.readLine();
        num = Integer.parseInt(line);
        }
        catch (Exception e)
        {
            
        }
        if (num>max)
            num=0;
        return num;
    }
}