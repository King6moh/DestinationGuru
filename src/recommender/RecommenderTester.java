/**
 * The following is part of the recommender subsystem
 */
package recommender;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ErrorLog.ErrorLog;
import customerBoundary.CustomerBoundary;

/**
 * @author Mohammed Ahmed-Muhsin
 * @version 1.0 
 * @date 2014-06-17
 * 
 * The following class will test the functionality of the RecommenderControl class
 * In specific, we are testing that the class initializes properly and all methods return a reasonable amount
 *
 */
public class RecommenderTester {
	
	private RecommendationControl testControl;
	private CustomerBoundary testCustomer;
	
	/**
	 * Called before each test to ensure proper variables
	 */
	@Before
	public void setUp(){
		testCustomer = new CustomerBoundary();
		testControl = new RecommendationControl(testCustomer);
	}

	@After
	public void tearDown(){
	}

	@Test
	public void testRecommendationControl() {
		assertTrue("Checking that we are extending the class properly from ErrorLog", testControl instanceof RecommendationControl);
	}

	@Test
	public void testAskHotOrNot() {
		
	}

	@Test
	public void testHot() {
		fail("Not yet implemented");
	}

	@Test
	public void testAskHeadToHead() {
		fail("Not yet implemented");
	}

	@Test
	public void testHeadToHead() {
		fail("Not yet implemented");
	}

}
