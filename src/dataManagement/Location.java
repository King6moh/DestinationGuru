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
	
	public Location()
	{
		addressNum = 0;
	}
	
	public void printInfo()
	{
		System.out.println("country: " + country);
		System.out.println("city: " + city);
		System.out.println("street: "  + street);
		System.out.println("addressNum: "  + addressNum);
		System.out.println("latitude: "  + latitude);
		System.out.println("longitude: " + longitude);
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public void setAddressNum(int addressNum)
	{
		this.addressNum = addressNum;
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public int getAddressNum()
	{
		return addressNum;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
} //end class
