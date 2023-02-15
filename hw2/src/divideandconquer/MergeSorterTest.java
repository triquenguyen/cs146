package divideandconquer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import divideandconquer.MergeSorter;

public class MergeSorterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int[] nums = new int[]{4,3,2,1};
		int[] sorted = new int[] {1,2,3,4};
		MergeSorter.mergeSort(nums);
		assertArrayEquals(nums,sorted);
	}

	//Test 1: test one element array
	@Test 
	public void testOneElement() {
		int[] nums = new int[]{1};
		MergeSorter.mergeSort(nums);
		assertArrayEquals(nums, nums);
	}

	//Test 2: test an array with duplicate elements
	@Test 
	public void testDuplicateElement() {
		int[] nums = new int[]{7, 7,1,3,45,3,2,1};
		MergeSorter.mergeSort(nums);
		assertArrayEquals(nums, nums);
	}

	//Test 3: random number test
	@Test 
	public void randomTest() {
		int[] nums = new int[]{3,2,5,2,6,4,7};
		int[] sorted = new int[] {2,2,3,4,5,6,7};
		MergeSorter.mergeSort(nums);
		assertArrayEquals(nums, sorted);
	}

	//Test 4: test an array that elements are symmetric to the middle
	@Test 
	public void symmetricTest() {
		int[] nums = new int[]{6,7,8,9,5,1,2,3,4};
		int[] sorted = new int[] {1,2,3,4,5,6,7,8,9};
		MergeSorter.mergeSort(nums);
		assertArrayEquals(nums, sorted);
	}

	//Test 5: test random 20 elements array
	@Test 
	public void randomTest2() {
		int[] nums = new int[]{4,46,234,34,12,7,45,2,27,34,6,58,1,3,7,9,454,23,2,4};
		int[] sorted = new int[] {1,2,2,3,4,4,6,7,7,9,12,23,27,34,34,45,46,58,234,454};
		MergeSorter.mergeSort(nums);
		assertArrayEquals(nums, sorted);
	}


}
