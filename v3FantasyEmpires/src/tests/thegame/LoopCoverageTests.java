package tests.thegame;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.helperclasses.ExitOnEmptyInputStream;
import tests.helperclasses.NoExitSecurityManager;
import tests.helperclasses.ReadablePrintStream;
import tests.helperclasses.NoExitSecurityManager.ExitException;

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
		

		//Make system.out and system.err traceable
		System.setOut(outStream);
		System.setErr(errStream);
	}
	
	@After
	public void tearDown() {
		System.setOut(systemout);
		System.setErr(systemerr);
	}
	
	/*
	 * Test 1st Itteration
	 */
	
	@Test
	public void testLoopQuitI1() {
		String input = "m 2\nq\n";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(0), exitStatus); 

		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testEmptyInputI1() {
		String input = "m 2\n\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("empty entry not allowed; options are 's', 'm <number>', 'l' or 'q'", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandErrorI1() {
		String input = "m 2\n42\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("invalid command; options are 's', 'm <number>', 'l' or 'q'", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandSpaceI1() {
		String input = "m 2\n \n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("Autumn of year 0", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandM_NoArgI1() {
		String input = "m 2\nm\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("invalid command:  e.g., m 20", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandM_Arg_ErrI1() {
		String input = "m 2\nm n\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("invalid command:  e.g., m 20", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandM_Arg_NoErrI1() {
		String input = "m 2\nm 2\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("Winter of year 0", outStream.output.pop());
		assertEquals("Autumn of year 0", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	/*
	 * Test 2nd Itteration (More than 1)
	 */

	@Test
	public void testLoopQuitI2() {
		String input = "m 2\nm 2\nq\n";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(0), exitStatus); 

		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testEmptyInputI2() {
		String input = "m 2\nm 2\n\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("empty entry not allowed; options are 's', 'm <number>', 'l' or 'q'", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandErrorI2() {
		String input = "m 2\nm 2\n42\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("invalid command; options are 's', 'm <number>', 'l' or 'q'", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandSpaceI2() {
		String input = "m 2\nm 2\n \n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("Spring of year 1", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandM_NoArgI2() {
		String input = "m 2\nm 2\nm\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("invalid command:  e.g., m 20", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandM_Arg_ErrI2() {
		String input = "m 2\nm 2\nm n\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("invalid command:  e.g., m 20", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
	
	@Test
	public void testCommandM_Arg_NoErrI2() {
		String input = "m 2\nm 2\nm 2\n";
		InputStream is = new ExitOnEmptyInputStream(input);
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		//Set system.in to exit when it tries to read after it already read my string.
		System.setIn(is);
		
		Integer exitStatus = null;
		try {
			tests.thegame.stubs.TheGame.main(null);						
		} catch (ExitException e) {
			exitStatus = new Integer(e.status);
		}	
		
		//Reset Security manager
		System.setSecurityManager(null);
		
		//Did not exit.
		assertTrue(exitStatus != null);
		//Exited with expected status.
		assertEquals(new Integer(42), exitStatus); 
		
		assertEquals("Summer of year 1", outStream.output.pop());
		assertEquals("Spring of year 1", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}
}
