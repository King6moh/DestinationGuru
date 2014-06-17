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
	
	public void compileGeneralRecommendations(ArrayList<String> tags) {
		
		for (int i = 0; i < tags.size(); i++) {
			list.remove(tags.get(i));
		} //end for loop
		
		Enumeration<ArrayList<Attraction>> tempEnum = list.elements();
		ArrayList<Attraction> tempList;
		
		recommendations.clear();
		
		while(tempEnum.hasMoreElements()) {
			
			tempList = tempEnum.nextElement();
			
			while(tempList.iterator().hasNext()) {
				
				recommendations.add(tempList.iterator().next());
				
			} //end while
			
		} //end while
		
	} //end compileGeneralRecommendations(tags)
	
	public void compileRecommendations() {
		
	} //end compileRecommendations(tags)
	
	public Enumeration<String> getTags() {
		
		return list.keys();
		
	} //end getTags()
	
	public ArrayList<Attraction> getAttractions() {
		
		return recommendations;
		
	} //end getAttractions()
	
	public void incMatchedTags(String tag) {
		for (Attraction attr : list.get(tag)) {
			attr.incMatchedTags();
		}
	}
	
} //end class
