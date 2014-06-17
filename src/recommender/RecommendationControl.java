package recommender;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

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
