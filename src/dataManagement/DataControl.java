package dataManagement;

import java.util.ArrayList;
import java.util.Hashtable;

public class DataControl {

	FileData fileData;
	
	public DataControl()
	{
		fileData = new FileData();
	}
	
	public Hashtable<String, ArrayList<Attraction>> getList()
	{
		fileData.getFileData();
		return fileData.getAttractionHash();
	}
	
	public void updateList()
	{
		//not implemented yet
	}
	
	public void updateAlgorithm()
	{
		//not implemented yet
	}
	
}
