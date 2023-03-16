package sorting;

import java.util.Arrays;

public class RadixSorter{
	
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
	
	
	
	
	
	
	public static void radixSort(int[] arr) {
		//Get the maximum to know how many digits I have
	    int max = getMax(arr);
	    
	    
	}

}
