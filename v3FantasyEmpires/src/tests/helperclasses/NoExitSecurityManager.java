package tests.helperclasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.Permission;

/**
 * if you set System.setSecurityManager(new NoExitSecurityManager()) then a ExitException will be thrown when any code
 * folllowing it tries to exit. You can then check the exit catching ExitException e, and calling e.status.
 * 
 * Allows you to test if a program exited as you expected it to without your test cases exiting.
 * 
 * Make sure to call System.setSecurityManager(null) once you want exits to occur properly again.
 * 
 * 
 * Also note that jUnit asserts will ALWAYS try to exit if they fail. Therefore always use it like this:
 * 
 * 		//Stop game from calling exit.
 *		System.setSecurityManager(new NoExitSecurityManager());
 *		
 *		
 *		Integer exitStatus = null;
 *		try {
 *			// The method that should call System.exit() //;						
 *		} catch (ExitException e) {
 *			exitStatus = new Integer(e.status);
 *		}	
 *		
 *		//Reset Security manager
 *		System.setSecurityManager(null);
 *		
 *		//Did not exit.
 *		assertTrue(exitStatus != null);
 *		//Exited with expected status.
 *		assertEquals(new Integer(//Expected Error Status//), exitStatus); 
 * 
 * @author amccann
 *
 */
public class NoExitSecurityManager extends SecurityManager 
{
	@Override
	public void checkPermission(Permission perm) 
	{
		// allow anything.
	}
	@Override
	public void checkPermission(Permission perm, Object context) 
	{
		// allow anything.
	}
	@Override
	public void checkExit(int status) 
	{
		super.checkExit(status);
		throw new ExitException(status);
	}
	
	public static class ExitException extends SecurityException 
	{
		public final int status;
		public ExitException(int status) 
		{
			super("There is no escape!");
			this.status = status;
		}
	}
}

