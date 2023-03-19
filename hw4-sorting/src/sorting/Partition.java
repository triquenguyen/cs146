package sorting;

import java.util.Arrays;

public class Partition {

	public static int partitionLomuto(int[] arr, int low, int high) {
		int x = arr[high];
		int i = low - 1;

		for (int j = low; j <= high - 1; j++) {
			if (arr[j] <= x) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return i + 1;
	}

	/*********** ANSWER QUESTION HERE *****************/
	/*
	 * What sort of input arrays will enable Hoare’s algorithm to still create
	 * relatively equal size partitions whereas Lomuto’s algorithm will create
	 * unequal partitions?
	 */

	public static int partitionHoare(int[] arr, int low, int high) {
		int pivot = arr[low];
		int i = low - 1;
		int j = high + 1;

		while (true) {
			do {
				i++;
			} while (arr[i] < pivot);

			do {
				j--;
			} while (arr[j] > pivot);

			if (i >= j) {
				return j;
			}

			swap(arr, i, j);
		}
	}

	// Swap 2 elements in an array
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Quick sort with Lomuto Partition
	public static void quickSortLomuto(int[] arr, int lo, int hi) {
		if (lo < hi) {
			int p = partitionLomuto(arr, lo, hi);
			quickSortLomuto(arr, lo, p - 1); // ***Range from low to pivot - 1
			quickSortLomuto(arr, p + 1, hi);
		}
	}

	// Quick sort with Hoare Partition
	public static void quickSortHoare(int[] arr, int lo, int hi) {
		if (lo < hi) {
			int p = partitionHoare(arr, lo, hi);
			quickSortHoare(arr, lo, p); // ***Range from low to pivot
			quickSortHoare(arr, p + 1, hi);
		}
	}

	static void printArray(int[] arr, int size) {
		for (int i = 0; i < size; i++)
			System.out.print(arr[i] + " ");

		System.out.println();
	}

	public static void main(String[] args) {
		int[] testArray = new int[] { 22, 4, 1, 57, 3, 65, 2, 8 };

		quickSortHoare(testArray, 0, testArray.length - 1);
		printArray(testArray, testArray.length);

		quickSortLomuto(testArray, 0, testArray.length - 1);
		printArray(testArray, testArray.length);
	}
}
