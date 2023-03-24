package sorting;

import java.util.Arrays;

public class Partition {
	
	public static int partitionLomuto(int[] arr,int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		
		for(int j = low;j<high;j++) {
			if (arr[j]<=pivot) {
				i = i+1;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		arr[high] = arr[i+1];
		arr[i+1]=pivot;
		return i+1;
				   

	}
	
	/***********ANSWER QUESTION HERE*****************/
    /*
     * What sort of input arrays will enable Hoare’s algorithm to still create relatively 
     * equal size partitions whereas Lumoto’s algorithm will create unequal partitions?
     */
	
	public static int partitionHoare(int[] arr,int low, int high) {
		int pivot = arr[low];
		int i = low-1;
		int j = high+1;
		while(true){
			i = i+1;
			j = j-1;
			while(arr[j]>pivot) {
				j = j-1;
			}
			while(arr[i]<pivot) {
				i = i +1;
			}
			if(i<j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;	
			}
			else {
				return j;
			}			
		}
	}

	public static void quickSortHoare(int[] arr, int lo, int hi) {
		if (lo < hi) {
			int p = partitionHoare(arr, lo, hi);
			quickSortHoare(arr, lo, p); // ***Range from low to pivot
			quickSortHoare(arr, p + 1, hi);
		}
	}
	

}
