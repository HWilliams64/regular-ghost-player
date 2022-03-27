package professorCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class with methods to monitor a text file.
 * @author Harris Williams
 * @since Sep 28, 2018
 */
public abstract class AbstractFileMonitor {

	private MessageDigest messageDigest;
	private int currentCheckSum;
	private boolean hasChanged = false;
	private long nextUpateTime = 0;
	
	public AbstractFileMonitor(String path){
        setFilePath(path);
	}
	
	/**
	 * Updates the variables that correspond to the file being monitored
	 * <br>
	 * <br>
	 * This function has been throttled such that it will only update values every 250 ms. In other words successive calls to this 
	 * function in time intervals that are less than 250 ms will yield no change.  
	 * @throws IOException Thrown if any type of I/O exception occurs while writing to the file
	 * @throws IllegalStateException If the {@link #setFile(String)} method in not invoked with a proper file prior to invocation of this
	 * @throws NoSuchAlgorithmException If the computer's OS is missing the SHA-256 hashing algorithm
	 * method. In other words if no file is currently set to be monitored. 
	 */
	public void update() throws IOException, IllegalStateException, NoSuchAlgorithmException {

		if(messageDigest == null){
			messageDigest = MessageDigest.getInstance("SHA-256");
		}
		
		if(nextUpateTime > System.currentTimeMillis()) {
			hasChanged = false;
			
			return;
		}
		
		nextUpateTime = System.currentTimeMillis() + 250;
		
		File file = new File(getFilePath());
		
		if(!file.exists()) return;
		
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(getFilePath()), messageDigest)) {
            while (dis.read() != -1) ;
            messageDigest = dis.getMessageDigest();
        }

        StringBuilder result = new StringBuilder();
        for (byte b : messageDigest.digest()) {
            result.append(String.format("%02x", b));
        }
        
        hasChanged =  currentCheckSum != result.toString().hashCode();
        
        currentCheckSum = result.toString().hashCode();
	}

	/**
	 * Tests if the file being monitored has changed since the last time {@link #update()} was invoked.
	 * @return true if and only if this monitor deems this file has changed. This will return false if the {@link #update()} method is not
	 * invoked prior to invocation of this method
	 */
	public boolean hasChanged(){
		return hasChanged;
	}
	
	/**
	 * Sets the path of the file to be monitored.
	 * @param path the location of the file to be monitored
	 * @throws IllegalArgumentException if path is null, empty or blank.
	 */
	public abstract void setFilePath(String path);

	/**
	 * Get the path of the file that is monitored
	 * @return path the location of the file that is monitored
	 * @throws IllegalStateException if {@link #setFile(String)} is not invoked prior to calling this method.
	 */
	public abstract String getFilePath() throws IllegalStateException;
}
