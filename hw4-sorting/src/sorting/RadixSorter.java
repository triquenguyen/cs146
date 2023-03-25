package sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.management.QueryEval;

public class RadixSorter {

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

	private static void queueSort(int[] arr, int place) {
		int size = arr.length;
		Queue[] queueArr = new Queue[10];

		for (int i = 0; i < 10; i++) {
			queueArr[i] = new Queue();
		}

		for (int j = 0; j < size; j++) {
			queueArr[(arr[j] / place) % 10].enqueue(arr[j]);
		}

		int j = 0;
		for (int i = 0; i<10; i++) {
			while (!queueArr[i].isEmpty()) {
				arr[j] = queueArr[i].dequeue();
				j++;
			}
		}
	}

	public static void radixSort(int[] arr) {
		// Get the maximum to know how many digits I have
		int max = getMax(arr);

		for (int place = 1; max / place > 0; place *= 10) {
			queueSort(arr, place);
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] {5555,2,3,17,1210,84,191236,2212,9, 2147483647};
		radixSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

}
