package hashset;

import java.util.Iterator;

public class MySet {
	// implements a set using a separate chaining hash table

	private class Node {
		private Integer element;
		private Node next;

		private Node(Integer e, Node n) {
			element = e;
			next = n;
		}
	}

	private Node table[]; // an array of linked list
	private int tableSize; // current number of lists in the table
	private int numElements; // number of elements in the set

	private final int primes[] = { 7, 23, 59, 131, 271, 563, 1171,
			2083, 4441, 8839, 16319, 32467,
			65701, 131413, 263983, 528991 };
	private int primeIndex; // last prime used

	private int nextPrime(int p) {
		// finds the next prime from the list above
		// used for resizing and the initial size
		while (primes[primeIndex] <= p)
			primeIndex++;
		return primes[primeIndex];
	}

	public MySet(int s) {
		// s is a hint for the initial size
		primeIndex = 0;
		tableSize = nextPrime(s);
		table = new Node[tableSize];
		numElements = 0;
	}

	// return the hash function value for k
	private int hash(Integer k) {

		return Math.abs(k.hashCode() % tableSize);
	}

	// "double" the table size and reinsert the values stored in the
	// current table. the table size should remain prime
	private void resize() {

	}

	// returns true when e is in the set, otherwise returns false
	public boolean find(Integer e) {
		return false;

	}

	// if e is not in the set add e to the set otherwise the set does not change
	// if after adding the new element numElements > 2*tableSize then call resize
	public void addElement(Integer e) {

	}

	// returns a string representation for the set
	// the string representation of the set is { followed by a comma delimiter list
	// of set
	// elements followed by a }. The string for the empty set is {}
	// For example, {1,2,3}.
	// Note that you SHOULD NOT have any spaces in your String
	/*
	 * Example:
	 * table[0]: 2 -> 4
	 * table[1]: 1 -> 3
	 *
	 * The string representation of this set is {2,4,1,3}
	 */
	public String toString() {

	}

	public class MySetIterator implements Iterator<Integer> {
		// implements an iterator for the set
		private int currentList;
		private Node currentNode;

		// helper method that finds the next non empty bucket
		private void nextList() {
			while (currentList < tableSize && table[currentList] == null) {
				currentList++;
			}
			currentNode = (currentList < tableSize) ? table[currentList] : null;
		}

		public MySetIterator() {
			currentList = 0;
			nextList();
		}

		// returns true if the iteration has more elements.
		public boolean hasNext() {
			return currentNode != null;
		}

		// Returns the next element in the iteration.
		public Integer next() {
			Integer rVal = currentNode.element;
			if (currentNode.next != null) {
				// what should the currentNode be?

			} else {
				// No more elements in the current bucket
				// I need to get the next bucket

			}
			return rVal;

		}

	}

	public Iterator<Integer> iterator() {
		// returns an iterator for the set
		return new MySetIterator();
	}

	/*
	 ******** YOU DON"T NEED TO IMPLEMENT THESE METHODS **************
	 */
	public MySet intersect(MySet s) {
		// return a new set that contains the intersection of the elements in
		// this set and the elements in the set s
	}

	public MySet union(MySet s) {
		// return a new set that contains the union of the elements in
		// this set and the elements in the set s
	}

	public MySet difference(MySet s) {
		// return a new set that contains the difference of the elements in
		// this set and the elements in the set s
		// this - s (i.e. all the elements in this that are not in s)
	}

}