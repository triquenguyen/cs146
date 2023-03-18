package sorting;

import java.util.Arrays;

public class RadixSort {

	// returns the max value in the array
	// used to provide the k to counting sort
	private static int getMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i : arr) {
			if (i > max)
				max = i;
		}
		return max;

	}

	private static void countingSort1(int[] arr, int place) {
		int size = arr.length;
		int[] output = new int[size];
		int[] count = new int[10];

		Arrays.fill(count, 0);

		/********** YOUR CODE GOES HERE ***********/

		for (int j = 0; j < size; j++) {
			count[(arr[j] / place) % 10]++;
		}

		for (int i = 1; i < 10; i++) {
			count[i] += count[i-1];
		}

		for (int j = size - 1; j >= 0 ; j--) {
			output[count[(arr[j] / place) % 10] - 1] = arr[j];
			count[(arr[j] / place) % 10]--;
		}

		// Reorder the original array using the output array
		for (int i = 0; i < size; i++)
			arr[i] = output[i];
	}

	public static void radixSort1(int[] arr) {
		// Get the maximum to know how many digits I have
		int max = getMax(arr);

		// Applies counting sort to each place starting with the 1s place
		for (int place = 1; max / place > 0; place *= 10) {
			countingSort1(arr, place);
		}
	}

	private static void countingSort2(int[] arr, int place) {
		int[] output = new int[arr.length];
		int[] count = new int[10];

		Arrays.fill(count, 0);

		/********** YOUR CODE GOES HERE ***********/

		// Reorder the original array using the output array
		for (int i = 0; i < arr.length; i++)
			arr[i] = output[i];

	}

	public static void radixSort2(int[] arr) {
		// Get the maximum to know how many digits I have
		int max = getMax(arr);

		// Applies counting sort to each place starting with the 1s place
		for (int place = 1; max / place > 0; place *= 10)
			countingSort2(arr, place);

		/*********** ANSWER QUESTION HERE *****************/
		/*
		 * Why is RadixSort2 not working?
		 */
	}

	static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");

		System.out.println();
	}

	public static void main(String[] args) {
		int[] testArray = new int[] { 329, 457, 657, 839, 436, 720, 355 };
		radixSort1(testArray);
		printArray(testArray);
	}

	// 329, 457, 657, 839, 436, 720, 355 -> 329, 355, 436, 457, 657, 720, 839
}
