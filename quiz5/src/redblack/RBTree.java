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
	
	void leftRotate(Node x) {
		Node y = x.right;									// Init node y	
		x.right = y.left;									// Connect x right subtree to y left subtree

		if (y.left != null) {							// Check if y left subtree is not null
			y.left.parent = x;							// Set x as y left subtree parent
		}

		y.parent = x.parent;							// Set y parent to x parent

		if (x.parent == null) {						// Check if x parent is null
			root = y;												// Set y as root
		} else if (x == x.parent.left) {	// Check if x is x parent's left child
			x.parent.left = y;							
		} else {
			x.parent.right = y;
		}

		y.left = x;
		x.parent = y;

		y.color = Color.BLACK;
		x.color = Color.RED;
	}
	
	void rightRotate(Node y) {
		Node x = y.left;
		x.left = y.right;

		if (x.right != null) {
			x.right.parent = y;
		}

		x.parent = y.parent;

		if (y.parent == null) {
			root = x;
		} else if (y == y.parent.left) {
			y.parent.left = x;
		} else {
			y.parent.right = x;
		}

		x.right = y;
		y.parent = x;
		y.color = Color.RED;
		x.color = Color.BLACK;
	}
	
	/*
	 * NO DUPLICATE VALUES SHOULD BE INSERTED!
	 */
	public void insert(int i) {
		Node z = new Node(i, Color.RED);
		Node x = root;											
		Node y = null;

		while (x != null) {
			y = x; 
			x = (z.data < x.data) ? x.left : x.right;
		}

		z.parent = y;
	
		if (y == null) {
			root = z;
		} else if (z.data < y.data) {
			y.left = z;
		} else {
			y.right = z;
		}
		z.left = z.right = null;
		insertFixup(z);
	}
	
	void insertFixup(Node z) {
		while (z.parent.color == Color.RED) {
			if (z.parent == z.parent.parent.left) {
				Node y = z.parent.parent.right;
				if (y.color == Color.RED) {
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					z = z.parent.parent;
				} else if (z == z.parent.right) {
						z = z.parent;
						leftRotate(z);
					}
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					rightRotate(z.parent.parent);
			} else {
				Node y = z.parent.parent.left;
				if (y.color == Color.RED) {
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					z = z.parent.parent;
				} else if (z == z.parent.left) {
						z = z.parent;
						rightRotate(z);
					}
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					leftRotate(z.parent.parent);
			}
		}
		root.color = Color.BLACK;
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
