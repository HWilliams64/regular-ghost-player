package professorCode;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * A class that holds a collection of words read from a text file. The collection of words is used in the methods of this class
 * methods provided in this class.
 * @author Harris Williams
 * @since Sep 28, 2018
 */
public abstract class AbstractDictionary {
	
	private final FileTextReader FILE_READER;
	private final Set<String> ALL_WORDS;
	
	/**
	 * Creates an abstract dictionary of words using the text file at the specified path. 
	 * @param path a path to a text file containing a dictionary of words
	 * @param dictionaryFileReader the FileReader used to read from the text file at the specified path
	 * @throws IOException thrown if there is a problem reading the file at the path
	 */
	public AbstractDictionary(String path, FileTextReader dictionaryFileReader) throws IOException {
		Objects.requireNonNull(dictionaryFileReader, "The File reader can not be null");
		Objects.requireNonNull(path, "The path can not be null");

		FILE_READER = dictionaryFileReader;

		ALL_WORDS = FILE_READER.getAllLines(path);
	}
	
	/**
	 * Returns a set of all the words contained in the dictionary text file.
	 * @return a set containing all the words in the dictionary file.
	 */
	public Set<String> getAllWords(){
		return ALL_WORDS;
	}


	/**
	 * Counts the number of words in this Dictionary that start with the specified prefix and have a length that is equal or greater
	 * than the specified size. If size the specified size is less than 1 then word size is not taken into account.
	 * @param prefix the prefix to be found
	 * @param size the length that the word must equal or be greater than. If a value less than 1 is specified, all words regardless of their
	 * characters size should be considered. In other words if the size parameter is < 1 word size is disregarded in the calculations. 
	 * @param ignoreCase if true this will ignore case differences when matching the strings. If false this considers 
	 * case differences when matching the strings
	 * @return The number of words that start with the specified prefix
	 * @throws IllegalArgumentException if the specified string is null or empty (Meaning contains no characters or only white space or blank)
	 */
	public abstract int countWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException;

	/**
	 * Tests if this Dictionary contains at least one word with a length equal to or greater than the specified size that starts with the specified prefix.
	 * If size the specified size is less than 1 then word size is not taken into account. 
	 * @param prefix the prefix to be found
	 * @param size the length that the word must equal or be greater than. If a value less than 1 is specified, all words regardless of their
	 * characters size should be considered. In other words if the size parameter is < 1 word size is disregarded in the calculations.
	 * @param ignoreCase if true this will ignore case differences when matching the strings. If false this considers 
	 * case differences when matching the strings
	 * @return The number of words that start with the specified prefix
	 * @throws IllegalArgumentException if the specified string is null or empty (Meaning contains no characters or only white space)
	 */
	public abstract boolean containsWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException;

	/**
	 * Returns a set of all the words within in this Dictionary that start with the specified prefix and have a length that is equal or greater
	 * than the specified size. If size the specified size is less than 1 then word size is not taken into account.
	 * @param prefix the prefix to be found
	 * @param size the length that the word must equal or be greater than. If a value less than 1 is specified, all words regardless of their
	 * characters size should be considered. In other words if the size parameter is < 1 word size is disregarded in the calculations.
	 * @param ignoreCase if true this will ignore case differences when matching the strings. If false this considers 
	 * case differences when matching the strings
	 * @return A list of all strings that start with the specified prefix
	 * @throws IllegalArgumentException if the specified string is null or empty (Meaning contains no characters or only white space)
	 */
	public abstract Set<String> getWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException;
	
}
