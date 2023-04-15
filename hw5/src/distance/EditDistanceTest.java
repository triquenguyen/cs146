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
		Path p = EditDistance.minEditDistance(s1, s2);
		assertEquals(3, p.cost);
	}

	// test 1: Reverse of source target
	@Test
	public void test1() {
		String s1 = "hello";
		String s2 = "olleh";
		Path p = EditDistance.minEditDistance(s1, s2);
		assertEquals(6, p.cost);
	}

	// test 2: blank source
	@Test
	public void test2() {
		String s1 = " ";
		String s2 = "iamtriquenguyen";
		Path p = EditDistance.minEditDistance(s1, s2);
		assertEquals(16, p.cost);
	}

	// test 3: identical source and target
	@Test
	public void test3() {
		String s1 = "identical";
		String s2 = "identical";
		Path p = EditDistance.minEditDistance(s1, s2);
		assertEquals(0, p.cost);
	}

	// test 4: "not identical but not different"
	@Test
	public void test4() {
		String s1 = "identical";
		String s2 = "identically";
		Path p = EditDistance.minEditDistance(s1, s2);
		assertEquals(2, p.cost);
	}

	// test 5: random string
	@Test
	public void test5() {
		String s1 = "thisissorandom";
		String s2 = "ihopethereisnomatch";
		Path p = EditDistance.minEditDistance(s1, s2);
		assertEquals(20, p.cost);
	}
	
}
