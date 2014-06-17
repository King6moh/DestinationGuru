/**
 * The following is part of the dataManagement subsystem
 */
package dataManagement;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ErrorLog.ErrorLog;

/**
 * @author Mohammed Ahmed-Muhsin
 * @version 2.0 
 * @date 2014-06-16
 * 
 * The following class will test the functionality of the FileAttractionData class
 * In specific, we are testing that the attractions in the dummy test file are all being populated correctly
 * This includes the name, location, and the tags of each attraction
 *
 */
public class AttractionDataTester {

	private FileAttractionData testAttractionData;
	
	@Before
	/**
	 * This methoid will be called before every test to make sure we are working with a fresh FileAttractionData object
	 * at each instance of the test
	 */
	public void setUp() {
		testAttractionData = new FileAttractionData();
	}// end method
	
	@After
	/**
	 * After each method
	 */
	public void tearDown(){
		
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#FileAttractionData()}.
	 * This will test the constructor and ensure that it creates the proper fields and initializes them
	 * Will also ensure that the object that we create is a subclass of ErrorLog to ensure proper logging
	 */
	@Test
	public void testFileData() {
		
		assertTrue("Checking that we are extending the class properly from ErrorLog", testAttractionData instanceof ErrorLog);
		assertEquals("Checking that the attraction list is created", true, testAttractionData.getAttractionList()!=null);
		assertEquals("Checking that the hashtable is created", true, testAttractionData.getAttractionHash()!=null);
	}

	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we receive an attraction list and the size
	 */
	@Test
	public void testGetAttractionList() {

		assertTrue("Checking that we receive the correct type", testAttractionData.getAttractionList() instanceof ArrayList<?>);
		assertEquals("Checking that we have the correct number of attractions in the list", 13,  testAttractionData.getAttractionList().size());

	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper two attractions in our list
	 */
	@Test
	public void testAttractionListName() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the Eiffel Tower attraction is in the list", "Tour Eiffel", testAttractionList.get(0).getName());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper country for both attractions
	 */
	@Test
	public void testAttractionListCountry() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the correct country for the first attraction is in the list", "France", testAttractionList.get(0).getLocation().getCountry());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper city for both attractions
	 */
	@Test
	public void testAttractionListCity() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the correct country for the first attraction is in the list", "Paris", testAttractionList.get(0).getLocation().getCity());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper street for both attractions
	 */
	@Test
	public void testAttractionListStreet() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the correct street for the first attraction is in the list", "Avenue Anatole France", testAttractionList.get(0).getLocation().getStreet());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper address number for both attractions
	 */
	@Test
	public void testAttractionListAddressNumber() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the correct address number for the first attraction is in the list", 5, testAttractionList.get(0).getLocation().getAddressNum());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper latitude for both attractions
	 */
	@Test
	public void testAttractionListLatitude() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the correct latitude for the first attraction is in the list", 48.858539, testAttractionList.get(0).getLocation().getLatitude(), 3);
	}

	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper latitude for both attractions
	 */
	@Test
	public void testAttractionListLongitude() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that the correct longitude for the first attraction is in the list", 2.294508, testAttractionList.get(0).getLocation().getLongitude(), 4);
	}

	/**
	 * Test method for {@link dataManagement.FileAttractionData#getAttractionList()}.
	 * Checks to see if we have the proper amount of tags for both attractions
	 */
	@Test
	public void testAttractionListTags() {
		
		// save the list as a variable to start parsing 
		ArrayList<Attraction> testAttractionList = new ArrayList<Attraction>();
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking for correct number of tags for first attraction",  7, testAttractionList.get(0).getTags().size());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#updateAttractionList()}.
	 * Checks to see if we have the proper amount of tags for both attractions
	 */
	@Test
	public void testUpdateAttraction() {
		ArrayList<Attraction> testAttractionList;
		// create the location for our new attraction
		Location locationOrsay = new Location("France", "Paris", "Rue de la Legion d'Honneur", 1, 48.860159, 2.326561);
		
		// create the list of tags for the attraction
		ArrayList<String> tagsOrsay = new ArrayList<String>();
		tagsOrsay.add("museum");
		tagsOrsay.add("art");
		
		// create a new attraction to pass and update our list
		Attraction attractionOrsay = new Attraction(locationOrsay, "Musee D'Orsay", tagsOrsay);
		
		testAttractionData.updateAttractionList(attractionOrsay);
		testAttractionData.printAllAttractions();

		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that we have increased the number of attractions", 13, testAttractionList.size());		
		assertEquals("Checking that the Orsay Musee attraction is in the list", "Musee D'Orsay", testAttractionList.get(11).getName());
	}
	
	/**
	 * Test method for {@link dataManagement.FileAttractionData#updateAttractionList()}.
	 * Checks to add an invalid attraction name and ensures that the size stayed the same
	 */
	@Test
	public void testInvalidUpdateAttraction() {
		ArrayList<Attraction> testAttractionList;
		
		Attraction attractionInvalid = new Attraction("Invalid Name//#$");
		testAttractionData.updateAttractionList(attractionInvalid);
		
		testAttractionList = testAttractionData.getAttractionList();
		assertEquals("Checking that we have not increased the number of attractions", 13, testAttractionList.size());
		
	}
	
	/**
	 * Test method for {@link dataManagement.Attraction#incMatchedTags()}.
	 * Ensures that we clear the number of matched tags and that we increment it correctly
	 * This simulates our recommendation entity incrementing the popularity of that attraction and making it to the list
	 */
	@Test
	public void testMatchedTags() {
		ArrayList<Attraction> testAttractionList = testAttractionData.getAttractionList();
		
		Attraction attractionTest = testAttractionList.get(0);
		
		// this should set it to 0
		attractionTest.clearMatchedTags();
		assertEquals("Checking that we increment to a correct value", 1, attractionTest.incMatchedTags());
		
	}
	

}
