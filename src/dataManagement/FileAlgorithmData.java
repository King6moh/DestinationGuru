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
	private AlgorithmParameters algorithm;
	
	
	public FileAlgorithmData()
	{
		super();
		file = new FileIO("algorithmData.dg");
		algorithm = new AlgorithmParameters();
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
					algorithm.sensitivity = Integer.valueOf(matcher.group(2));
				}else if (matcher.group(1).equals("numHeadToHead"))
				{
					algorithm.numHH = Integer.valueOf(matcher.group(2));
				}else if (matcher.group(1).equals("numHotOrNot"))
				{
					algorithm.numHN = Integer.valueOf(matcher.group(2));
				}
			}
		}
	}
	
	public AlgorithmParameters getAlgorithm()
	{
		getFileData();
		return algorithm;
	}
	
	public void printAlgorithm()
	{
		System.out.println("Sensitivity: " + algorithm.sensitivity);
		System.out.println("NumHeadToHead: " + algorithm.numHH);
		System.out.println("NumHotOrNot: " + algorithm.numHN);
	}
	
	public void updateAlgorithm(AlgorithmParameters newAlgorithm)
	{
		ArrayList<String> update = new ArrayList<String>();
		update.add("<sensitivity><" + String.valueOf(newAlgorithm.sensitivity) + ">");
		update.add("<numHeadToHead><" + String.valueOf(newAlgorithm.numHH) + ">");
		update.add("<numHotOrNot><" + String.valueOf(newAlgorithm.numHN) + ">");
		file.write(update);
	}

}
