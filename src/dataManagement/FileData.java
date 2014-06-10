package dataManagement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ErrorLog.ErrorLog;;


/**
 * 
 * @author Daniel
 *This class uses a FileIO class to read all the data from a text file called attractionData.txt
 *The data has a specific format.
 *It parses the file and creates an ArrayList of the attractions whose information is in the file
 *It puts all the information from the file into the appropriate fields of the Attraction and Location classes
 */
public class FileData extends ErrorLog {

	private FileIO file;
	private ArrayList<Attraction> attractionList;	
	private Hashtable<String, ArrayList<Attraction>> tagTable;
	
	public FileData()
	{
		super();
		file = new FileIO("attractionData.txt");
		attractionList = new ArrayList<Attraction>();
		tagTable = new Hashtable<String, ArrayList<Attraction>>();
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
					
					/*
					 * Ideally, hashtable should look roughly like this when finished reading in data
					 * |	TAG		|	Attractions				|
					 * | museum		|museum of civ., war museum |
					 * | beach		| mooneys bay, sandbanks	|
					 * |historic	| museum of civ				|
					 * 
					 * where each attraction now has a matchedTag field.
					 * 
					 * To check for a certain number of matches, we can simply check the value
					 * of this field once we update it, hence the incMatchedTags() method in Attraction, 
					 * which will return this value once it finishes, letting us immediately check if
					 * the attraction has met the quota for matched tags (and ensuring we don't have to
					 * iterate through the whole list of Attractions to determine which ones have matched
					 * enough tags.
					 */
					
					ArrayList<Attraction> tempList = tagTable.get(matcher.group(2));
					if(tempList == null){tempList = new ArrayList<Attraction>();}
					if(!tempList.contains(attraction)){
						tempList.add(attraction);
						tagTable.put(matcher.group(2), tempList);
					} else {/* Do nothing */}
				}else {
					//replace this with a write to the error log once we have an error log
					//System.out.println("Unrecognized identifier: " + matcher.group(1));
					logger.info("Unrecognized identifier: " + matcher.group(1));
				}
			}else {
				System.out.println("line did not match: " + line);
				logger.info("line did not match: " + line);
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
	
	public Hashtable<String, ArrayList<Attraction>> getAttractionHash()
	{
		return tagTable;
	}
	
	//for testing
	public static void main(String[] args) {
		FileData fileData = new FileData();
		fileData.getFileData();
	}

}
