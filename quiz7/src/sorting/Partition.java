package sorting;

import java.util.Arrays;

public class Partition {
	
	public static int partitionLomuto(int[] arr,int low, int high) {
		int x = arr[high];
		int i = low - 1;

		for (int j = low; j <= high - 1; j++) {
			if (arr[j] <= x) {
				i++;
				swap(arr[i], arr[j]);
			}	
		}
		swap(arr[i+1], arr[high]);
		return i+1;
	}
	
	/***********ANSWER QUESTION HERE*****************/
    /*
     * What sort of input arrays will enable Hoare’s algorithm to still create relatively 
     * equal size partitions whereas Lomuto’s algorithm will create unequal partitions?
     */
	
	public static int partitionHoare(int[] arr,int low, int high) {
		int pivot = arr[low];
		int i = low - 1;
		int j = high + 1;
		while (true){
			if (arr[j] <= pivot) {
				j--;
			}

			if (arr[i] >= pivot) {
				i++;
			}

			if (i<j) {
				swap(arr[i], arr[j]);
			} else {
				return j;
			}
		}
	}
	
	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}

}
