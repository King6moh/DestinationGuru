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
		try {
			fileTxt = new FileHandler("ErrorLog.txt", true); // Second parameter true for append
		} catch (Exception e) {
			System.out.println("Couldn't create the logging file");
		}

		// create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);

		logger.setUseParentHandlers(false);
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

