package dataManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ErrorLog.ErrorLog;

/**
 * 
 * @author Daniel
 *A wrapper class for basic file IO. 
 *Each instance is matched to one file.
 *Opening and closing the BufferedReader are built in to getLines()
 * Opening and closing the BufferedWriter is built in to write
 */
public class FileIO extends ErrorLog {

	private File file;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	/**
	 * 
	 * @param fileName The filename with which IO will be performed
	 * Gets a BufferedReader for the file
	 */
	public FileIO(String fileName)
	{
		super();
		file = new File(fileName);
	}
	
	
	/**
	 * 
	 * get a BufferedReader for the File file.
	 */
	private void getBufferedReader()
	{
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			open();
			logger.info("Error creating fileReader " + e);
			close();
		}
	}
	
	/**
	 * Closes the BufferedReader
	 */
	private void closeBufferedReader()
	{
		try {
			reader.close();
		} catch (IOException e) {
			open();
			logger.info("Error closing BufferedReader " + e);
			close();
		}
	}
	/**
	 * 
	 * @return ArrayList of every line from the file
	 * opens and closes the bufferedReader
	 */
	public ArrayList<String> getLines()
	{
		getBufferedReader();
		ArrayList<String> lineArray = new ArrayList<String>();
		String line;
		try {
			while ((line = reader.readLine()) != null)
			{
				lineArray.add(line);
			}
		}catch (IOException e) {
			open();
			logger.info("Error reading from file " + e);
			close();
		}
		closeBufferedReader();
		return lineArray;
	}
	
	
	private void getBufferedWriter()
	{
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			open();
			logger.info("Error creating BufferedWriter "  +e);
			close();
		}
	}
	
	private void closeBufferedWriter()
	{
		try {
			writer.close();
		} catch (IOException e) {
			open();
			logger.info("Error closing BufferedWriter " + e);
			close();
		}
	}
	
	/**
	 * 
	 * @param lines An ArrayList where each element is printed on its own line
	 */
	public void write(ArrayList<String> lines)
	{
		getBufferedWriter();
		try {
			for (String line: lines)
			{
				writer.write(line);
				writer.newLine();
			}
			
		} catch (IOException e) {
			open();
			logger.info("Error writing to file" + e);
			close();
		}
		closeBufferedWriter();
		
	}
}
