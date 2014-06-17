package recommender;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import com.sun.security.ntlm.Client;

import customerBoundary.CustomerBoundary;
import dataManagement.Attraction;
import dataManagement.DataControl;

public class RecommendationControl {

	private RecommendationEntity recommendation;
	private DataControl dataManagement;
	private CustomerBoundary client;
	
	private Enumeration<String> tagList;
	ArrayList<String> tags;
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

	public static final int NUM_HOT_OR_NOT = 10;
	public static final int NUM_HEAD_TO_HEAD = 10;

	/***
	 * RecommendationControl constructor
	 */

	public RecommendationControl(CustomerBoundary client) {

		dataManagement = new DataControl();
		recommendation = new RecommendationEntity(dataManagement.getList());
		this.client = client;
		hnCounter = 0; 
		hhCounter = 0;
		tagList = recommendation.getTags();
		tags = new ArrayList<String>();

	} //end constructor

	public RecommendationEntity getRecommendation() {

		recommendation.compileGeneralRecommendations(HotOrNot());

		HeadToHead();
		recommendation.compileRecommendations();
		//recommendation.compileRecommendations(tags);

		return recommendation;
	} //end getRecommendation()

	private Hashtable<String, ArrayList<Attraction>> getAttractionTable()
	{
		return dataManagement.getList();
	}
	
	private ArrayList<String> HotOrNot() {

		Enumeration<String> tagList = recommendation.getTags();
		String tempTag;

		for (int i = 0; i < NUM_HOT_OR_NOT; i++) {

			while(tagList.hasMoreElements()) {
				tempTag = tagList.nextElement();
				
				if (!client.askHotOrNot(tempTag)) {
					tags.add(tempTag);
				} //end if
				else {
					recommendation.incMatchedTags(tempTag);
				} //end else
				
			} //end while
			
		} //end for loop

		return tags;
	} //end HotOrNot()

	private void HeadToHead() {

		ArrayList<Attraction> h2h = recommendation.getAttractions();
		int answer = 0;
		Attraction attraction_1;
		Attraction attraction_2;
		
		for (int i = 0; i < NUM_HEAD_TO_HEAD; i++) {
			
			while(h2h.iterator().hasNext()) {
				attraction_1 = h2h.iterator().next();
				attraction_2 = h2h.iterator().next();
				answer = client.askHeadToHead(attraction_1, attraction_2);
				/*
				if (answer == 1) {
					h2h.remove(attraction_2);
				 }
				else if (answer == 2) {
					h2h.remove(attraction_1);
				}
				*/
			} //end while
			
		} //end for loop
		
	} //end HeadToHead()

	public String askHotOrNot(){
		if(hnCounter < NUM_HOT_OR_NOT){
			hnCounter++;
			return tagList.nextElement();
		} else {
			return "DONE";
		}
	}
	
	public void hot(String tag) {
		recommendation.incMatchedTags(tag);
	}
	
	public void not(String tag) {
		tags.add(tag);
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
