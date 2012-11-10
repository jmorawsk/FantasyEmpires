import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class GameSim
{
    private World world;
    private int step;

    public GameSim()
    {

        // TODO Auto-generated constructor stub
    }
    
    public GameSim(World aWorld)
    {
        this.world = aWorld;
        // TODO Auto-generated constructor stub
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * DON'T USE FOR PLAYING
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps ; step++) {
            simulateOneStep();
        }
    }
    /**
     * Run the simulation from its current state for the one step/season.
     */
    public void simulateOneStep()
    {
        step++;
        world.newSeason();
        System.out.println(world.getSeason() +" of year " + world.getAge());
        for(Iterator<Civilization> it = world.getCivs().iterator(); it.hasNext(); ) {
            Civilization civ = it.next();
            civ.act(world.getCivs());
            if (civ.isDefeated())
            {
                System.out.println(civ + " was defeated!");
                it.remove();
            }
        }
    }
}
