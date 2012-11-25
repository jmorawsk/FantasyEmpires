import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class GeneratorJunitTest
{
	/* creates a new civilization */
	public Civilization getCivilizationConstructor(String name)
	{
		return new Civilization(new Race(name),"","",100);
	}
	
	/* creates and returns a list of civilizations */
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
	
	@Test
	/* tests for 100% statement coverage */
	public void testRandomCiv_StatementCoverage()
	{
		Civilization civ = Generator.RandomCiv();
	}

	@Test
	/* tests for 100% statement coverage */
	public void testPickRandom_statementCoverage()
	{
		List<Civilization> allCiv = this.getAllCivArrayList();
		Object obj = Generator.PickRandom((ArrayList<Civilization>) allCiv);
		
	}
	
	@Test 
	/* tests for 100% statement and branch coverage */
	public void testWorldGen_BranchCoverage()
	{
		Generator.WorldGen();
	}
}
