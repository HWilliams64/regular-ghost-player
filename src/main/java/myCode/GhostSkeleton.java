package myCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Random;

import professorCode.TurnParser;

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

		final Path sharedFile = FileSystems.getDefault().getPath(sharedFilePath);

		if(!sharedFile.toFile().exists()){
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

		/*
		 * Game running logic. This is where the shared game file is monitored for changes and the gameLogic()
		 * function is called when a changed is witnessed.
		 */
		gameLogic(sharedFile.toString());

		try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
			sharedFile.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
			while (true) {
				final WatchKey wk = watchService.take();
				for (WatchEvent<?> event : wk.pollEvents()) {
					final Path changed = (Path) event.context();
					if (sharedFile.endsWith(changed)) {
						gameLogic(changed.toString());
					}
				}
				boolean valid = wk.reset();
				if (!valid) {
					System.out.println("Key has been unregistered");
				}
			}
		}
	}

	/**
	 * This is where we implement our game logic. In this function you will:
	 * 1) Read from the shared game file.
	 * 2) Preform some logic based on the information your have read to select a word from the dictionary then play a letter based on the word.
	 * 3) Write the letter to the file.
	 *
	 * @param filePath The file path to the shared game file
	 * @throws IOException If anything wrong happens while attempting to read or write to the shared game file.
	 */
	private static void gameLogic(String filePath) throws IOException{
		//We get the most recent line written to the shared file
		String lastLine = fileManager.getLastLine(filePath);
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
