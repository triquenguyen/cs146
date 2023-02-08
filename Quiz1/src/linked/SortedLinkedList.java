package linked;


import java.util.*;

/* Name: Trique (Dung) Nguyen (id: 016639103)
 * Enrollment Status: Enrolled
 */

/*
 * This simple doubly linked list class maintains a doubly linked list
 * with elements in that are in sorted order (smallest to largest)
 * i.e., the smallest value is in the head node 
 * and the largest value is in the tail node
 */

public class SortedLinkedList {
	
	/*
	 * DO NOT MODIFYY
	 */
	
	static class Node {
		int data;
		Node next;
		Node prev;
		
		public Node(int data) {
			this.data = data;
			next = null;
			prev = null;
		}
	}
	
	Node head;
	Node tail;
	
	/*
	 * DO NOT MODIFY
	 * Returns whether or not a list is empty
	 */
	public boolean isEmpty() {
		return head==null && tail==null;
	}
	
	/*
	 * DO NOT MODIFY
	 * Returns the value in the head node
	 */
	public int getHeadData() {
		return head.data;
	}
	
	/*
	 * DO NOT MODIFY
	 * Returns the value in the tail node
	 */
	public int getTailData() {
		return tail.data;
	}
	
	/*
	 * DO NOT MODIFY
	 * toString method to use for testing
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

	
	 /****** THIS IS WHERE YOUR IMPLEMENTATION STARTS******/
	 
	
	/*
	 * Inserts a new node in the linked list with data equal to i
	 * Maintains the sorted order of the list
	 */
	public void insert(int i) {
		Node newNode = new Node(i);
		
		// Return the node to newNode if node is null
		if (head == null) {
			head = newNode;
			head.prev = null;
			head.next = null;
		} else {
			// Linked the newNode to the head if newNode < first node
			// Case for the first node 
			if (i <= head.data) {
				newNode.next = head;
				newNode.prev = null;
				head = newNode;
			} 
			else {
				Node currNode = head;
				Node prevNode = null;

				// Update/Increase 2 nodes when i > currNode
				while (currNode != null && i > currNode.data) {
					prevNode = currNode;
					currNode = currNode.next; 
				}
				// Linked newNode to the end when it is the largest number
				if (currNode == null) {
					prevNode.next = newNode;
					newNode.prev = prevNode;
					newNode.next = null;
				} else {
					// Link newNode to between prevNode and currNode
					newNode.prev = prevNode;
					newNode.next = currNode;
					prevNode.next = newNode;
					currNode.prev = newNode;
				}
			}
		}		
	}
	
	/*
	 * This method returns true if the list is in sorted order
	 */
	public boolean isSorted() {
		// Create a node currNode same as head
		if (head == null || head.next == null) {
			return true;
		}

		Node currNode = head;

		// Iterate through currNode
		while (currNode.next != null) {
			// return false when the current node > next node
			if (currNode.data > currNode.next.data) {
				return false;
			} 
			// go to the next node for next checking loop
			currNode = currNode.next;
		}
		return true;
	}

	/*
	 * This method returns a Java LinkedList of integers that matches the SortedLinkedList
	 */
	public LinkedList<Integer> getAscending() {
		// Initial a linked list object to store result
		LinkedList<Integer> result = new LinkedList<>();
		
		if (head == null) {
			return result;
		} else {
			Node currNode = head;
			// Iterate through every node, 
			while (currNode != null) {
				// Use 2 pointers to compare every nodes with each other
				Node nextNode = currNode.next;
				while (nextNode != null) {
					if (currNode.data > nextNode.data) {
						int temp = currNode.data;
						currNode.data = nextNode.data;
						nextNode.data = temp;
					}
					// Go every other next node while current is still the same
					nextNode = nextNode.next;
				}
				// Update current node after finish compare with every other node
				currNode = currNode.next;
			}
			// Store sorted nodes to the result
			while (head != null) {
				result.add(head.data);
				head = head.next;
			}
		}	
		return result;
	}
	
	/*
	 * This method returns a Java LinkedList of integers that is the SortedLinkedList
	 * but in descending order (from largest to smallest)
	 */
	public LinkedList<Integer> getDescending(){
		// Same as getAscending but check currNode < nextNode
		LinkedList<Integer> result = new LinkedList<>();
		
		if (head == null) {
			return result;
		} else {
			Node currNode = head;

			while (currNode != null) {
				Node nextNode = currNode.next;

				while (nextNode != null) {
					if (currNode.data < nextNode.data) {
						int temp = currNode.data;
						currNode.data = nextNode.data;
						nextNode.data = temp;
					}
					nextNode = nextNode.next;
				}
				currNode = currNode.next;
			}

			while (head != null) {
				result.add(head.data);
				head = head.next;
			}
		}	
		return result;
	}
}
