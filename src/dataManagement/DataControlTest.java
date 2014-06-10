/**
 * 
 */
package dataManagement;

import static org.junit.Assert.*;
import java.util.Hashtable;
import org.junit.Test;

/**
 * @author Moh
 *
 */
public class DataControlTest {

	/**
	 * Test method for {@link dataManagement.DataControl#DataControl()}.
	 * Will test the constructor to ensure that the file that we get is initialized by checking if it is equal to null
	 */
	@Test
	public void testDataControl() {
		DataControl testDataControl = new DataControl();
		assertEquals("Checking if the fileData object is initialized in constructor", true, testDataControl.fileData!=null); 
	}

	/**
	 * Test method for {@link dataManagement.DataControl#getList()}.
	 * Will test the getList() method to ensure we are returning a HashTable -- the contents of the hashtable
	 * will then be tested in the FileDataTest JUnit tester
	 */
	@Test
	public void testGetList() {
		DataControl testDataControl = new DataControl();
		assertTrue(testDataControl.getList() instanceof Hashtable<?,?>);		
	}

	/**
	 * Test method for {@link dataManagement.DataControl#updateList()}.
	 * updateList() is not yet implemented in the DataControl.java program
	 */
	@Test
	public void testUpdateList() {
	}

	/**
	 * Test method for {@link dataManagement.DataControl#updateAlgorithm()}.
	 * updateAlgorithm() is not yet implemented in the DataControl.java program
	 */
	@Test
	public void testUpdateAlgorithm() {
	}

}
