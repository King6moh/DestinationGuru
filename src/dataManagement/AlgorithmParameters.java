package dataManagement;

public class AlgorithmParameters {

	public int sensitivity ;
	public int numHH;
	public int numHN;
	
	public AlgorithmParameters()
	{
		sensitivity = 0;
		numHH = 0;
		numHN = 0;
	}
	
	public AlgorithmParameters(int sensitivity, int numHH, int numHN)
	{
		this.sensitivity = sensitivity;
		this.numHH = numHH;
		this.numHN = numHN;
	}
	
}
