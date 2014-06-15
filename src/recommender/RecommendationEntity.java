package recommender;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import dataManagement.Attraction;

public class RecommendationEntity {

	private ArrayList<Attraction> recommendations;	
	private Hashtable<String, ArrayList<Attraction>> list;
	
	/***
	 * RecommendationEntity constructor
	 */
	public RecommendationEntity() {
		
		recommendations = new ArrayList<Attraction>();
		
	} // end constructor
	
	public RecommendationEntity(Hashtable<String, ArrayList<Attraction>> table) {
		list = table;
	}
	
	public void compileGeneralRecommendations(ArrayList<String> tags) {
		
	} //end compileGeneralRecommendations(tags)
	
	public void compileRecommendations(ArrayList<String> tags) {
		
	} //end compileRecommendations(tags)
	
	public Enumeration<String> getTags() {
		
		return list.keys();
		
	} //end getTags()
	
} //end class
