package sorting;

import java.util.NoSuchElementException;

public class Queue {

	private static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			next = null;
		}

	}

	private Node front;
	private Node back;
	private int size;

	public void enqueue(int i) {
		Node toInsert = new Node(i);
		if (size == 0) {
			front = toInsert;
			back = toInsert;
			size++;
		} else {
			back.next = toInsert;
			back = back.next;
			size++;
		}
	}

	public int dequeue() {
		if (size == 0) {
			throw new NoSuchElementException("Queue is Empty");
		}
		int frontData = front.data;
		if (size == 1) {
			front = null;
			back = null;
		} else {
			front = front.next;
		}
		size--;
		return frontData;

	}

	public int peek() {
		if (size == 0) {
			throw new NoSuchElementException("Queue is Empty");
		}

		return front.data;

	}

	public boolean isEmpty() {
		return size == 0;
	}

}
