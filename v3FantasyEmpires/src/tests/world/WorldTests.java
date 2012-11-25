package tests.world;

import static org.junit.Assert.*;

import org.junit.Test;

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
    public void testCivList(){
        //TODO
        World w = new World();
    }

    @Test
    public void testSetPlayer(){
        //TODO
        World w = new World();
        
    }
    public void test()
    {

        fail("Not yet implemented");
    }

}
