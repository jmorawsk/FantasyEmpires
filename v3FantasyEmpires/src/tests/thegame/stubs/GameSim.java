package tests.thegame.stubs;


public class GameSim extends code.GameSim {

	private String[] seasons;
	private int turnNum;
	
	public GameSim(code.World world) {
		turnNum = 0;
		seasons = new String[] {
				"Spring", "Summer", "Autumn", "Winter"
		};
	}
	
	public void simulateOneStep() {
		
		System.out.println(seasons[turnNum % 4] + " of year " + turnNum / 4);
		
		turnNum++;
	}
	
	 public void simulate(int numSteps) {
		 for(int i = 0; i < numSteps; i++) {
			 simulateOneStep();
		 }
	 }
}
