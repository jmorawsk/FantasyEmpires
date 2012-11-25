package tests.helperclasses;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * An input stream that exits (with status 42) once a BufferedReader tries to read past the end of the string.
 */
public class ExitOnEmptyInputStream extends InputStream {

	private ArrayList<Byte> buffer;
	
	private boolean empty = false;
	
	public ExitOnEmptyInputStream(String input) {
		byte[] buf = input.getBytes();
		buffer = new ArrayList<>();
		
		for(byte b : buf) {
			if(b != '\0') {
				buffer.add(new Byte(b));
			}
		}
	}
	
	@Override
	public int read() {
		
		if(empty) {
			System.exit(42);
		}
		
		if(buffer.size() != 0) {
			return buffer.remove(0).byteValue();
		} else {
			//Note BufferedInputReader's method readLine reads up until a \n then one byte more to check for EOF.
			//So we need to exit only once it has try's to read for the second time.
			empty = true;
			return -1;
		}
	}
	
	public int available() {
		return buffer.size();
	}

	
	
}
