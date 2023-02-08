import divideandconquer.MergeSorter;

public class App {
    public static void main(String[] args) throws Exception {
        MergeSorter sortTool = new MergeSorter();
        int arr[] = {1, 3, 5, 2, 0, 6};
        sortTool.mergeSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
             System.out.print(arr[i] + " ");
        }           
    }
}
