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
	
}

