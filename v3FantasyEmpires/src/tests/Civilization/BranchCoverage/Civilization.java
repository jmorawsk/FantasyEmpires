import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple model of a Civilization.
 * Civilizations have a name, race and descriptor.
 * A civilization has allies and enemies.
 * 
 * @author Jason Morawski 
 */
public class Civilization
{
    private boolean player;
    private double offense;
    private double defense;
    private static double baseGrowth = 0.02;
    private String descriptor;
    private String civType;
    private String civName;
    private Race race;
    private ArrayList<String> allies;
    private ArrayList<String> enemies;
    private ArrayList<String> knownCivs;
    private int population;
    
    public Civilization(Race startRace, String startType, String descriptor, int startPop)
    {
        this.race = startRace;
        this.civType = startType;
        this.descriptor = descriptor;
        this.civName = this.descriptor + " " + this.race + " " + this.civType;
        this.allies = new ArrayList<String>();
        this.enemies = new ArrayList<String>();
        this.knownCivs = new ArrayList<String>();
        this.population = startPop;
        this.defense = 1;
        this.offense = 1;
        this.player = false;
    }
    public void setName(String newName)
    {
        this.civName = newName;
    }
    public String getName()
    {
        return this.civName;
    }
    public String getType()
    {
        return this.civType;
    }
    public Race getRace()
    {
        return this.race;
    }
    public ArrayList<String> getAllies()
    {
        return this.allies;
    }
    public ArrayList<String> getEnemies()
    {
        return this.enemies;
    }
    public ArrayList<String> getknownCivs()
    {
        return this.knownCivs;
    }
    public void act(ArrayList<Civilization> allCivs)
    {
        //System.out.println(this.civName + " is acting.");

        this.grow();

        if (this.isPlayer())
            this.PlayerAct(allCivs);
        else
            this.AIact(allCivs);
    }
 
    public void PlayerAct(ArrayList<Civilization> allCivs)
    {
        System.out.println("Your turn. Select an action");
        System.out.println("1: Check my team.");
        System.out.println("2: Attack another team.");
        System.out.println("3: Declare War on another team.");
        System.out.println("4: End Your turn.");
        int num = 0;
        int attacks = 0;
        while (num==0)
        {
            num = Reader.pickOption(4);
            switch (num){
                case 1:{
                    System.out.println("Population: " + this.population);
                    num=0;
                    break;
                }
                case 2:{
                    this.attack(this.chooseTarget(allCivs));
                    num=0;
                    break;
                }
                case 3:{
                    this.declareWar(this.chooseTarget(allCivs));
                    num=0;
                    break;
                }
                case 4:{
                    break;
                }
                default: {
                    num=0;
                    break;
                }
            }
        }
    }
    
    public void AIact(ArrayList<Civilization> allCivs)
    {
        for(Iterator<Civilization> it = allCivs.iterator(); it.hasNext(); ) {
            Civilization civ = it.next();

            System.out.println("Random math:" +Math.random());
            if (!this.equals(civ) && !this.enemies.contains(civ.getName()) && Math.random()>0.9)
            {
                this.declareWar(civ);
                civ.declareWar(this);
            }
            if (this.enemies.contains(civ.getName()))
            {
                this.attack(civ);
            }
        }
    }
    private Civilization chooseTarget(ArrayList<Civilization> allCivs)
    {
        int num = 1;
        Civilization civ;
        for(Iterator<Civilization> it = allCivs.iterator(); it.hasNext();num++ ) {
            civ = it.next();

            System.out.println(num + ": " + civ);
        }
        return allCivs.get(Reader.pickOption(num));
    }
    
    private void attack(Civilization civ)
    {
        System.out.print(this +" attacked "+ civ +"! ");
        this.battle(civ);
    }
    private void battle(Civilization def)
    {

        double offense = this.offense*this.population*Math.random();
        double defense = def.defense*def.population*Math.random();
        int lost = 0;
        if (offense < defense)
        {
            lost = (int) Math.round( (defense - offense)*0.1);
            this.population -= lost;
            System.out.println(this + " lost " + lost +" people.");
        }
        if (offense > defense)
        {
            lost = (int) Math.round((offense - defense)*0.1);
            def.population -= lost;
            System.out.println(def + " lost " + lost +" people.");
        }
    }
    public void grow()
    {
        this.population += Math.round(this.population*baseGrowth*Math.random()*2);
    }
    public void declareWar(Civilization enemy)
    {
        String enemyName = enemy.getName();
        if (this.allies.contains(enemyName))
            this.allies.remove(enemyName);
        this.enemies.add(enemyName);
        System.out.println(this.civName + " declared war on " + enemy + "!");
    }
    public void offerPeace()
    {

    }
    public void offerAlliance()
    {

    }
    public String toString()
    {
        return this.civName;
    }
    public void setPop(int population)
    {
        this.population = population;
    }
    public int getPop()
    {
        return population;
    }
    public boolean isDefeated()
    {
        return (this.population<0);
    }
    public boolean isPlayer()
    {
        return this.player;
    }
    public void makePlayer()
    {
        this.player = true;
    }
    
    //methods modified for testing purposes
    public void act(ArrayList<Civilization> allCivs, int choice)
    {
        //System.out.println(this.civName + " is acting.");

        this.grow();
        
        if (this.isPlayer())
            this.PlayerAct(allCivs, new int[]{choice});
        else
            this.AIact(allCivs);
    }
    

    
    //created for testing without user input
    public void PlayerAct(ArrayList<Civilization> allCivs, int[] choice)
    {
        System.out.println("Your turn. Select an action");
        System.out.println("1: Check my team.");
        System.out.println("2: Attack another team.");
        System.out.println("3: Declare War on another team.");
        System.out.println("4: End Your turn.");
        int num = 0;
        int attacks = 0;
        int i = 0;
        while (num==0&&i<choice.length)
        {
            num = choice[i];
            switch (num){
                case 1:{
                    System.out.println("Population: " + this.population);
                    num=0;
                    break;
                }
                case 2:{
                    this.attack(this.chooseTarget(allCivs,1));
                    num=0;
                    break;
                }
                case 3:{
                    this.declareWar(this.chooseTarget(allCivs,1));
                    num=0;
                    break;
                }
                case 4:{
                    break;
                }
                default: {
                    num=0;
                    break;
                }
            }
            i++;
        }
    }

    //created for testing without user input
    private Civilization chooseTarget(ArrayList<Civilization> allCivs, int choice)
    {
        int num = 1;
        Civilization civ;
        for(Iterator<Civilization> it = allCivs.iterator(); it.hasNext();num++ ) {
            civ = it.next();

            System.out.println(num + ": " + civ);
        }
        return allCivs.get(choice);
    }
}
