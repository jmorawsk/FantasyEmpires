package tests.gamesim.stubs;

import java.util.ArrayList;


public class World extends code.World {
	private String[] seasons;
	private int turn;
	private ArrayList<code.Civilization> civs;
	
	public World(ArrayList<code.Civilization> civs) {
		turn = 0;
		seasons = new String[] {
				"Spring", "Summer", "Autumn", "Winter"
		};
		this.civs = civs;
	}
	
	public void newSeason() {
		turn++;
	}
	
	public String getSeason() {
		return seasons[turn % 4];
	}
	
	
	public int getAge() {
		return turn / 4;
	}
	
	public ArrayList<code.Civilization> getCivs() {
		return this.civs;
	}
	
}
