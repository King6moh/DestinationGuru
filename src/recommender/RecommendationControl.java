package recommender;

import java.util.ArrayList;
import java.util.Enumeration;

import dataManagement.AttractionList;



public class RecommendationControl {

	private RecommendationEntity recommendation;
	private AttractionList attractionList;

	private int hnCounter, hhCounter;

	public static final String[] attractionTypes = {
		"Museums",
		"Natural Wonders",
		"Man Made Wonders",
		"Night Clubs",
		"Theme Parks",
		"Casinos",
		"Zoos",
		"Cultural",
		"Sporting Events",
	"Special Events"};

	public static final String[] attractionNames = {
		"The Louvre",
	"Musee d'Orsay"};

	public static final int NUM_HOT_OR_NOT = 1;
	public static final int NUM_HEAD_TO_HEAD = 1;

	/***
	 * RecommendationControl constructor
	 */
	public RecommendationControl() {

		recommendation = new RecommendationEntity();
		attractionList = new AttractionList();
		hnCounter = 0; 
		hhCounter = 0;

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
		if(hnCounter < NUM_HOT_OR_NOT){
			return attractionTypes[hnCounter++];
		} else {
			return "DONE";
		}
	}

	public String[] askHeadToHead(){
		String[] temp = new String[2];
		if(hhCounter < NUM_HEAD_TO_HEAD){
			temp[0] = attractionNames[hhCounter++];
			temp[1] = attractionNames[hhCounter++];
		} else {
			temp[0] = "DONE";
			temp[1] = "DONE";
		}
		return temp;
	}
} //end class