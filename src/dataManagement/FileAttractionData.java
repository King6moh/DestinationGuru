package dataManagement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ErrorLog.ErrorLog;;


/**
 * 
 * @author Daniel
 *This class uses a FileIO object to read all the data from a text file called attractionData.txt
 *The data has a specific format.
 *It parses the file and creates an ArrayList of the attractions whose information is in the file
 *It puts all the information from the file into the appropriate fields of the Attraction and Location classes
 */
public class FileAttractionData extends ErrorLog {

	private FileIO file;
	private ArrayList<Attraction> attractionList;	
	private Hashtable<String, ArrayList<Attraction>> tagTable;
	
	public FileAttractionData()
	{
		super();
		file = new FileIO("attractionData.dg");
		attractionList = new ArrayList<Attraction>();
		tagTable = new Hashtable<String, ArrayList<Attraction>>();
		getFileData();
	}
	
	/**
	 * parses the file and creates an Attraction object for every entry with a name
	 * All the Attraction objects are placed in the attractionList
	 * Currently it prints out the attractionList information to illustrate what the method does
	 * The file format is basically each line is a tuple.
	 * A line looks like <name><Eiffel Tower> or <tag><museum>.
	 * The first <> must be a word, but the second <> can be any combination of letters, spaces, numbers, or periods.
	 */
	private void getFileData()
	{
		//reset attractionList
		ArrayList<Attraction> newList = new ArrayList<Attraction>();
		attractionList = newList;
		//reset tagTable
		Hashtable<String, ArrayList<Attraction>> newHash = new Hashtable<String, ArrayList<Attraction>>();
		tagTable = newHash;
		Attraction attraction = null;
		ArrayList<String> fileList = null;
		fileList = file.getLines();
		String input = "";
		Pattern basicPattern = Pattern.compile("<(\\w+)><([a-zA-Z0-9'\\.\\s]+)>");
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
					}
				}else {
					logger.info("Unrecognized identifier: " + matcher.group(1));
				}
			}else {
				if (!line.equals(""))
				{
					System.out.println("line did not match: " + line);
					logger.info("line did not match: " + line);
				}	
			}
		}
		
	}
	
	//
	/**
	 * updates one attraction at a time. I believe this is what we want.
	 * @param attraction The attraction to be added to the file
	 */
	public void updateAttractionList(Attraction attraction)
	{
		Attraction changed = null;
		for (Attraction a: attractionList)
		{
			if (a.getName().equals(attraction.getName()))
			{
				changed = a;
				break;
			}
		}
		if (changed == null) //new attraction
		{
			attractionList.add(attraction);
		}else { //update attraction
			changed.setName(attraction.getName());
			changed.setLocation(attraction.getLocation());
			changed.setTags(attraction.getTags());
		}
		
		writeAttractionList();
	}
	
	/**
	 * writes the attractionList to file in the appropriate format
	 */
	private void writeAttractionList()
	{
		ArrayList<String> update = new ArrayList<String>();
		for (Attraction a: attractionList)
		{
			update.add("<name><" + a.getName() + ">");
			Location l = null;
			l = a.getLocation();
			update.add("<country><"  +l.getCountry() + ">");
			update.add("<city><"  +l.getCity() + ">");
			update.add("<street><"  +l.getStreet() + ">");
			update.add("<addressNumber><"  +String.valueOf(l.getAddressNum()) + ">");
			update.add("<latitude><"  +l.getLatitude() + ">");
			update.add("<longitude><"  +l.getLongitude() + ">");
			for (String tag: a.getTags())
			{
				update.add("<tag><" + tag + ">");
			}
			update.add("");
			
		}
		
		file.write(update);
	}
	
	public void printAllAttractions()
	{
		for (Attraction att: attractionList)
		{
			att.printInfo();
			System.out.println("");
		}
	}
	
	public ArrayList<Attraction> getAttractionList()
	{
		getFileData();
		return attractionList;
	}
	
	public Hashtable<String, ArrayList<Attraction>> getAttractionHash()
	{
		getFileData();
		return tagTable;
	}
	

}
