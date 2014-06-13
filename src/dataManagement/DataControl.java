package dataManagement;

import java.util.ArrayList;
import java.util.Hashtable;

public class DataControl {

	FileAttractionData fileAttractionData;
	FileAlgorithmData fileAlgorithmData;
	
	public DataControl()
	{
		fileAttractionData = new FileAttractionData();
		fileAlgorithmData = new FileAlgorithmData();
	}
	
	public Hashtable<String, ArrayList<Attraction>> getList()
	{
		return fileAttractionData.getAttractionHash();
	}
	
	public void updateList(Attraction attraction)
	{
		fileAttractionData.updateAttractionList(attraction);
	}
	
	public int getAlgorithm()
	{
		return fileAlgorithmData.getSensitivity();
	}
	
	public void updateAlgorithm(int sensitivity)
	{
		fileAlgorithmData.updateAlgorithm(sensitivity);
	}
	
}
