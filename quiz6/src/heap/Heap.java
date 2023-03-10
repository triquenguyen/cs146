package heap;

import java.util.Arrays;

public class Heap {
	
	private int[] data;
	private int heapSize;
	
	public Heap(int[] data,int heapSize) {
		this.data = data;
		this.heapSize = heapSize;
	}
	
	public void setHeapSize(int i) {
		heapSize = i;
	}
	
	public int getHeapSize() {
		return heapSize;
	}
	
	//return the parent of ith element in the array.
	//should return -1 if the ith element is the root of the heap
	public int parent(int i) {
		return 0;
		
	}
	
	//returns the index of the left child of the ith element in the array
	//for leaves the index will be greater than the heapSize
	public int left(int i) {
		return 0;
		
	}
	
	//returns the index of the right child of the ith element in the array
	//for leaves the index will be greater than the heapSize
	public int right(int i) {
		return 0;
		
	}
	
	//modifies the data array so that the tree rooted at the loc element
	//is a max heap.
	//Assumes that the trees rooted at the left and right children of loc
	//are max heaps
	public void maxHeapify(int loc) {
		
				
	}
	

	//converts the data array to an array that represents a max heap of size HeapSize
	public void buildMaxHeap() {
		
		
	}
	
	//helper method for debugging and printing
	public String toString() {
        
        return Arrays.toString(Arrays.copyOfRange(data, 0, heapSize));

	}


}
