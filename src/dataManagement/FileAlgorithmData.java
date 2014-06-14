package dataManagement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ErrorLog.ErrorLog;


/**
 * 
 * @author Daniel
 *Uses a FileIO object to read/write from a file called algorithmData.dg
 */
public class FileAlgorithmData extends ErrorLog {

	private FileIO file;
	private int sensitivity;
	
	
	public FileAlgorithmData()
	{
		super();
		file = new FileIO("algorithmData.dg");
		sensitivity = 0;
	}
	
	/**
	 * reads the sensitivity value from the algorithData file.
	 * Right now the algorithm data file structure is simple, but this setup 
	 * can easily be expanded to read from more complex file structures 
	 */
	private void getFileData()
	{
		ArrayList<String> fileList = null;
		fileList = file.getLines();
		Pattern basicPattern = Pattern.compile("<(\\w+)><(\\d+)>");
		for (String line: fileList)
		{
			Matcher matcher = basicPattern.matcher(line);
			if (matcher.find())
			{
				if (matcher.group(1).equals("sensitivity"))
				{
					sensitivity = Integer.valueOf(matcher.group(2));
				}
			}
		}
	}
	
	public int getSensitivity()
	{
		getFileData();
		return sensitivity;
	}
	
	public void updateAlgorithm(int sensitivity)
	{
		ArrayList<String> update = new ArrayList<String>();
		update.add("<sensitivity><" + String.valueOf(sensitivity) + ">");
		file.write(update);
	}
	
	public static void main(String[] args) {
		//test
		
		FileAlgorithmData testData = new FileAlgorithmData();
		testData.updateAlgorithm(2);
		System.out.println("Sensitivity is: " + testData.getSensitivity());
		

	}

}
