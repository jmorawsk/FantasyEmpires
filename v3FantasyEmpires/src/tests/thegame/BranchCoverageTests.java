package tests.thegame;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import tests.helperclasses.*;
import tests.helperclasses.NoExitSecurityManager.ExitException;

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
		

		//Make system.out and system.err traceable
		System.setOut(outStream);
		System.setErr(errStream);
	}
	
	@After
	public void tearDown() {
		System.setOut(systemout);
		System.setErr(systemerr);
	}
	
	@Test
	public void testIOException() {
		
		//Copy original System.in.
		InputStream systemin = System.in;
		
		
		//Set System.in to throw IOException when read
		System.setIn(new IOExceptionInputStream());


		//Stop game from calling exit.
		System.setSecurityManager(new NoExitSecurityManager());
		
		
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
		assertEquals(new Integer(-1), exitStatus); 


		assertEquals("Simulation, error: problem reading from console.", errStream.output.pop());

		//Set reset everything to it's default.
		System.setIn(systemin);
		
	}
	
	@Test
	public void testLoopCoditionFailure() {
		
		String input = "q\n";
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
	public void testEmptyInput() {
		String input = "\n";
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
	public void testCommandError() {
		String input = "42\n";
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
	public void testCommandSpace() {
		String input = " \n";
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
		
		assertEquals("Spring of year 0", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);

	}
	
	@Test
	public void testCommandM_NoArg() {
		String input = "m\n";
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
	public void testCommandM_Arg_Err() {
		String input = "m n\n";
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
	public void testCommandM_Arg_NoErr() {
		String input = "m 2\n";
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
		
		assertEquals("Summer of year 0", outStream.output.pop());
		assertEquals("Spring of year 0", outStream.output.pop());
		
		//Set reset everything to it's default.
		System.setIn(systemin);
	}

}
