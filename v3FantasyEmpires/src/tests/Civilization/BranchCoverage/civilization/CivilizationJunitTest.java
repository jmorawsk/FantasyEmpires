package code.civilization;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class CivilizationJunitTest
{

	Civilization civ = this.getCivilizationConstructor("ME");

	public Civilization getCivilizationConstructor(String name)
	{
		return new Civilization(new Race(name),"","",100);
	}

	public List<Civilization> getAllCivArrayList()
	{
		Civilization civ1 = this.getCivilizationConstructor("SUPER MEN");
		Civilization civ2 = this.getCivilizationConstructor("SUPER WOMEN");
		Civilization civ3 = this.getCivilizationConstructor("SUPER BOYS");
		Civilization civ4 = this.getCivilizationConstructor("SUPER GIRLS");

		List<Civilization> allCivs = new ArrayList<Civilization>();
		allCivs.add(civ1);
		allCivs.add(civ2);
		allCivs.add(civ3);
		allCivs.add(civ4);

		return (ArrayList<Civilization>) allCivs;
	}

	public List<Civilization> getLargeCivArrayList()
	{
		List<Civilization> allCivs = this.getAllCivArrayList();
		int i = 10;
		while(i --> 0)
		{
			List<Civilization> moreCivs = this.getAllCivArrayList();
			allCivs.addAll(moreCivs);
		}
		return allCivs;
	}

	@Test
	public void testPlayerAct_BrachCoverage()
	{
		List<Civilization> allCivs = this.getAllCivArrayList();

		
		allCivs.add(civ);

		int [] choice = {0,1,2,3,4,5,6, };
		civ.PlayerAct((ArrayList<Civilization>)allCivs, choice);	
	}

	@Test
	public void testAIact_BranchCoverage()
	{
		List<Civilization> allCivs = this.getLargeCivArrayList();
		
		allCivs.add(civ);
		allCivs.get(2).declareWar(allCivs.get(1));
		System.out.println(allCivs.size());
		allCivs.get(1).AIact((ArrayList<Civilization>)allCivs);
	}
	
	@Test
	public void testGrow_BranchCoverage()
	{
		
		civ.grow();
	}
	
	@Test
	public void testOffers_BranchCoverage()
	{

		
		civ.offerPeace();
		civ.offerAlliance();
	}
	
	@Test
	public void testDeclareWar_BranchCoverage()
	{
		
		Civilization enemy = this.getCivilizationConstructor("YOU");
		civ.declareWar(enemy);
		assertTrue(civ.getEnemies().contains(enemy.getName()));
		
		//it is impossible to get 100% branch coverage here because there is no way to 
		//make an ally
	}
	@Test
	public void testGettersAndSetters_BranchCoverage()
	{
		assertTrue(civ.getAllies().isEmpty());
		assertTrue(civ.getEnemies().isEmpty());
		assertTrue(civ.getknownCivs().isEmpty());
		civ.getName();
		assertTrue(civ.getPop()==100);
		civ.getRace().getName();
		assertTrue(civ.getType().isEmpty()); 
		civ.setName("DKSFJKLDS");
	}
	
	@Test
	public void testAct_BranchCoverage()
	{	
		List<Civilization> allCivs = this.getAllCivArrayList();
		civ.act((ArrayList<Civilization>)allCivs, 4);
		civ.makePlayer();
		civ.act((ArrayList<Civilization>)allCivs, 4);
	}
	@Test
	public void testBooleans()
	{
		assertTrue(!civ.isPlayer());
		civ.makePlayer();
		assertTrue(civ.isPlayer());
		assertTrue(!civ.isDefeated());
		civ.setPop(-1);
		assertTrue(civ.isDefeated());
	}

}
