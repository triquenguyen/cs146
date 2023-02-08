package deque;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/* Name: Trique Nguyen
 * Enrollment Status: Enrolled
 * Resources Used (including students): https://code.visualstudio.com/docs/languages/java, Hoang Nguyen, Quiz 1
 */


public class Deque {
	
	/* 
	 * Nested inner class, that holds the Nodes of the Deque
	 */
	static class Node {
		int data;
		Node next;
		Node prev;
		
		public Node(int i) {
			data = i;
			next = null;
			prev = null;
		}
	}
	
	
	Node head; //front of the Deque
	Node tail; //back of the Deque
	
	public Deque() {
		head = null;
		tail = null;
	}
	
	/*
	 * Returns true if the Deque is empty (contains no Nodes). Returns true otherwise.
	 */
	public boolean isEmpty() {
		return head==null&&tail==null;
	}
	
	/*
	 * Converts an Deque to an ArrayList. 
	 * This method is useful for testing.
	 */
	public ArrayList<Integer> toArrayList(){
		ArrayList<Integer> deq = new ArrayList<>();
		Node current = head;
		while(current!=null) {
			deq.add(current.data);
			current = current.next;
		}
		return deq;
	}
	
	/*
	 * Returns a String representation of the Deque.
	 * This method is useful for testing.
	 */
	public String toString() {
		String s = "";
		if(isEmpty())
			return s;
		Node current = head;
		while(current!=null) {
			s = s+current.data + ",";
			current = current.next;
		}
		return s.substring(0,s.length()-1);
	}
	
	/* *******************************************************************************
	 * 
	 *                               ADD YOUR METHODS HERE
	 * 
	 *********************************************************************************/

	/* 
	 * Insert a node at head position
	*/
	public void insertHead(int i) {
		// Create a node for data i
		Node newNode = new Node(i);

		// In case the deque is null, assign newNode as head and tail
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.next = head; 	// Link the newNode with value i to the head of deque
			head.prev = newNode; 	// Link 2 nodes together
			head = newNode;			// Make the inserted become the head of deque
		}
	}

	/* 
	 * Insert a node at last / tail position
	*/
	public void insertTail(int i) {
		// Create a node for data i
		Node newNode = new Node(i);
		
		// In case the deque is null, assign newNode as head and tail
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.next = newNode; 	// Link the newNode with value i to the tail of deque
			newNode.prev = tail;
			tail = newNode; 		// Make the inserted become the tail of deque
			tail.next = null;		// The next node of tail is null 
		}
	}

	/* 
	 * Remove the first node of deque
	*/
	public int removeHead() {
		Node removeNode = head;		// Store the current head to a node

		// Throw exception when head is null
		if (isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		}	
			
		// Cheking 1 node deque
		if (head == tail) {
			head = tail = null; 	// Make both head and tail null 
		} else {
			head = head.next;		// Make the second node become the head node
			head.prev = null;		// Remove the first node by making it null
		}
		
		return removeNode.data;
	}

	/* 
	 * Remove the last node of deque
	*/
	public int removeTail() {		
		Node removeNode = tail;		// Store the current tail to a node
		
		if (isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		} 

		// Cheking 1 node deque
		if (head == tail) {			
			head = tail = null;		// Assign everything to null
		} else if (head.next.next == null) { // 2 nodes deque case
			tail = head;			
			tail.next = null;
			removeNode.prev = null;
		} else {				
			tail.prev.next = null;	// Unlink the last node
			tail = tail.prev;		// Assign 2nd last node to last node
			removeNode.prev = null;	// Unlink the removed node from deque											
		}							

		return removeNode.data;
	}
	
	/* 
	 * Return the head node value
	*/
	public int peekHead() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		}			
		return head.data;
	}

	/* 
	 * Return the tail node value
	*/
	public int peekTail() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		} 			
		return tail.data;
	}
	
	// Check empty case
	public Deque sort() {
		Deque result = new Deque();		// Create a deque to store the sorted nodes
		Node currNode = head;

		//Check empty deque case -> return an empty deque
		if (isEmpty()) {
			return result;
		} 

		while (currNode != null) {
			// Create another node to iterate through every other nodes than currNode
			Node nextNode = currNode.next;
			
			while(nextNode != null) {
				// Checking if nodes at front > nodes following -> swap 2 data of those 2 nodes
				if (currNode.data > nextNode.data) {
					int temp = currNode.data;
					currNode.data = nextNode.data;
					nextNode.data = temp;
				}
				nextNode = nextNode.next;				
			}
			currNode = currNode.next;
		}

		// Iterate through every node (sorted) and insert to the return variable
		while (head != null) {
			result.insertTail(head.data);
			head = head.next;
		}
		
		return result;
	}
}
