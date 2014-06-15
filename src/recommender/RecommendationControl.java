package recommender;

import java.util.ArrayList;
import java.util.Enumeration;

import dataManagement.AttractionList;



public class RecommendationControl {
	
	private RecommendationEntity recommendation;
	private AttractionList attractionList;
	
	private int counter;
	
	public static final String[] attractionListNames = {"Museums",
	                                                    "Natural Wonders",
	                                                    "Man Made Wonders",
	                                                    "Night Clubs",
	                                                    "Theme Parks",
	                                                    "Casinos",
	                                                    "Zoos",
	                                                    "Cultural",
	                                                    "Sporting Events",
	                                                    "Special Events"};
	public static final int NUM_HOT_OR_NOT = 10;
	public static final int NUM_HEAD_TO_HEAD = 10;
	
	/***
	 * RecommendationControl constructor
	 */
	public RecommendationControl() {
		
		recommendation = new RecommendationEntity();
		attractionList = new AttractionList();
		counter = 0;
		
	} //end constructor
	
	public RecommendationEntity getRecommendation() {
		
		recommendation.compileGeneralRecommendations(HotOrNot());
		
		HeadToHead();
		//recommendation.compileRecommendations(tags);
		
		return recommendation;
	} //end getRecommendation()
	
	private ArrayList<String> HotOrNot() {
		
		ArrayList<String> tags = new ArrayList<String>();
		Enumeration<String> tagList = recommendation.getTags();
		String tempTag;
		
		for (int i = 0; i < NUM_HOT_OR_NOT; i++) {
			
			tempTag = tagList.nextElement();
			/*
			if (client.askHotOrNot(tempTag)) {
				tags.add(tempTag);
			}
			*/
			
		} //end for loop

		return tags;
	} //end HotOrNot()
	
	private void HeadToHead() {
		
	} //end HeadToHead()
	
	public String askHotOrNot(){
		if(counter < NUM_HOT_OR_NOT){
			return attractionListNames[counter++];
		} else {
			return null;
		}
	}
	
	public void askHeadToHead(){
		
	}
} //end class