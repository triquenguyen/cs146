package sorting;

import java.util.Arrays;

public class RadixSort {
	
	//returns the max value in the array
	//used to provide the k to counting sort
	private static int getMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int i: arr) {
			if(i>max)
				max = i;
		}
		return max;
		
	}
	
	private static void countingSort1(int[] arr, int place) {
		int[] output = new int[arr.length];
		int[] count = new int[10];
		
		Arrays.fill(count, 0);
		
		/**********YOUR CODE GOES HERE***********/
		
		
	    
	    //Reorder the original array using the output array
	    for (int i = 0; i < arr.length; i++)
	        arr[i] = output[i];
	}
	
	public static void radixSort1(int[] arr) {
		//Get the maximum to know how many digits I have
	    int max = getMax(arr);

	    //Applies counting sort to each place starting with the 1s place
	    for (int place = 1; max / place > 0; place *= 10)
	      countingSort1(arr, place);
		
	}
	
	private static void countingSort2(int[] arr, int place) {
		int[] output = new int[arr.length];
		int[] count = new int[10];
	
		Arrays.fill(count, 0);
		
		/**********YOUR CODE GOES HERE***********/
		
		//Reorder the original array using the output array
	    for (int i = 0; i < arr.length; i++)
	        arr[i] = output[i];
		
	}
	
	public static void radixSort2(int[] arr) {
		//Get the maximum to know how many digits I have
	    int max = getMax(arr);

	    //Applies counting sort to each place starting with the 1s place
	    for (int place = 1; max / place > 0; place *= 10)
	      countingSort2(arr, place);
	    
	    /***********ANSWER QUESTION HERE*****************/
	    /*
	     * Why is RadixSort2 not working? 
	     */
		
	}
	
	

}
