package divideandconquer;

public class LinkedList {
	
	//inner class that creates Nodes for the LinkedList
	static  class Node {
		int data;
		Node next;
		Node prev;
		Node(int data) {
			this.data = data;
			next = null;
			prev = null;
		}
		
		Node(int data, Node next,Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
	}
	
	//Node that starts the LinkedList
	Node head;
	Node tail;
		
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
	 * Running sublist on 1<->2<->3<->4<->5 with start = 2 and end =4
	 * returns the new LinkedList:2<->3<->4
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
		Node node = new Node(i);
		if(isEmpty()) 
			head = node;
		else {
			tail.next = node;
			node.prev = tail;
		}
		
		tail = node;
	}
	
	boolean isEmpty() {
		return head==null&&tail==null;
	}
	
	//finds the middle node in the linked list
	Node middle(Node low,Node high) {
		int size = 0;
		Node current = low;
		while(current!=high) {
			size++;
			current = current.next;
		}
		int half = size/2;
		current = low;
		while(half>0) {
			current = current.next;
			half--;
		}
		return current;
	}
	
	//string representation of the linked list
	//useful for debugging
	public String toString() {
		String s = "";
		if(isEmpty())
			return s;
		Node current = head;
		while(current!=null) {
			s = s+current.data + "<->";
			current = current.next;
		}
		return s.substring(0,s.length()-3);
	}
	

	

}
