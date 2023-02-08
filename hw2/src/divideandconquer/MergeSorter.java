package divideandconquer;

public class MergeSorter {
    static int MAX = Integer.MAX_VALUE;

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }       

        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid, end);
    }

    static void merge(int[] arr, int start, int mid, int end) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int leftIndex = 0; leftIndex < leftSize; leftIndex++) {
            left[leftIndex] = arr[start + leftIndex];
        }

        for (int rightIndex = 0; rightIndex < rightSize; rightIndex++) {
            right[rightIndex] = arr[mid + rightIndex + 1];
        }

        // left[leftSize] = MAX;
        // right[rightSize] = MAX;
        int leftIndex = 0;
        int rightIndex = 0;
        int mergeIndex = start;
        
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (left[leftIndex] <= right[rightIndex]) {
                arr[mergeIndex] = left[leftIndex];
                leftIndex++;
            } else {
                arr[mergeIndex] = right[rightIndex];
                rightIndex++;
            }
            mergeIndex++;
        }

        while (leftIndex < leftSize) {
            arr[mergeIndex] = left[leftIndex];
            leftIndex++;
            mergeIndex++;
        }

        while (rightIndex < rightSize) {
            arr[mergeIndex] = right[rightIndex];
            rightIndex++;
            mergeIndex++;
        }

        // for (int mergeIndex = start; mergeIndex < end; mergeIndex++) {
        //     if (left[leftIndex] <= right[rightIndex]) {
        //         arr[mergeIndex] = left[leftIndex];
        //         leftIndex++;
        //     } else {
        //         arr[mergeIndex] = right[rightIndex];
        //         rightIndex++;
        //     }
        // }
    }
}
