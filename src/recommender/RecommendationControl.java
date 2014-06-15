package recommender;

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