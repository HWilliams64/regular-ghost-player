package professorCode;

import java.util.Objects;

/**
 * A class that contains useful methods that inform you game state information. All method beside {@link #getNextLine(String)} and {@link #setTeamName(String)} are expected 
 * to only accept the last line of the shared text file.  
 * @author Harris Williams
 * @since Sep 28, 2018
 */

public class TurnParser {
	/**
	 * A key word that denotes the game is over
	 */
	public static final String GAME_OVER = "GAME_OVER";
	/**
	 * A key word that denotes the game has begun
	 */
	public static final String GAME_START = "START_GAME";
	/**
	 * A key word that denotes a turn
	 */
	public static final String TURN = "TURN";
	/**
	 * The value that separates values in the text 
	 */
	public static final String DELIMITER = ":";

	private String teamName;

	/**
	 * Create a team parser
	 * @param teamName your team name
	 */
	public TurnParser(String teamName) {
		this.setTeamName(teamName);
	}

	private void validNonEmpty(String string) {
		if(Objects.isNull(string)) {
			throw new IllegalArgumentException("String is null");
		}
		if(string.replaceAll("\\s+","").isEmpty()) {
			throw new IllegalArgumentException("String is empty");
		}
	}
	
	/**
	 * Returns the specified string with all white spaces removed converted to all capital letters. 
	 * @param string
	 * @return
	 */
	public static String formateString(String string) {
		return string.replaceAll("\\s+","").toUpperCase();
	}

	/**
	 * Sets the value held by this class that denotes you team name
	 * @param teamName Your team name
	 * @throws IllegalArgumentException if the specified string is null or empty (contains no characters or only white space)
	 */
	public void setTeamName(String teamName) {
		validNonEmpty(teamName);
		
		teamName = formateString(teamName);
		
		this.teamName = teamName;
	}

	/**
	 * Tests if the specified string contains {@link #GAME_OVER}
	 * @param line The last line of the file to be tested
	 * @return true if and only if the specified string contains {@link #GAME_OVER}
	 * @throws IllegalArgumentException if the specified string is null or empty (contains no characters or only white space)
	 */
	public boolean isGameOver(String line){
		validNonEmpty(line);
		
		line = formateString(line);

		return line.contains(GAME_OVER);
	}
	
	/**
	 * Tests if the specified string contains {@link #GAME_START}
	 * @param line The last line of the file to be tested
	 * @return true if and only if the specified string contains {@link #GAME_START}
	 * @throws IllegalArgumentException if the specified string is null or empty (contains no characters or only white space)
	 */
	public boolean isGameStarted(String line){
		validNonEmpty(line);
		
		line = formateString(line);

		return line.contains(GAME_START);
	}

	/**
	 * Tests if the specified string denotes its the your turn. 
	 * <br>
	 * <br>
	 * More specifically, if String.split({@link #DELIMITER}) is called on the string the returned array should 
	 * have a length of 2 and the first value is equal to your team name and the second value equals {@link #TURN}
	 * @param line The last line of the file to be tested
	 * @return True if the string specified denotes it your turn
	 * @throws IllegalArgumentException if the specified string is null or empty (contains no characters or only white space)
	 */
	public boolean isMyTurn(String line){
		validNonEmpty(line);
		
		line = formateString(line);

		String[] split = line.split(DELIMITER);

		if(split.length == 2) {
			return split[0].equals(teamName) && split[1].equals(TURN);
		}

		return false;
	}

	/**
	 * Tests if the specified string denotes its the other team's turn. 
	 * <br>
	 * <br>
	 * More specifically, if String.split({@link #DELIMITER}) is called on the string the returned array should 
	 * have a length of 2 and the first value is not equal to your team name
	 * @param line The last line of the file to be tested
	 * @return True if the string specified denotes its the other team's turn
	 * @throws IllegalArgumentException if the specified string is null or empty (contains no characters or only white space)
	 */
	public boolean isOtherPlayersTurn(String line){
		validNonEmpty(line);
		
		line = formateString(line);

		String[] split = line.split(DELIMITER);

		if(split.length == 2) {
			return !split[0].equals(teamName);
		}

		return false;
	}

	/**
	 * Tests if the specified string denotes its the other team has completed there turn. 
	 * <br>
	 * <br>
	 * More specifically, if String.split({@link #DELIMITER}) is called on the string the returned array should 
	 * have a length of 2 and the first value is not equal to your team name and the second value is not equal to {@link #TURN}
	 * @param line The last line of the file to be tested
	 * @return True if the string specified denotes its the other team completed there turn
	 * @throws IllegalArgumentException if the specified string is null or empty (contains no characters or only white space)
	 */
	public boolean didOtherPlayerFinishTurn(String line){
		validNonEmpty(line);
		
		line = formateString(line);

		String[] split = line.split(DELIMITER);

		if(split.length == 2) {
			return !split[0].equals(teamName) && split[1].length() == 1;
		}

		return false;
	}

	/**
	 * Returns the letter played by the other team.
	 * <br>
	 * <br>
	 * More specifically,last non-white space charter of the string specified if and only if the string does not contain any 
	 * of the key words mentioned above and does not start with your team name. Otherwise this will throw an IllegalArgumentException.
	 * @param line The last line of the file to be tested
	 * @return A single character string denoting the letter that was played by the other team
	 * @throws IllegalArgumentException If the string specified does not confirm match the pattern "OTHER_TEAM_NAME:LETTER"
	 */
	public String getOtherPlayersLetter(String line){
		validNonEmpty(line);
		
		line = formateString(line);

		String[] split = line.split(DELIMITER);

		if(split.length == 2 && !split[0].equals(teamName) && split[1].length() == 1) {
			return split[1].trim();
		}

		throw new IllegalArgumentException("\""+line+"\" does not conform to OTHER_TEAM_NAME:LETTER pattern");
	}

	/**
	 * Creates a properly conformed string that should be written to the shared text file. 
	 *
	 * @param myLetter The letter you are going to use
	 * @return A string the matches the pattern "YOUR_TEAM_NAME:myLetter"
	 * @throws IllegalArgumentException if the specified string is null, empty (contains no characters or only white space) or contains more than 
	 * one character
	 */
	public String getNextLine(String myLetter){
		validNonEmpty(myLetter);
		
		myLetter = formateString(myLetter);
		
		if(myLetter.length() > 1) {
			throw new IllegalArgumentException("The specified string can only be one letter");
		}
		
		return "\n"+teamName+DELIMITER+myLetter;
	}
}
