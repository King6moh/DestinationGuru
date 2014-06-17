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
 * @version 1.0 
 * @date 2014-06-16
 * 
 * The following class will test the functionality of the FileAttractionData class
 * In specific, we are testing that the attractions in the dummy test file are all being populated correctly
 * This includes the name, location, and the tags of each attraction
 *
 */
public class FileAttractionDataTester {

	private FileAttractionData testAttractionData;
	
	@Before
	/**
	 * This methoid will be called before every test to make sure we are working with a fresh FileAttractionData object
	 * at each instance of the test
	 */
	public void setUp() {
		testAttractionData = new FileAttractionData();
	}// end method
	
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
	 * Checks to see if we receive an attraction list and of the expected size
	 */
	@Test
	public void testGetAttractionList() {

		assertTrue("Checking that we receive the correct type", testAttractionData.getAttractionList() instanceof ArrayList<?>);
		assertEquals("Checking that we have the correct number of attractions in the list", 3,  testAttractionData.getAttractionList().size());
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
		assertEquals("Checking that the Eiffel Tower attraction is in the list", "Eiffel Tower", testAttractionList.get(0).getName());
		assertEquals("Checking that the The Louvre  attraction is in the list", "The Louvre", testAttractionList.get(1).getName());
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
		assertEquals("Checking that the correct country for the second attraction is in the list", "France", testAttractionList.get(1).getLocation().getCountry());
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
		assertEquals("Checking that the correct country for the second attraction is in the list", "Paris", testAttractionList.get(1).getLocation().getCity());
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
		assertEquals("Checking that the correct street for the first attraction is in the list", "Champ de Mars", testAttractionList.get(0).getLocation().getStreet());
		assertEquals("Checking that the correct street for the second attraction is in the list", "Champ de Mars", testAttractionList.get(1).getLocation().getStreet());
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
		assertEquals("Checking that the correct address number for the second attraction is in the list", 12, testAttractionList.get(1).getLocation().getAddressNum());
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
		assertEquals("Checking that the correct latitude for the first attraction is in the list", 4851296, testAttractionList.get(0).getLocation().getLatitude(), 3);
		assertEquals("Checking that the correct latitude for the second attraction is in the list", 1111.9999, testAttractionList.get(1).getLocation().getLatitude(), 3);
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
		assertEquals("Checking that the correct longitude for the first attraction is in the list", 217402, testAttractionList.get(0).getLocation().getLongitude(), 4);
		assertEquals("Checking that the correct longitude for the second attraction is in the list", 9999.1111, testAttractionList.get(1).getLocation().getLongitude(), 4);
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
		assertEquals("Checking for correct number of tags for second attraction", 8, testAttractionList.get(1).getTags().size());
	}

}
