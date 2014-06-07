package dataManagement;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Daniel
 *A wrapper class for basic file IO. 
 *Each instance is matched to one file.
 *Opening and closing the BUfferedReader are built in to getLines()
 * *Will probably be made more advanced later.
 */
public class FileIO {

	/*note: using a BufferedReader and a BufferedOutputStream is kind of weird because they don't match,
	 * but Reader is good for reading in lines at a time, which I think we want.
	 * If we don't, looking into a BufferedInputStream and other output stuff might be necessary.
	 */
	private String fileName;
	private File file;
	private BufferedReader reader;
	
	private BufferedOutputStream bufOutStream;
	private FileOutputStream fileOutStream;
	
	/**
	 * 
	 * @param fileName The filename with which IO will be performed
	 * Gets a BufferedReader for the file
	 */
	public FileIO(String fileName)
	{
		this.fileName = fileName;
		file = new File(fileName);
		//reader = getBufferedReader();
	}
	
	
	/**
	 * 
	 * get a BufferedReader for the File file.
	 */
	public void getBufferedReader()
	{
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("Error creating fileReader " + e);
		}
	}
	
	/**
	 * Closes the BufferedReader
	 */
	public void closeBufferedReader()
	{
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("Error closing BufferedReader" + e);
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
			System.out.println("Error reading from file " + e);
		}
		closeBufferedReader();
		return lineArray;
	}
	
	public void getOutputStream()
	{
		try{
			fileOutStream = new FileOutputStream(fileName);
			bufOutStream = new BufferedOutputStream(fileOutStream);
		}catch (FileNotFoundException fnfe) {
			System.out.println("File not found" + fnfe);
		}
	}
	
	public void closeOutputStream()
	{
		try{
			if (bufOutStream != null) {
				bufOutStream.flush();
				bufOutStream.close();
			}
		}catch (Exception e) {
			System.out.println("Error while closing streams" + e);
		}
	}
	
	/**
	 * 
	 * @param byteArray What will be written to the file
	 * opens and closes the output streams
	 */
	public void write(byte[] byteArray)
	{
		getOutputStream();
		try {
			bufOutStream.write(byteArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error writing to file " + fileName + " " + e);
		}
		closeOutputStream();
	}
	
	public static void main(String[] args) {
		//test the reading capabilities
		/*
		FileIO testIO = new FileIO("testRead.txt");
		ArrayList<String> lineArray = new ArrayList<String>();
		lineArray = testIO.getLines();
		for (String s : lineArray)
		{
			System.out.println(s);
		}
		*/
		
		//test the writing capabilities
		/*
		byte[] testArray = ("The" + "\n" + "Output" + "\n" + "Test" + "\n" + "Is" + "\n" + "Working").getBytes();
		FileIO testIO = new FileIO("TestWrite.txt");
		testIO.write(testArray);
		*/
	}

}
