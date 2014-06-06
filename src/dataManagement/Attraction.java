package dataManagement;
import java.util.ArrayList;


public class Attraction {

	private Location location;
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
		
	} //end constructor
} //end class
