package sorting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComparisonSorterTest {

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
	public void basicHeap() {
		int[] data = new int[] { 5, 2, 3, 17, 10, 84, 19, 6, 22, 9 };
		int[] output = new int[] { 2, 3, 5, 6, 9, 10, 17, 19, 22, 84 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);

	}

	// Heap test 1
	@Test
	public void testHeap1() {
		int[] data = new int[] { 1 };
		int[] output = new int[] { 1 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// Heap test 2
	@Test
	public void testHeap2() {
		int[] data = new int[] { 4, 1, 3, 2, 9, 10, 8, 7, 0, 11, 5 };
		int[] output = new int[] { 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// Heap test 3
	@Test
	public void testHeap3() {
		int[] data = new int[] {};
		int[] output = new int[] {};
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// Heap test 4
	@Test
	public void testHeap4() {
		int[] data = new int[] { 320, 13, 45, 235, 34, -30, -2, 0 };
		int[] output = new int[] { -30, -2, 0, 13, 34, 45, 235, 320 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// Heap test 5
	@Test
	public void testHeap5() {
		int[] data = new int[] { 320, 13, 22, 44, 22, 99, 22, 13 };
		int[] output = new int[] { 13, 13, 22, 22, 22, 44, 99, 320 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	@Test
	public void basicQuick() {
		int[] data = new int[] { 5, 2, 3, 17, 10, 84, 19, 6, 22, 9 };
		int[] output = new int[] { 2, 3, 5, 6, 9, 10, 17, 19, 22, 84 };
		ComparisonSorter.quickSort(data);
		assertArrayEquals(output, data);
	}

	// QuickSort test 1
	@Test
	public void testQuickSort1() {
		int[] data = new int[] { 1 };
		int[] output = new int[] { 1 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// QuickSort test 2
	@Test
	public void testQuickSort2() {
		int[] data = new int[] { 4, 1, 3, 2, 9, 10, 8, 7, 0, 11, 5 };
		int[] output = new int[] { 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// QuickSort test 3
	@Test
	public void testQuickSort3() {
		int[] data = new int[] {};
		int[] output = new int[] {};
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// QuickSort test 4
	@Test
	public void testQuickSort4() {
		int[] data = new int[] { 320, 13, 45, 235, 34, -30, -2, 0 };
		int[] output = new int[] { -30, -2, 0, 13, 34, 45, 235, 320 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

	// QuickSort test 5
	@Test
	public void testQuickSort5() {
		int[] data = new int[] { 320, 13, 22, 44, 22, 99, 22, 13 };
		int[] output = new int[] { 13, 13, 22, 22, 22, 44, 99, 320 };
		ComparisonSorter.heapSort(data);
		assertArrayEquals(output, data);
	}

}
