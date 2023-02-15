package divideandconquer;

public class MergeSorter {
    static int MAX = Integer.MAX_VALUE;

    // Method utilizes mergeSort algorithm to sort the arr
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int start, int end) {
        // When there is one element, we just exit the recursion
        if (start >= end) {
            return;
        }       

        int mid = start + (end - start) / 2;    // Initialize middle point
        mergeSort(arr, start, mid);             // Recursively break down the first half of the array
        mergeSort(arr, mid + 1, end);           // Recursively break down the second half of the array

        merge(arr, start, mid, end);            // Start sorting and merge every element back
    }

    static void merge(int[] arr, int start, int mid, int end) {
        // Initialize the size of 2 bits / chunks of the array
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        // Split the array into left and right halves
        int[] left = new int[leftSize+1];
        int[] right = new int[rightSize+1];

        // Getting the left side array
        for (int leftIndex = 0; leftIndex < leftSize; leftIndex++) {
            left[leftIndex] = arr[start + leftIndex];
        }

        // Getting the right side array
        for (int rightIndex = 0; rightIndex < rightSize; rightIndex++) {
            right[rightIndex] = arr[mid + rightIndex + 1];
        }

        left[leftSize] = MAX;
        right[rightSize] = MAX;

        // Initialize the index of each array
        int leftIndex = 0;
        int rightIndex = 0;

        //Compare the left and right array and merge them back into the original array
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

    public static void main(String[] args) throws Exception {
        MergeSorter sorter = new MergeSorter();
        int arr[] = {};
        sorter.mergeSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
             System.out.print(arr[i] + " ");
        }           
    }
}
