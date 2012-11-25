package tests.thegame.stubs;

import java.util.ArrayList;

public class World extends code.World {

	public World() {
		
	}
	
	public void CivList() {
		System.out.println("1: hyper-intelligent pan-dimensional beings");
	}
	
	public ArrayList<code.Civilization> getCivs() {
		ArrayList<code.Civilization> civs =  new ArrayList<>();
		
		civs.add(new tests.thegame.stubs.Civilization(null, null, null, 0));
		
		return civs;
	}
	
	public void setPlayer(int num) {
		//Dont care.
	}
	
}
