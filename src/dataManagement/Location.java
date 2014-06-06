package dataManagement;

public class Location {
	
	private String country;
	private String city;
	private String street;
	private int addressNum;
	private double latitude;
	private double longitude;

	/***
	 * Location constructor
	 * 
	 * @param country
	 * @param city
	 * @param street
	 * @param addressNum
	 * @param latitude
	 * @param longitude
	 */
	public Location(String country, String city, String street, int addressNum, double latitude, double longitude) {
		
		this.country = country;
		this.city = city;
		this.street = street;
		this.addressNum = addressNum;
		this.latitude = latitude;
		this.longitude = longitude;
		
	} //end constructor
} //end class
