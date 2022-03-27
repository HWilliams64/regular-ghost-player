package professorCode;

import java.io.IOException;

/**
 * A class with methods specifically for reading from text files
 * @author Harris Williams
 * @since Sep 28, 2018
 */
public interface FileTextWriter {

	/**
	 * Writes the specified string to a file at the specified path. It creates a new line in the file then writes the string
	 * to the file. It has the same result of calling System.out.println(String) has with the console but in this case its for a
	 * file.
	 * @param string the text that will be written to the file
	 * @param path the location of a file that the string will be written to
	 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
	 * @throws IllegalArgumentException if the specified string is null or empty (Meaning contains no characters or only white space)
	 */
	public void writeToFile(String string, String path) throws IOException, IllegalArgumentException;
}
