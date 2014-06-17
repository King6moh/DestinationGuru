package recommender;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

import dataManagement.Attraction;

public class RecommendationEntity {

	private ArrayList<Attraction> recommendations, finalRecoms;	
	private Hashtable<String, ArrayList<Attraction>> list;
	private HashSet<String> desiredTags;
	private int sensitivity;
	
	/***
	 * RecommendationEntity constructor
	 */
	public RecommendationEntity() {
		
		recommendations = new ArrayList<Attraction>();
		
	} // end constructor
	
	public RecommendationEntity(int sensitivity, Hashtable<String, ArrayList<Attraction>> table) {
		recommendations = new ArrayList<Attraction>();
		finalRecoms = new ArrayList<Attraction>();
		list = table;
		this.sensitivity = sensitivity;
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
	
	public void desiredTagsSet(ArrayList<String> tagList){
		desiredTags = new HashSet<String>(tagList);
		desiredTags.remove("DONE");
		
		for (String tag: desiredTags){
			System.out.println("tag:" + tag);
			for (Attraction att: list.get(tag)){
				if(att.incMatchedTags() >= sensitivity){
					finalRecoms.add(att);
				}
			}
		}
	}
	
	public ArrayList<Attraction> getFinalRecoms(){
		return finalRecoms;
	}
	
} //end class
