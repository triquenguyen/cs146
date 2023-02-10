package subarray;

import java.util.Arrays;

import subarray.LinkedList.Node;

public class App {
    public static void main(String[] args) {
        int[] testArr = {13,-3,-25,-20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        // System.out.println(findMaximumSubArray(testArr).toString());

        LinkedList testList = new LinkedList(testArr);
        findMaximumSubList(testList);
    }

    private static void findMaximumSubList(LinkedList nums) {
        Node currNode = nums.head;
		Node nextNode = currNode.next;
		int currSum = currNode.data;
		int maxSum = currSum;
		Node startNode = currNode;
		Node tempNode = currNode;
		Node endNode = currNode;

		while (currNode.next != null) {
			currSum = currNode.data;
			tempNode = currNode;

			if (currSum > maxSum) {
				maxSum = currSum;
				startNode = tempNode;
				endNode = currNode;
				currNode = currNode.next;
			}
			//System.out.println(currSum);

			while (nextNode != null) {
				System.out.println("currSum = " + currSum + " + " + nextNode.data);
				currSum = currSum + nextNode.data;
				System.out.println("Current: " + currSum);

				if (currSum > maxSum) {
					maxSum = currSum;
					startNode = tempNode;
					endNode = nextNode;
					nextNode = nextNode.next;
				}
				System.out.println("Max: " + maxSum);	
				nextNode = nextNode.next;		
			}		
			System.out.println("----");
			currNode = currNode.next;
			nextNode = currNode.next;
		}
		
		
    }
}
