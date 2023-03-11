package heap;

import java.util.Arrays;

public class Heap {

	private int[] data;
	private int heapSize;

	public Heap(int[] data, int heapSize) {
		this.data = data;
		this.heapSize = heapSize;
	}

	public void setHeapSize(int i) {
		heapSize = i;
	}

	public int getHeapSize() {
		return heapSize;
	}

	// return the parent of ith element in the array.
	// should return -1 if the ith element is the root of the heap
	public int parent(int i) {
		if (i == 0) {
			return -1;
		} else {
			return i / 2;
		}
	}

	// returns the index of the left child of the ith element in the array
	// for leaves the index will be greater than the heapSize
	public int left(int i) {
		return 2 * i + 1;
	}

	// returns the index of the right child of the ith element in the array
	// for leaves the index will be greater than the heapSize
	public int right(int i) {
		return 2 * i + 2;
	}

	// modifies the data array so that the tree rooted at the loc element
	// is a max heap.
	// Assumes that the trees rooted at the left and right children of loc
	// are max heaps
	public void maxHeapify(int loc) {
		int largest;
		int l = left(loc);
		int r = right(loc);

		if (l < heapSize && data[l] > data[loc]) {
			largest = l;
		} else {
			largest = loc;
		}

		if (r < heapSize && data[r] > data[largest]) {
			largest = r;
		}

		if (largest != loc) {
			int temp = data[loc];
			data[loc] = data[largest];
			data[largest] = temp;
			maxHeapify(largest);
		}
	}

	// converts the data array to an array that represents a max heap of size
	// HeapSize
	public void buildMaxHeap() {
		heapSize = data.length;
		for (int i = data.length / 2 - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	// helper method for debugging and printing
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(data, 0, heapSize));
	}

	public static void main(String[] args) {
		Heap testHeap = new Heap(new int[] { 5, 3, 17, 10, 84, 19, 6, 22, 9 }, 9);
		testHeap.buildMaxHeap();
		System.out.println(testHeap.toString());
	}
	// 5,3,17,10,84,19,6,22,9 -> 84,22,9,10,3,17,6,5,9
	// 4,1,3,2,16,9,10,14,8,7 -> 16,14,10,8,7,3,2,4,1
}
