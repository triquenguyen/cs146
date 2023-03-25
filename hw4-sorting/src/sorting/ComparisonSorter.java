package sorting;

import java.util.Arrays;
import java.util.Random;

public class ComparisonSorter {

	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i - 1;
			int toPlace = arr[i];
			while (j >= 0 && arr[j] > toPlace) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = toPlace;
		}
	}

	public static void mergeSort(int[] arr) {
		mergeSortRecurse(arr, 0, arr.length - 1);
	}

	private static void mergeSortRecurse(int[] arr, int start, int end) {
		if (start >= end)
			return;

		int mid = start + ((end - start) / 2);
		mergeSortRecurse(arr, start, mid);
		mergeSortRecurse(arr, mid + 1, end);
		merge(arr, start, mid, end);
	}

	private static void merge(int[] arr, int start, int mid, int end) {
		int leftSize = mid - start + 1;
		int rightSize = end - mid;

		int[] left = new int[leftSize + 1];
		int[] right = new int[rightSize + 1];

		int leftIndex;
		int rightIndex;

		for (leftIndex = 0; leftIndex < leftSize; leftIndex++)
			left[leftIndex] = arr[start + leftIndex];
		for (rightIndex = 0; rightIndex < rightSize; rightIndex++)
			right[rightIndex] = arr[mid + rightIndex + 1];

		left[leftIndex] = Integer.MAX_VALUE;
		right[rightIndex] = Integer.MAX_VALUE;

		leftIndex = 0;
		rightIndex = 0;

		for (int mergeIndex = start; mergeIndex <= end; mergeIndex++) {
			if (left[leftIndex] <= right[rightIndex]) {
				arr[mergeIndex] = left[leftIndex];
				leftIndex++;
			} else {
				arr[mergeIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}

	public static void heapSort(int[] arr) {
		Heap arrHeap = new Heap(arr, arr.length);
		arrHeap.heapSort();
	}

	public static void quickSort(int[] arr) {
		Partition.quickSortHoare(arr, 0, arr.length - 1);
	}

	public static void compare(int n) {
		int[] insertionData = new int[n];
		int[] mergeData = new int[n];
		int[] heapData = new int[n];
		int[] quickData = new int[n];
		long start;
		long end;
		long runtime;
		Random randomGenerator = new Random();

		for (int i = 0; i < n; i++) {
			int j = randomGenerator.nextInt() % 10001;
			insertionData[i] = mergeData[i] = heapData[i] = quickData[i] = j;
		}
		start = System.nanoTime();

		insertionSort(insertionData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Insertion Sort took: " + runtime);

		start = System.nanoTime();

		mergeSort(mergeData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Merge Sort took: " + runtime);

		start = System.nanoTime();

		heapSort(heapData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Heap Sort took: " + runtime);

		start = System.nanoTime();

		quickSort(quickData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("QuickSort took: " + runtime);
	}

	// Creates arrays of integers 1 to n in sorted order and runs the different
	// sorting algorithms on the arrays
	public static void compareSorted(int n) {
		int[] insertionData = new int[n];
		int[] mergeData = new int[n];
		int[] heapData = new int[n];
		int[] quickData = new int[n];
		long start;
		long end;
		long runtime;
		Random randomGenerator = new Random();

		for (int i = 0; i < n; i++) {
			int j = i + 1;
			insertionData[i] = mergeData[i] = heapData[i] = quickData[i] = j;
		}

		start = System.nanoTime();

		insertionSort(insertionData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Insertion Sort took: " + runtime);

		start = System.nanoTime();

		mergeSort(mergeData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Merge Sort took: " + runtime);

		start = System.nanoTime();

		heapSort(heapData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Heap Sort took: " + runtime);

		start = System.nanoTime();

		quickSort(quickData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("QuickSort took: " + runtime);
	}

	// Creates an array of integers 1 to n in reverse sorted order and runs the
	// different sorting algorithms on the arrays
	public static void compareReverseSorted(int n) {
		int[] insertionData = new int[n];
		int[] mergeData = new int[n];
		int[] heapData = new int[n];
		int[] quickData = new int[n];
		long start;
		long end;
		long runtime;
		Random randomGenerator = new Random();
		int j = n;

		for (int i = 0; i < n; i++) {			
			insertionData[i] = mergeData[i] = heapData[i] = quickData[i] = j;
			j--;
		}

		start = System.nanoTime();

		insertionSort(insertionData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Insertion Sort took: " + runtime);

		start = System.nanoTime();

		mergeSort(mergeData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Merge Sort took: " + runtime);

		start = System.nanoTime();

		heapSort(heapData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Heap Sort took: " + runtime);

		start = System.nanoTime();

		quickSort(quickData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("QuickSort took: " + runtime);
	}

	// Creates an array of n 1s and runs the different sorting algorithms on the
	// arrays
	public static void compareDuplicates(int n) {
		int[] insertionData = new int[n];
		int[] mergeData = new int[n];
		int[] heapData = new int[n];
		int[] quickData = new int[n];
		long start;
		long end;
		long runtime;
		Random randomGenerator = new Random();

		for (int i = 0; i < n; i++) {
			insertionData[i] = mergeData[i] = heapData[i] = quickData[i] = 1;
		}

		start = System.nanoTime();

		insertionSort(insertionData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Insertion Sort took: " + runtime);

		start = System.nanoTime();

		mergeSort(mergeData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Merge Sort took: " + runtime);

		start = System.nanoTime();

		heapSort(heapData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("Heap Sort took: " + runtime);

		start = System.nanoTime();

		quickSort(quickData);

		end = System.nanoTime();
		runtime = end - start;
		System.out.println("QuickSort took: " + runtime);
	}

	public static void main(String[] args) {
		// compare(Integer.parseInt(args[0]));
		// System.out.println();
		// compareSorted(Integer.parseInt(args[0]));
		// System.out.println();
		// compareReverseSorted(Integer.parseInt(args[0]));
		// System.out.println();
		// compareDuplicates(Integer.parseInt(args[0]));

		compare(1000);
		System.out.println();
		compareSorted(1000);
		System.out.println();
		compareReverseSorted(1000);
		System.out.println();
		compareDuplicates(1000);

		//compareSorted(10);
	}

}
