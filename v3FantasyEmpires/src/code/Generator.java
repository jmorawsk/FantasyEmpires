package code;

import java.util.ArrayList;
import java.util.Iterator;


public class Generator
{
    
    public static Object PickRandom(ArrayList list)
    {
        int total = list.size() - 1;
        int pick = (int) Math.round(total*Math.random());
        return list.get(pick);
    }
    public static Civilization RandomCiv()
    {
        String fileName;
        fileName = "Civilizations.txt";
        String randomRace = (String) Generator.PickRandom(Reader.Store(Reader.in(fileName)));
        fileName = "CivTypes.txt";
        String randomName = (String) Generator.PickRandom(Reader.Store(Reader.in(fileName)));;
        fileName = "CivDescriptors.txt";
        String randomDescriptor = (String) Generator.PickRandom(Reader.Store(Reader.in(fileName)));;
        Civilization randomCiv = new Civilization(new Race(randomRace), randomName, randomDescriptor, 100);
        return randomCiv;
    }
    public static World WorldGen()
    {
        World newWorld = new World(0,100,0.05);
        
        double raceTotal = newWorld.getSize()*newWorld.getDensity();
        
        for (int i=0;i<raceTotal;i++)
        {
            Civilization newCiv  = RandomCiv();
            String newName = newCiv.getName();
            newWorld.addCiv(newCiv);
        }
        return newWorld;
    }
}
