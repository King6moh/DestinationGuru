package recommender;

import dataManagement.AttractionList;

public class RecommendationControl {
	
	private RecommendationEntity recommendation;
	private AttractionList attractionList;
	
	/***
	 * RecommendationControl constructor
	 */
	public RecommendationControl() {
		
		recommendation = new RecommendationEntity();
		attractionList = new AttractionList();
		
	} //end constructor
} //end class
