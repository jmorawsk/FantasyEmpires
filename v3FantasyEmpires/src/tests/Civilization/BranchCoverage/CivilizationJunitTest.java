import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class CivilizationJunitTest
{

	Civilization civ = this.getCivilizationConstructor("ME");
	@Test
	public void testConstructor()
	{
		Race race = new Race("SUPER MEN");
		Civilization civ1 = new Civilization(race,"","",8);
		assertTrue(civ1.getName().trim().equals("SUPER MEN"));
		assertTrue(civ1.getPop()==8);

		Civilization civ2 = new Civilization(race,"aaaaaaaaaaaaaaaaaaaaaaaaajsdfk;asjdfkl;adklsfkasjdfkl;aklsdfklasjkl;dfalsfkals;jska;dfjsakl;fkadfkadksfaksf;asflasklafkalsfklasdflasfklasdfadsfasdfjjaskdfjkasjfdk;aklsdfjklsafkl;aklsdfkflasfldfkjas;f","",8);
		assertTrue(civ2.getName().trim().equals("SUPER MEN aaaaaaaaaaaaaaaaaaaaaaaaajsdfk;asjdfkl;adklsfkasjdfkl;aklsdfklasjkl;dfalsfkals;jska;dfjsakl;fkadfkadksfaksf;asflasklafkalsfklasdflasfklasdfadsfasdfjjaskdfjkasjfdk;aklsdfjklsafkl;aklsdfkflasfldfkjas;f"));
		assertTrue(civ2.getPop()==8);

		Civilization civ3 = new Civilization(race,"","jas;lkfkasdfjk;asdkajklsjdfk;alsdflasdfjakls;dfkasfkaksflkakalsfjk;asfdlfsffakasjfk;asfsadjf;aksdfjas;saf;afjsadjf;klkadfjdskasdfasfkajsfaksd;fdfaksfklfadsfjas;dfkaklsfkaskf",8);
		assertTrue(civ3.getName().trim().equals("jas;lkfkasdfjk;asdkajklsjdfk;alsdflasdfjakls;dfkasfkaksflkakalsfjk;asfdlfsffakasjfk;asfsadjf;aksdfjas;saf;afjsadjf;klkadfjdskasdfasfkajsfaksd;fdfaksfklfadsfjas;dfkaklsfkaskf SUPER MEN"));
		assertTrue(civ3.getPop()==8);

		Civilization civ4 = new Civilization(race,"","",-1);
		assertTrue(civ4.getName().trim().equals("SUPER MEN"));
		assertTrue(civ4.getPop()==-1);

		Civilization civ5 = new Civilization(race,"","",0);
		assertTrue(civ5.getName().trim().equals("SUPER MEN"));
		assertTrue(civ5.getPop()==0);

		Civilization civ6 = new Civilization(race,"","",2147483647);
		assertTrue(civ6.getName().trim().equals("SUPER MEN"));
		assertTrue(civ6.getPop()==2147483647);
	}

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
		
		//it is impossible to get 10% branch coverage here because there is no way to 
		//make an ally
	}
	@Test
	public void testGettersAndSetters_BranchCoverage()
	{
		assertTrue(civ.getAllies().isEmpty());
		assertTrue(civ.getEnemies().isEmpty());
		assertTrue(civ.getknownCivs().isEmpty());
		assertTrue(civ.getName().trim().equals("ME"));
		assertTrue(civ.getPop()==100);
		assertTrue(civ.getRace().getName().equals(new Race("ME").getName()));
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
		//fails
		civ.setPop(0);
		assertTrue(civ.isDefeated());
	}

}
