package divideandconquer;

import divideandconquer.Triple;
import divideandconquer.LinkedList.*;

public class MaxSubFinder {
    /*
     * Max Subarray Methods for Array
     */
    private static Triple<Integer, Integer, Integer> findMaxSubarray(int[] arr, int low, int high) {
        // return the same element for one element array
        if (high == low) {
            return new Triple<Integer,Integer,Integer>(low, high, arr[low]);
        } else {
            // Getting midpoint
            int mid = (low + high) / 2;

            // recursively find max subarray in left and right half
            Triple<Integer, Integer, Integer> left = findMaxSubarray(arr, low, mid);
            Triple<Integer, Integer, Integer> right = findMaxSubarray(arr, mid+1, high);
            // find max subarray crossing the midpoint
            Triple<Integer, Integer, Integer> cross = findMaxCrossingArray(arr, low, mid, high);

            // respectively check and return the max subarray
            if (left.getLast() >= right.getLast() && left.getLast() >= cross.getLast()) {
                return left;
            } else if (right.getLast() >= left.getLast() && right.getLast() >= cross.getLast()) {
                return right;
            } else {
                return cross;
            }
        }
    }

    private static Triple<Integer, Integer, Integer> findMaxCrossingArray(int[] arr, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;            // Initialize leftSum to min value
        int sum = 0, maxLeft = 0, maxRight = 0;
        
        for (int i = mid; i >= low; i--) {
            sum += arr[i];                          // Getting the sum of left half
            //Find the max sum in the left half
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;           // Initialize leftSum to min value
        sum = 0;
        
        for (int j = mid + 1; j <= high; j++) {    
            sum += arr[j];                          // Getting the sum of right half
            //Get the max sum in the left half
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        return new Triple<Integer,Integer,Integer>(maxLeft, maxRight, leftSum + rightSum);
    }

    /*
     * Max Subarray Methods for LinkedList
     */
    private static Triple<Node, Node, Integer> findMaxSubList(LinkedList list, Node low, Node high) {
        if (high == low) {
            return new Triple<Node,Node,Integer>(low, high, low.data);
        } else {
            Node mid = list.middle(low, high);
            Triple<Node, Node, Integer> left = findMaxSubList(list, low, mid);
            Triple<Node, Node, Integer> right = findMaxSubList(list, mid.next, high);
            Triple<Node, Node, Integer> cross = findMaxCrossingList(list, low, mid, high);

            if (left.getLast() >= right.getLast() && left.getLast() >= cross.getLast()) {
                return left;
            } else if (right.getLast() >= left.getLast() && right.getLast() >= cross.getLast()) {
                return right;
            } else {
                return cross;
            }
        }
    }

    private static Triple<Node, Node, Integer> findMaxCrossingList(LinkedList list, Node low, Node mid, Node high) {
        // Initialize leftSum to min value
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        LinkedList leftList = list.subList(low, mid);
        Node leftTail = leftList.tail;
        Node leftHead = leftList.head;
        Node maxLeft = leftTail;
        
        while (leftTail != leftHead) {
            sum += leftTail.data;
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = leftTail;
            }
            leftTail = leftTail.prev;
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        LinkedList rightList = list.subList(mid.next, high);
        Node rightTail = rightList.tail;
        Node rightHead = rightList.head;
        Node maxRight = rightHead;
        
        while (rightHead != rightTail) {
            sum += rightHead.data;
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = rightHead;
            }
            rightHead = rightHead.next;
        }
        return new Triple<Node,Node,Integer>(maxLeft, maxRight, leftSum + rightSum);
    }

    public static Triple<Integer,Integer,Integer> getMaxSubarray(int[] arr) {
        return findMaxSubarray(arr, 0, arr.length - 1);
    }

    public static Triple<Node,Node,Integer> getMaxSubList(LinkedList list) {
        return findMaxSubList(list, list.head, list.tail);
    }
}
