package distance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import distance.EditDistance.Path;

public class EditDistanceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void givenTest() {
		String s1 = "tuesday";
		String s2 = "thursday";
		Path p = EditDistance.minEditDistance(s1,s2);
		assertEquals(3,p.cost);
	}

}
