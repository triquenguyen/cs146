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
		int n = arr.length;
		Heap arrHeap = new Heap(arr, n);

		arrHeap.buildMaxHeap();

		for (int i = arrHeap.getHeapSize() - 1; i >= 1; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			n--;
			arrHeap.setHeapSize(n);
			arrHeap.maxHeapify(0);
		}
	}

	public static void quickSort(int[] arr) {

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

	public static void main(String[] args) {
		int[] arr = new int[] { };
		heapSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		//compare(Integer.parseInt(args[0]));
	}

}
