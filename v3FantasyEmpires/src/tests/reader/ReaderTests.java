package tests.reader;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.Reader;


public class ReaderTests
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    String fileName = "test.txt";
    File file;

    @Before
    public void setupFile(){

        file = new File(fileName);
        try
        {
            file.createNewFile();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpFile()
    {
        file.delete();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testInFindsFile(){
        Object reader = Reader.in(fileName);
        assertNotNull(reader);

    }
    @Test
    public void testReadNoFile(){
        Boolean exceptionOccurred = false;
        try{
            Reader.in("missing.txt");
        }
        catch(Exception e){
            exceptionOccurred = true;
        }
        finally{
            assertTrue(exceptionOccurred);
        }
    }
    @Test
    public void testPrintingFromFile(){
        String[] test = new String[]{"test1","test2"};
        writeToFile(test);
        Reader.Print(Reader.in(fileName));
        assertEquals("test1\r\ntest2\r\n", outContent.toString());

    }
    @Test
    public void testPrintingException(){
        BufferedReader buffer = null;
        try
        {
            buffer = Reader.in(fileName);
            buffer.close();
            Reader.Print(buffer);

        } 
        catch(IOException e){
        }
        assertTrue( errContent.toString().length()>1 );
    }
    @Test
    public void testStoreFromFile(){
        String[] test = new String[]{"test1","test2"};
        writeToFile(test);
        ArrayList<String> answer = new ArrayList<String>();
        answer = Reader.Store(Reader.in(fileName));

        for (int n=0;n<test.length;n++)
            assertEquals(test[n], answer.get(n));


    }@Test
    public void testStoreException(){
        BufferedReader buffer = null;
        try
        {
            buffer = Reader.in(fileName);
            buffer.close();
            Reader.Store(buffer);

        } 
        catch(IOException e){
        }
        assertTrue( errContent.toString().length()>1 );
    }
    public void writeToFile(String[] words){
        FileWriter fstream;
        try
        {
            fstream = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fstream);
            for (int n=0;n<words.length;n++){
                out.write(words[n]);
                //if (n<words.length-1)
                out.write("\r\n");
            }
            //Close the output stream
            out.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void testPickOptionValidInt(){
        int answer = testPickOption("1",10);
        assertEquals(1,answer);
    }
    @Test
    public void testPickOptionAboveMax(){
        int answer = testPickOption("11",10);
        assertEquals(0,answer);
    }
    @Test
    public void testPickOptionBelowZero(){
        int answer = testPickOption("-1",10);
        assertEquals(0,answer);
    }
    @Test
    public void testPickOptionNotInt(){
        int answer = testPickOption("Test",10);
        assertEquals(0,answer);
    }
    public int testPickOption(String response,int max){
        System.out.println(response);
        InputStream input = new StringBufferInputStream(outContent.toString());
        System.setIn(input);
        return Reader.pickOption(max);
    }
    public void test()
    {


        PrintStream ps = new PrintStream(new FileOutputStream(FileDescriptor.out));
        ps.println(errContent.toString());
        fail("Not yet implemented");
    }

}
