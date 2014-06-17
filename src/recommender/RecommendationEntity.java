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
		recommendations = new ArrayList<Attraction>();
		list = table;
	}
	
	public void compileRecommendations() {
		
	} //end compileRecommendations(tags)
	
	public Enumeration<String> getTags() {
		
		return list.keys();
		
	} //end getTags()
	
	public Hashtable<String, ArrayList<Attraction>> getList() {
		return list;
	}
	
	public ArrayList<Attraction> getAttractions() {
		
		return recommendations;
		
	} //end getAttractions()
	
	public void addAttraction(Attraction attr) {
		recommendations.add(attr);
	}
	
	public void incMatchedTags(String tag) {
		if (list.containsKey(tag)) {
			for (Attraction attr : list.get(tag)) {
				attr.incMatchedTags();
			}
		}
	}
	
} //end class
