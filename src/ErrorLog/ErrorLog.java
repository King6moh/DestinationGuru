package ErrorLog;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * ErrorLog class.
 */

public abstract class ErrorLog {
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;
	static protected Logger logger;

	/**
	 * Constructor for ErrorLog objects.
	 */
	public ErrorLog() {

		// Get the global logger to configure it
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		logger.setLevel(Level.INFO);

		logger.setUseParentHandlers(false);
	}
	
	public void open(){
		try {
			fileTxt = new FileHandler("ErrorLog.txt", true); // Second parameter true for append
			// create txt Formatter
			formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);

		} catch (Exception e) {
			System.out.println("Couldn't create the logging file");
		} 
	}
	
	public void close(){
		try {
			fileTxt.close();
		} catch (Exception e) {
			System.out.println("Couldn't close the logging file");
		} 
	}
	
	/**
	 * Get the Logger object being used in the ErrorLog.
	 * @return The Logger object being used in the ErrorLog.
	 */
	/*public Logger getErrorLog(){ // Useful if not abstract class...
		return logger;
	}*/

	/**
	 * Append to the log.
	 * @param s The string to be appended to the log.
	 */
	/*public static void log(String s){
		logger.info(s);
	}*/
}

