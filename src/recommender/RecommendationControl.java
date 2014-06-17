package recommender;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import customerBoundary.CustomerBoundary;
import dataManagement.AlgorithmParameters;
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
	private AlgorithmParameters algorithmParam;
	
	private String currentTag;

	/***
	 * RecommendationControl constructor
	 */

	public RecommendationControl(CustomerBoundary client) {

		dataManagement = new DataControl();
		recommendation = new RecommendationEntity(dataManagement.getAlgorithm().sensitivity, dataManagement.getList());
		this.client = client;
		hnCounter = 0; 
		hhCounter = 0;
		tagCount = 0;
		algorithmParam = dataManagement.getAlgorithm();
		tagList = recommendation.getTags();
		tags = new ArrayList<String>();
		currentTag = new String();

	} //end constructor

	private Hashtable<String, ArrayList<Attraction>> getAttractionTable()
	{
		return dataManagement.getList();
	}

	public String askHotOrNot(){
		if(hnCounter < algorithmParam.numHN){
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

			//System.out.println(currentTag);
			if((hhCounter < algorithmParam.numHH) && !currentTag.equals("DONE")){

				System.out.println(currentTag);
				if((hhCounter < algorithmParam.numHH) && !currentTag.equals("DONE")){
					hhCounter++;
					//Iterator<Attraction> iter = recommendation.getList().get(currentTag).iterator();
					ArrayList<Attraction> attList = recommendation.getList().get(currentTag);
					Random r = new Random();
					int index0, index1;

					index0 = r.nextInt(attList.size());
					index1 = index0;

					temp[0] = attList.get(index0);
					if(attList.size() == 1){
						temp[1] = null;
					} else {
						while(index1 == index0){
							index1 = r.nextInt(attList.size());
						}
						temp[1] = attList.get(index1);
					}

					//if(iter.hasNext()) temp[0] = (Attraction) iter.next();
					//else temp[0] = null;

					//if(iter.hasNext()) temp[1] = (Attraction) iter.next();
					//else temp[1] = null;
				} else {
					temp[0] = null;
					temp[1] = null;
				}
			}
		}
		return temp;
	}

	public String getCurrentTag(){
		return currentTag;
	}
	
	public void headToHead(Attraction attr1, Attraction attr2) {
		if (attr1 != null) {
			//recommendation.addAttraction(attr1);
			for (String tag : attr1.getTags()){
				tags.add(tag);
			}
		}
		if (attr2 != null) {
			//recommendation.addAttraction(attr2);
			for (String tag : attr2.getTags()){
				tags.add(tag);
			}
		}
	}
	
	public void updateRecomEntity(){
		recommendation.desiredTagsSet(tags);
	}
	
} //end class
