package code;
import java.util.ArrayList;
import java.util.Iterator;



public class World
{
    private ArrayList<Civilization> civs;
    private int worldAge;
    private int size;
    private double density;
    String era;
    String season;
    public World(){
        this.worldAge = 0;
        this.size = 100;
        this.density = 0.1;
        this.season = "Spring";
        civs = new ArrayList<Civilization>();
    }
    public World(int age, int size, double density){
        this.worldAge = age;
        this.size = size;
        this.density = density;
        this.season = "Spring";
        civs = new ArrayList<Civilization>();
    }
    public ArrayList<Civilization> getCivs()
    {
        return this.civs;
    }
    public void addCiv(Civilization civ)
    {
        this.civs.add(civ);
    }
    public int getAge()
    {
        return this.worldAge;
    }
    public int getSize()
    {
        return this.size;
    }
    public double getDensity()
    {
        return this.density;
    }
    public String getEra()
    {
        return this.era;
    }
    public String getSeason()
    {
        return this.season;
    }
    public void newSeason()
    {
        if (this.season.equals("Spring"))
            this.season = "Summer";
        else if (this.season.equals("Summer"))
            this.season = "Autumn";
        else if (this.season.equals("Autumn"))
            this.season = "Winter";
        else if (this.season.equals("Winter")){
            this.season = "Spring";
            this.worldAge ++;
        }

    }
    public void CivList()
    {
        int num = 1;
        for(Iterator<Civilization> it = this.civs.iterator(); it.hasNext();num++ ) {
            Civilization civ = it.next();

            System.out.println(num + ": " + civ);

        }
    }
    public void setPlayer(int num)
    {
        if (num>0 && num<=civs.size())
        {
            this.civs.get(num-1).makePlayer();
        }
    }
}
