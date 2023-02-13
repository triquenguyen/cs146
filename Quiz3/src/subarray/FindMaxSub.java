package subarray;

import java.util.Arrays;
import subarray.LinkedList.Node;

public class FindMaxSub {

	public static LinkedList findMaximumSubList(LinkedList nums) {
		Node currNode = nums.head;
		Node nextNode = currNode.next;
		int currSum = currNode.data;
		int maxSum = currSum;
		Node startNode = currNode;
		Node endNode = currNode;

		while (currNode.next != null) {
			currSum = currNode.data;
			if (currSum > maxSum) {
				maxSum = currSum;
				startNode = currNode;
				endNode = currNode;
				currNode = currNode.next;
			}			
			while (nextNode.next != null) {
				currSum = currSum + nextNode.data;
				if (currSum > maxSum) {
					maxSum = currSum;
					startNode = currNode;
					endNode = nextNode;
					nextNode = nextNode.next;
				}
				nextNode = nextNode.next;		
			}		
			currNode = currNode.next;
			nextNode = currNode.next;
		}		
		currSum = currNode.data;
		if (currSum > maxSum) {
			maxSum = currSum;
			startNode = currNode;
			endNode = currNode;
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

	public static void main(String[] args) {
        int[] testArr = {13,-3,-25,-20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        LinkedList testList = new LinkedList(testArr);
        LinkedList resultList = findMaximumSubList(testList);
		int[] resultArr = findMaximumSubArray(testArr);

		System.out.println("maxSubList: " + resultList.toString());
		System.out.print("maxSubArray: ");
		for (int i : resultArr) {
			System.out.print(i + " ");
		}
    }
}
