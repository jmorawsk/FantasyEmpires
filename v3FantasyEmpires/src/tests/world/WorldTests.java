package tests.world;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import code.Civilization;
import code.World;


public class WorldTests
{

    @Test
    public void testDefaultConstructor()
    {
        World w = new World();
        assertNotNull(w);
        assertNotNull(w.getAge());
        assertNotNull(w.getCivs());
        assertNotNull(w.getDensity());
        assertNotNull(w.getSeason());
        assertNotNull(w.getSize());

    }
    @Test
    public void testParameterConstructor()
    {
        int age = 2012;
        int size = 6371;
        double density = 70.8;
        World w = new World(age, size, density);
        assertNotNull(w);
        assertEquals(age,w.getAge());
        assertEquals(size,w.getSize());
        assertEquals(density,w.getDensity(),0.01);
        assertNotNull(w.getCivs());
        assertNotNull(w.getSeason());
    }
    @Test
    public void testNewSeasons(){
        World w = new World();
        for (int n=0;n<50;n++)
        {
            assertEquals(n,w.getAge());
            assertEquals("Spring",w.getSeason());
            w.newSeason();
            w.newSeason();
            w.newSeason();
            w.newSeason();
        }
    }
    @Test
    public void testEachSeason(){
        World w = new World();
        for (int n=0;n<2;n++)
        {
            assertEquals("Spring",w.getSeason());
            w.newSeason();
            assertEquals("Summer",w.getSeason());
            w.newSeason();
            assertEquals("Autumn",w.getSeason());
            w.newSeason();
            assertEquals("Winter",w.getSeason());
            w.newSeason();
        }
    }
    @Test
    public void testAddCiv(){
        World w = new World();
        Civilization civ = new Civilization(null,"","",1);
        Civilization civ2 = new Civilization(null,"","",1);
        w.addCiv(civ);
        w.addCiv(civ2);
        ArrayList<Civilization> civs = w.getCivs();
        assertEquals(civ,civs.get(0));
        assertEquals(civ2,civs.get(1));
    }
    @Test
    public void testCivList(){

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        World w = new World();
        int num = 5;
        spawnTestCivs(w,num);
        w.CivList();

        String result = outContent.toString();
        String expected;
        for (int n=0;n<num;n++){
            expected = "TestCiv" + (n+1);
            assertTrue(result.indexOf(expected)!=-1);
        }
    }

    @Test
    public void testSetPlayerValid(){
        int num=5;
        int player=1;
        World w = new World();
        spawnTestCivs(w, num);
        w.setPlayer(player);
        for (int n=0;n<num;n++){
            if (n!=player-1)
                assertFalse(w.getCivs().get(n).isPlayer());
            else
                assertTrue(w.getCivs().get(n).isPlayer());
        }
    }
    @Test
    public void testSetPlayerTooLarge(){
        int num=5;
        int player=6;
        World w = new World();
        spawnTestCivs(w, num);
        w.setPlayer(player);
        for (int n=0;n<num;n++){
            assertFalse(w.getCivs().get(n).isPlayer());
        }
    }
    @Test
    public void testSetPlayerTooSmall(){
        int num=5;
        int player=0;
        World w = new World();
        spawnTestCivs(w, num);
        w.setPlayer(player);
        for (int n=0;n<num;n++){
            assertFalse(w.getCivs().get(n).isPlayer());
        }
    }
    public void spawnTestCivs(World w,int num){
        Civilization civ;
        for (int n=0;n<num;n++){
            civ = new Civilization(null,"","",1);
            civ.setName("TestCiv" + (n+1));
            w.addCiv(civ);
        }
    }

}
