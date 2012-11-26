package tests.race;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Race;

public class RaceTests {

	@Test
	public void testRaceGetName() {
		String name = "test";
		Race race = new Race(name);
		assertEquals(race.getName(), name);
	}
	
	@Test
	public void testRacetoString() {
		String name = "test";
		Race race = new Race(name);
		assertEquals(race.toString(), name);
	}

}
