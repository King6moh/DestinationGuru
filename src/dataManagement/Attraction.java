package dataManagement;
import java.util.ArrayList;


public class Attraction {

	public Location location;
	private String name;
	private ArrayList<String> tags;
	
	/***
	 * Attraction constructor
	 * 
	 * @param location
	 * @param name
	 * @param tags
	 */
	public Attraction (Location location, String name, ArrayList<String> tags) {
		
		this.location = location;
		this.name = name;
		this.tags = tags;
		
	}
	
	public Attraction(String name)
	{
		tags = new ArrayList<String>();
		location = new Location();
		this.name = name; 
	}
	
	public Attraction()
	{
		tags = new ArrayList<String>();
		location = new Location();
		name = null; 
	}
	
	//instead should override that method that lets you print the object directly
	public void printInfo()
	{
		System.out.println("name: " + name);
		System.out.println("Location information:");
		location.printInfo();
		System.out.println("tags:");
		for (String tag: tags)
		{
			System.out.println("\t" + tag);
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	public void addTag(String tag)
	{
		tags.add(tag);
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<String> getTags()
	{
		return tags;
	}
} //end class
