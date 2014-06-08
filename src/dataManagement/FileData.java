package dataManagement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author Daniel
 *This class uses a FileIO class to read all the data from a text file called attractionData.txt
 *The data has a specific format.
 *It parses the file and creates an ArrayList of the attractions whose information is in the file
 *It puts all the information from the file into the appropriate fields of the Attraction and Location classes
 */
public class FileData {

	private FileIO file;
	private ArrayList<Attraction> attractionList;
	
	public FileData()
	{
		file = new FileIO("attractionData.txt");
		attractionList = new ArrayList<Attraction>();
	}
	
	/**
	 * parses the file and creates an Attraction object for every entry with a name
	 * All the Attraction objects are placed in the attractionList
	 * Currently it prints out the attractionList information to illustrate what the method does
	 * The file format is basically each line is a tuple.
	 * A line looks like <name><Eiffel Tower> or <tag><museum>.
	 * The first <> must be a word, but the second <> can be any combination of letters, spaces, numbers, or periods.
	 */
	public void getFileData()
	{
		Attraction attraction = null;
		ArrayList<String> fileList = null;
		fileList = file.getLines();
		String input = "";
		Pattern basicPattern = Pattern.compile("<(\\w+)><([a-zA-Z0-9\\.\\s]+)>");
		for (String line: fileList)
		{
			Matcher matcher = basicPattern.matcher(line);
			if (matcher.find())
			{
				if (matcher.group(1).equals("name"))
				{
					attraction = new Attraction();
					attractionList.add(attraction);
					attraction.setName(matcher.group(2));
				}else if (matcher.group(1).equals("country"))
				{
					attraction.location.setCountry(matcher.group(2));
				}else if (matcher.group(1).equals("city"))
				{
					attraction.location.setCity(matcher.group(2));
				}else if (matcher.group(1).equals("street"))
				{
					attraction.location.setStreet(matcher.group(2));
				}else if (matcher.group(1).equals("addressNumber"))
				{
					try {
						attraction.location.setAddressNum(Integer.valueOf(matcher.group(2)));
					}catch (Exception e) {
						System.out.println(e);
					}
				}else if (matcher.group(1).equals("latitude"))
				{
					attraction.location.setLatitude(Double.valueOf(matcher.group(2)));
				}
				else if (matcher.group(1).equals("longitude"))
				{
					attraction.location.setLongitude(Double.valueOf(matcher.group(2)));
				}else if (matcher.group(1).equals("tag"))
				{
					attraction.addTag(matcher.group(2));
				}else {
					//replace this with a write to the error log once we have an error log
					System.out.println("Unrecognized identifier: " + matcher.group(1));
				}
			}else {
				System.out.println("line did not match: " + line);
			}
		}
		for (Attraction att: attractionList)
		{
			att.printInfo();
			System.out.println("");
		}
		
	}
	
	public ArrayList<Attraction> getAttractionList()
	{
		return attractionList;
	}
	
	//for testing
	public static void main(String[] args) {
		FileData fileData = new FileData();
		fileData.getFileData();
	}

}
