package tests.gamesim;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tests.gamesim.stubs.*;
import tests.helperclasses.ReadablePrintStream;

public class BranchCoverageTests {
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
	public void testSingleStep_SingleCiv_Defeated() {
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 0));

		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(1);

		assertEquals("hyper-intelligent pan-dimensional being was defeated!",
				outStream.output.pop());
		assertEquals("Spring of year 0", outStream.output.pop());

	}

	@Test
	public void testNoStep() {
		GameSim sim = new GameSim(null);
		sim.simulate(0);

		assertEquals(0, outStream.output.size());
	}

	@Test
	public void testSingleStepNoCiv() {
		ArrayList<code.Civilization> civs = new ArrayList<>();

		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(1);

		assertEquals(1, outStream.output.size());
		assertEquals("Spring of year 0", outStream.output.pop());
	}

	@Test
	public void testSingleStep_SingleCiv_NotDefeated() {
		ArrayList<code.Civilization> civs = new ArrayList<>();

		civs.add(new Civilization(null, null,
				"hyper-intelligent pan-dimensional being", 1));

		World world = new World(civs);

		GameSim sim = new GameSim(world);

		sim.simulate(1);

		assertEquals("hyper-intelligent pan-dimensional being is acting.",
				outStream.output.pop());
		assertEquals("Spring of year 0", outStream.output.pop());

	}

}
