package sorting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RadixSorterTest {

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
	public void basicTest() {
		int[] data = new int[] {5,2,3,17,10,84,19,6,22,9}; 
		int[] output = new int[] {2, 3, 5, 6, 9, 10, 17, 19, 22, 84};
		RadixSorter.radixSort(data);
		assertArrayEquals(output,data);
		
	}

}
