package recommender;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import com.sun.security.ntlm.Client;

import customerBoundary.CustomerBoundary;
import dataManagement.Attraction;
import dataManagement.DataControl;

public class RecommendationControl {

	private RecommendationEntity recommendation;
	private DataControl dataManagement;
	private CustomerBoundary client;

	private Enumeration<String> tagList;
	private ArrayList<String> tags;
	private int tagCount;
	private int hnCounter, hhCounter;
	
	private String currentTag;

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
		tagCount = 0;
		tagList = recommendation.getTags();
		tags = new ArrayList<String>();
		currentTag = new String();

	} //end constructor

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
		tags.add(tag);
	}

	public Attraction[] askHeadToHead(){
		Attraction[] temp = new Attraction[2];
		if(tagCount < tags.size()){
			currentTag = tags.get(tagCount++);
			System.out.println(currentTag);
			if((hhCounter < NUM_HEAD_TO_HEAD) && !currentTag.equals("DONE")){
				hhCounter++;
				Iterator<Attraction> iter = recommendation.getList().get(currentTag).iterator();
				
				if(iter.hasNext()) temp[0] = (Attraction) iter.next();
				else temp[0] = null;
				
				if(iter.hasNext()) temp[1] = (Attraction) iter.next();
				else temp[1] = null;
			} else {
				temp[0] = null;
				temp[1] = null;
			}
		}
		return temp;
	}

	public String getCurrentTag(){
		return currentTag;
	}
	
	public void headToHead(Attraction attr1, Attraction attr2) {
		if (attr1 != null) {
			recommendation.addAttraction(attr1);
		}
		if (attr2 != null) {
			recommendation.addAttraction(attr2);
		}
	}
} //end class
