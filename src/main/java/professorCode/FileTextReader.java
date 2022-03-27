package professorCode;

import java.io.IOException;
import java.util.Set;

/**
 * A class with methods specifically for reading from text files
 * @author Harris Williams
 * @since Sep 28, 2018
 */
public interface FileTextReader{
	
	/**
	 * Reads all the text in text file at the specified path.
	 * @param path the location of a file that the text will be read
	 * @return A string of all the text contained in the specified text file
	 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
	 */
	public String readText(String path) throws IOException;
	
	/**
	 * Reads all the lines of the text file at the specified path. Adds each line of the text to the a collection and
	 * returns that collection.
	 * <br><br>
	 * Each line in the collection should have all the leading and trailing white space characters removed prior to
	 * adding it to the collection. See the trim() method in the String class as an alternative.
	 *
	 * @param path the location of a file that the text will be read
	 * @return A collection of all the lines of text contained in the specified text file
	 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
	 */
	public Set<String> getAllLines(String path) throws IOException;
	
	/**
	 * Reads the last non-empty (contains at least one character) line of the file at the specified path
	 * @param path the location of a file that the string will be written to
	 * @return The text of the last non-empty (Meaning contains at least one characters) line of the specified file
	 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
	 */
	public String getLastLine(String path) throws IOException;
}
