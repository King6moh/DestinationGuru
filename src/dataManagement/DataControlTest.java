/**
 * The following is part of the dataManagement subsystem
 */
package dataManagement;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mohammed Ahmed-Muhsin
 * @version 2.0 
 * @date 2014-06-14
 * 
 * The following class will test the functionality of the DataControl class
 * In specific, we are testing that the class initializes properly and that we get the list of the attractions
 *
 */
public class DataControlTest {
	
	private DataControl testDataControl;

	@Before
	/**
	 * This method will be called before every test to make sure we are working with a fresh DataControl object 
	 * at each instance of the test
	 */
	public void setUP() {
		testDataControl = new DataControl();
		
	}
	/**
	 * Test method for {@link dataManagement.DataControl#DataControl()}.
	 * Will test the constructor to ensure that the file that we get is initialized by checking if it is equal to null
	 */
	@Test
	public void testDataControl() {
		assertEquals("Checking if the fileAttractionData object is initialized in constructor", true, testDataControl.fileAttractionData!=null); 
		assertEquals("Checking if the fileAlgorithmData object is initialized in constructor", true, testDataControl.fileAlgorithmData!=null); 
	}

	/**
	 * Test method for {@link dataManagement.DataControl#getList()}.
	 * Will test the getList() method to ensure we are returning a HashTable -- the contents of the hashtable
	 * will then be tested in the FileDataTest JUnit tester
	 */
	@Test
	public void testGetList() {
		assertTrue("Checking that we return a Hashtable", testDataControl.getList() instanceof Hashtable<?,?>);		
	}

	/**
	 * Test method for {@link dataManagement.DataControl#updateList()}.
	 * updateList() is not yet implemented in the DataControl.java program
	 */
	@Test
	public void testUpdateList() {
	}
	
	/**
	 * Test method for {@link dataManagement.DataControl#getAlgorithm()}.
	 * Will test the getAlgorithm() method to ensure we receive an int value
	 */
	@Test
	public void testGetAlgorithm() {
		testDataControl.updateAlgorithm(3);
		assertTrue("Check that we return the sensitivity 3", testDataControl.getAlgorithm() == 3);
	}

	/**
	 * Test method for {@link dataManagement.DataControl#updateAlgorithm()}.
	 * updateAlgorithm() is not yet implemented in the DataControl.java program
	 */
	@Test
	public void testUpdateAlgorithm() {
	}
	
	@After
	public void tearDown() {
	}

}
