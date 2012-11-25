package tests.helperclasses;

import java.io.IOException;
import java.io.InputStream;

/**
 * An input stream that throws an IOException when someone tries to use it.
 * @author amccann
 *
 */
public class IOExceptionInputStream extends InputStream {

	@Override
	public int read() throws IOException {
		throw new IOException();
	}
	
}
