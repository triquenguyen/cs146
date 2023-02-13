package divideandconquer;

import divideandconquer.Triple;
import divideandconquer.LinkedList.*;

public class MaxSubFinder {

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
        // Initialize leftSum to min value
        int leftSum = Integer.MIN_VALUE;
        int sum = 0, maxlLeft = 0, maxRight = 0;

        // Getting the sum of left half
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxlLeft = i;
            }
        }

        // Initialize leftSum to min value
        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        // Getting the sum of right half
        for (int j = mid + 1; j <= high; j++) {
            sum += arr[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        return new Triple<Integer,Integer,Integer>(maxlLeft, maxRight, leftSum + rightSum);
    }

    private static Triple<Node, Node, Integer> findMaxSubList(LinkedList list, Node low, Node high) {
        if (high == low) {
            return new Triple<Node,Node,Integer>(low, high, low.data);
        } else {
            Node mid = list.middle(low, high);

            Triple<Node, Node, Integer> left = findMaxSubList(list, low, mid);
            Triple<Node, Node, Integer> right = findMaxSubList(list, mid.next, high);
            Triple<Node, Node, Integer> cross = findMaxCrossing(list, low, mid, high);

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
         int sum = 0, maxlLeft = 0, maxRight = 0;
         
    }

    public static Triple<Integer,Integer,Integer> getMaxSubarray(int[] arr) {
        return findMaxSubarray(arr, 0, arr.length - 1);
    }

    public static Triple<Node,Node,Integer> getMaxSubList(LinkedList list) {
        return null;
    }
}
