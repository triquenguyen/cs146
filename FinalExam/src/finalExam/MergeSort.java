package finalExam;

import java.util.Arrays;

public class MergeSort {

	public static void mergeSort(int[] arr) {

		for (int i = 1; i < arr.length; i *= 2) {
			for (int j = 0; j < arr.length - i; j += 2 * i) {
				merge(arr, j, i + j - 1, Math.min(2 * i + j - 1, arr.length - 1));
			}
		}
	}

	static void merge(int[] arr, int start, int mid, int end) {
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

	public static void main(String[] args) {
		int[] test = { 5, 4, 19, 122, 135, 1, 6, 1231, 12, 21, 0 };
		mergeSort(test);

		for (int i : test) {
			System.out.print(i + " ");
		}
	}

}
