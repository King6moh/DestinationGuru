package dataManagement;

import java.util.ArrayList;

public class AttractionList {
	
	private ArrayList<Attraction> attractionList;
	
	/***
	 * AttractionList constructor
	 */
	public AttractionList() {
		
		attractionList = new ArrayList<Attraction>();
	
	} //end constructor
	
	/***
	 * Method invoked by an administrator to
	 * update the list of attractions
	 */
	public void updateList() {} //end method
	
	public ArrayList<Attraction> getList() {
		
		return attractionList;
		
	} //end method
} //end class
