package redblack;

import java.util.Stack;

public class RBTree {
	
	/*
	 * For coloring the nodes.
	 */
	enum Color {
		RED, BLACK;
	}
	
	/*
	 * Nested class for a Tree node
	 */
	static class Node {
		
		Node left;
		Node right;
		Node parent; 
		int data;
		Color color; 
		
		public Node(int data, Color c) {
			this.data = data;
			color = c;
			left = right = parent = null;
		}
		
	}
	
	Node root;
	
	void leftRotate(Node node) {
	
	}
	
	void rightRotate(Node node) {
		
		
	}
	
	/*
	 * NO DUPLICATE VALUES SHOULD BE INSERTED!
	 */
	public void insert(int i) {
		

	}
	
	void insertFixup(Node node) {
		
		
	}
	/*
	 * This method should return a string representation of the tree from an in order traversal
	 * This method has to be completed iteratively (HINT: you might want to use a Stack)
	 * An in order traversal means that the data will be in sorted order
	 * An empty tree should return the String "{}"
	 * A tree with the nodes inserted in the order 2, 1, 3 
	 * should return the String "{(1,RED),(2,BLACK),(3,RED)}"
	 */
	public String toString() {
		//returns an inOrder traversal of the tree (data will be in sorted order)
		if(root == null){
			return "{}";
		}



		return null;		
	}
}
