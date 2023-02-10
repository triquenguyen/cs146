package subarray;

import java.util.Arrays;
import subarray.LinkedList.Node;

public class FindMaxSub {
	
	
	public static LinkedList findMaximumSubList(LinkedList nums) {
		Node currNode = nums.head;
		Node nextNode = nums.head.next;
		int currSum = currNode.data;
		int maxSum = currSum;
		Node startNode = currNode;
		Node endNode = currNode;

		while (currNode != null) {
			currSum = currNode.data;
			if (currSum > maxSum) {
				maxSum = currSum;
				startNode = currNode;
				endNode = currNode;
			}

			while (nextNode != null) {
				currSum += nextNode.data;
				if (currSum > maxSum) {
					maxSum = currSum;
					startNode = currNode;
					endNode = nextNode;
				}
			}
		}
		
		return nums.subList(startNode, endNode);
	}
	
	public static int[] findMaximumSubArray(int[] nums){
		int currSum = nums[0];
        int maxSum = currSum;
		int startIndex = 0;
		int endIndex = 0;

		for (int i = 0; i < nums.length; i++) {
            currSum = nums[i];

            if (currSum > maxSum) {
                maxSum = currSum;
                startIndex = i;
		        endIndex = i;
            }

			for (int j = i + 1; j < nums.length; j++) {	
				currSum += nums[j];
                
				if (currSum > maxSum) {
					maxSum = currSum;
                    startIndex = i;
                    endIndex = j;
				}
			}
        }
        
		return Arrays.copyOfRange(nums, startIndex, endIndex + 1);
	}
}
