package tests.gamesim.stubs;

import java.util.ArrayList;

import code.Race;

public class Civilization extends code.Civilization {

	private String civName;
	private int population;
	
	public Civilization(Race startRace, String startType, String descriptor,
			int startPop) {
		super(startRace, startType, descriptor, startPop);
		
		this.civName = descriptor;
		this.population = startPop;
	}
	

	public void act(ArrayList<code.Civilization> allCivs)
    {
        System.out.println(this.civName + " is acting.");
        population--;
    }
	
	public boolean isDefeated() {
		return population <= 0;
	}
	
	public String toString() {
		return civName;
	}
}
