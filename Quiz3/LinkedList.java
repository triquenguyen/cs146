package subarray;

public class LinkedList {
	
	//inner class that creates Nodes for the LinkedList
	static  class Node {
		int data;
		Node next;
		Node(int data) {
			this.data = data;
			next = null;
		}
		
		Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
		
	}
	
	//Node that starts the LinkedList
	Node head;
	
	//Constructor that converts an int array to a LinkedList
	LinkedList(int[] nums) {
		for(int i: nums) {
			insert(i);
		}
	}
	
	//No argument constructor
	LinkedList() {
		head = null;
	}
	
	/*
	 * Creates a sublist from the LinkedList from the start node
	 * to the end node
	 * Running sublist on 1->2->3->4->5 with start = 2 and end =4
	 * returns the new LinkedList:2->3->4
	 */
	LinkedList subList(Node start,Node end) {
		LinkedList sub = new LinkedList();
		Node current = head;
		while(current!=start) {
			current = current.next;
		}
		sub.insert(current.data);
		if(start==end)
			return sub;
		current = current.next;
		while(current!=end) {
			sub.insert(current.data);
			current = current.next;	
		}
		sub.insert(current.data);
		return sub;	
	}
	
	/*
	 * insert a new node at the end of the LinkedList
	 * with data equal to i
	 */
	void insert(int i) {
		if(head==null) {
			head = new Node(i);
		}
		else {
			Node current = head;
			while(current.next != null) {
				current = current.next;
			}
			current.next = new Node(i);
		}
	}
	
	boolean isEmpty() {
		return head==null;
	}
	
	//string representation of the linked list
	//useful for debugging
	public String toString() {
		String s = "";
		if(isEmpty())
			return s;
		Node current = head;
		while(current!=null) {
			s = s+current.data + "->";
			current = current.next;
		}
		return s.substring(0,s.length()-2);
	}

}
