package myCode;

import professorCode.TurnParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class GhostSkeleton {

	private static String sharedFilePath;
	private static TurnParser turnParser;
	private static StringBuffer wordFragment;
	private static FileManager fileManager;
	private static Dictionary dictionary;
	public static String dictionaryFilePath = getRunningDirectory()+File.separator+"ARBITER_DICTIONARY.txt";

	public static void main(String[] args) throws Exception {

		if(args == null || args.length < 1){
			throw new IllegalArgumentException(getTeamName()+".jar could not start because a shared file path was not specified as a runtime argument.");
		}

		if(!new File(args[0]).exists()){
			throw new FileNotFoundException(getTeamName()+".jar could not start because a shared file path specified points to a file that does not exist.");
		}

		if(!new File(dictionaryFilePath).exists()){
			throw new FileNotFoundException(getTeamName()+".jar could not start because the dictionary could not be found.");
		}

		//The location of the shared game file will be provided to you at runtime
		sharedFilePath = args[0];

		/*
		 * Create all of your necessary classes. 
		 * Keep in mind that my constructors may look different than yours.
		 */
		turnParser = new TurnParser(getTeamName());

		wordFragment = new StringBuffer();

		fileManager = new FileManager(sharedFilePath);

		dictionary = new Dictionary(dictionaryFilePath, fileManager);
		
		//Starts your game loop
		while(true) {

			//Updates the file monitor with the most recent information
			fileManager.update();
			
			//A change to the shared file as occurred!
			if(fileManager.hasChanged()) {
				
				//We get the most recent line written to the shared file
				String lastLine = fileManager.getLastLine(sharedFilePath);

				//Check if its our turn
				if (turnParser.isMyTurn(lastLine)) {
					
					/*
					 * This is where we'd would do our more complex logic for letter selection.
					 * 
					 * We would use the wordFragment in combination with our dictionary to
					 * find possible words that start with the given word fragment.
					 *
					 * For example purposes I am simply selecting a random letter from a - z.
					 */
					Random rnd = new Random();
					
					char c = (char) (rnd.nextInt(26) + 'a');
					
					String nextLetter = Character.toString(c);
					
					//Figure out if we want even or odd length words
					System.out.println("I WANT EVEN WORDS: "+doIWantEvenLengthWords());
					
					//Adds the letter to our word fragment
					wordFragment.append(nextLetter);
					
					//Creates the next line to be written to the shared file
					String nextLine = turnParser.getNextLine(nextLetter);
					
					//Writes the next line to the shared file
					fileManager.writeToFile(nextLine, sharedFilePath);
				}
				
				//Checks if the other player has finished their turn
				else if(turnParser.didOtherPlayerFinishTurn(lastLine)){
					//Adds their letter to my word fragment
					wordFragment.append(turnParser.getOtherPlayersLetter(lastLine));
				
				//The game is over and its time for us to quit
				}else if(turnParser.isGameOver(lastLine)) {
					System.exit(0);
				}
			}
		}
	}
	
	/**
	 * Tells you if you want to select odd or even words. Only call this before adding your letter to the word fragment
	 * and it will tell you if your looking for even or odd length words.
	 * @return True if you want Even length words. False otherwise.
	 */
	public static boolean doIWantEvenLengthWords() {
		return (wordFragment.length() & 1) == 0; 
	}

	/**
	 * Returns a file object representing your jar file.
	 * @return A file object
	 */
	private static File getJarFile(){
		return new java.io.File(GhostSkeleton.class.getProtectionDomain()
				.getCodeSource()
				.getLocation()
				.getPath());
	}

	/**
	 * This will figure out your team name by using the name of your jar file.
	 * @return A string representing your team name. The string is equal to the name of your jar file.
	 */
	public static String getTeamName(){
		return getJarFile().getName().replaceAll(".jar", "");
	}

	/**
	 * This will return the directory that your jar file is located in. This is useful when you want to reference
	 * your dictionary text file when it is located in the same directory as your jar file.
	 * @return The current directory of your jar file.
	 */
	public static String getRunningDirectory(){
		return getJarFile().getParent();
	}
}
