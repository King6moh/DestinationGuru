package recommender;

import Client.ClientBoundary;

import java.util.ArrayList;
import java.util.Enumeration;

import com.sun.security.ntlm.Client;

import dataManagement.Attraction;
import dataManagement.AttractionList;

public class RecommendationControl {

	private RecommendationEntity recommendation;
	private AttractionList attractionList;
	private ClientBoundary client;

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
	public RecommendationControl(ClientBoundary client) {

		recommendation = new RecommendationEntity();
		attractionList = new AttractionList();
		this.client = client;
		hnCounter = 0; 
		hhCounter = 0;

	} //end constructor

	public RecommendationEntity getRecommendation() {

		recommendation.compileGeneralRecommendations(HotOrNot());

		HeadToHead();
		recommendation.compileRecommendations();

		return recommendation;
	} //end getRecommendation()

	private ArrayList<String> HotOrNot() {

		ArrayList<String> tags = new ArrayList<String>();
		Enumeration<String> tagList = recommendation.getTags();
		String tempTag;

		for (int i = 0; i < NUM_HOT_OR_NOT; i++) {

			while(tagList.hasMoreElements()) {
				tempTag = tagList.nextElement();
				/*
				if (!client.askHotOrNot(tempTag)) {
					tags.add(tempTag);
				} //end if
				else {
					recommendation.incMatchedTags();
				} //end else
				*/
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