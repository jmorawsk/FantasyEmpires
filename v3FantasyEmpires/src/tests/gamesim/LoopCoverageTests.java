package tests.gamesim;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.gamesim.stubs.Civilization;
import tests.gamesim.stubs.GameSim;
import tests.gamesim.stubs.World;
import tests.helperclasses.ReadablePrintStream;

public class LoopCoverageTests {

	PrintStream systemout;
	PrintStream systemerr;
	ReadablePrintStream errStream;
	ReadablePrintStream outStream;

	@Before
	public void setUp() {
		systemout = System.out;
		systemerr = System.err;

		errStream = new ReadablePrintStream(systemerr);
		outStream = new ReadablePrintStream(systemout);

		// Make system.out and system.err traceable
		System.setOut(outStream);
		System.setErr(errStream);
	}

	@After
	public void tearDown() {
		System.setOut(systemout);
		System.setErr(systemerr);
	}

	@Test
	public void testDoubleStep_DoubleCiv_Defeated() {
		System.out.println("---Double Step Double Civ Defeated---");
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 0));

		civs.add(new Civilization(null, null,
				"Dolphin", 1)); //1 pop going to die each turn.
		
		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(2);

		assertEquals("Dolphin was defeated!", outStream.output.pop());
		assertEquals("Summer of year 0", outStream.output.pop());
		System.out.println("------");

	}

	@Test
	public void testDoubleStep_SingleCiv() {
		System.out.println("---Double Step Single Civ---");
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 0));

		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(2);

		assertEquals("Summer of year 0", outStream.output.pop());
		System.out.println("------");
	}


	@Test
	public void testDoubleStep_DoubleCiv_NotDefeated() {
		System.out.println("---Double Step Double Civ Not Defeated---");
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 0)); 

		civs.add(new Civilization(null, null,
				"Dolphin", 2)); //1 pop going to die each turn.

		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(2);

		assertEquals("Dolphin is acting.", outStream.output.pop());
		assertEquals("Summer of year 0", outStream.output.pop());
		System.out.println("------");

	}
	
	@Test
	public void testTripleStep_TripleCiv_Defeated() {
		System.out.println("---Triple Step Triple Civ Defeated---");
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 0));

		civs.add(new Civilization(null, null,
				"Dolphin", 1)); //1 pop going to die each turn.
		
		civs.add(new Civilization(null, null,
				"Hitchhiker", 2)); //1 pop going to die each turn.
		
		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(3);

		assertEquals("Hitchiker was defeated!", outStream.output.pop());
		assertEquals("Autumn of year 0", outStream.output.pop());
		System.out.println("------");
	}
	
	@Test
	public void testTripleStep_TripleCiv_NotDefeated() {
		System.out.println("---Triple Step Triple Civ Defeated---");
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 0));

		civs.add(new Civilization(null, null,
				"Dolphin", 1)); //1 pop going to die each turn.
		
		civs.add(new Civilization(null, null,
				"Hitchhiker", 3)); //1 pop going to die each turn.
		
		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(3);

		assertEquals("Hitchiker is acting.", outStream.output.pop());
		assertEquals("Autumn of year 0", outStream.output.pop());
		System.out.println("------");
	}

}
