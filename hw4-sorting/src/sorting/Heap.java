package sorting;

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
		if(i==0)
			return -1;
		else
			return (i-1)/2;
	}
	
	//returns the index of the left child of the ith element in the array
	//for leaves the index will be greater than the heapSize
	public int left(int i) {
		return (2*i)+1;
		
	}
	
	//returns the index of the right child of the ith element in the array
	//for leaves the index will be greater than the heapSize
	public int right(int i) {
		return (2*i) + 2;
		
	}
	
	//modifies the data array so that the tree rooted at the loc element
	//is a max heap.
	//Assumes that the trees rooted at the left and right children of loc
	//are max heaps
	public void maxHeapify(int loc) {
		int l = left(loc);
		int r = right(loc);
		int largest;
		if(l<heapSize && data[l]>data[loc])
			largest = l;
		else
			largest = loc;
		if(r<heapSize && data[r]>data[largest])
			largest = r;
		if(largest != loc) {
			swap(loc,largest);
			maxHeapify(largest);
		}

	}
	
	private void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	//converts the data array to an array that represents a max heap
	public void buildMaxHeap() {
		int n = heapSize;
		for(int i=(n/2)-1;i>=0;i--) {
			maxHeapify(i);
		}
	}

	public void heapSort() {
		buildMaxHeap();
		for (int i = heapSize - 1; i >= 1; i--) {
			swap(0, i);
			heapSize--;
			maxHeapify(0);
		}
	}

}
