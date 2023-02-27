package redblack;

import java.util.ArrayList;
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

	// General logic: connect the right/left subtree of the node to the right/left
	// child's left/right subtree
	// and connect the parent of that node to the right/left child of that node
	// make the child node to the parent node of that node
	void leftRotate(Node x) {
		Node y = x.right; // Init node y
		x.right = y.left; // Turning y left subtree into x's right subtree
		if (y.left != null) { // Check if y left subtree is not null
			y.left.parent = x; // Set x as y left subtree parent
		}
		y.parent = x.parent; // Set y parent to x parent
		if (x.parent == null) { // Check if x parent is null -> found the root
			root = y; // Set y as root of the tree
		} else if (x == x.parent.left) { // Check if x is x parent's left child
			x.parent.left = y; // Turn y into the left children of x's parent
		} else {
			x.parent.right = y; // Turn y into the right children of x's parent
		}
		y.left = x; // Turn x to be y's left childrem
		x.parent = y; // Make y to be x's parent
	}

	// Same as above but in the opposite direction
	void rightRotate(Node y) {
		Node x = y.left;
		y.left = x.right;
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
	}

	/*
	 * NO DUPLICATE VALUES SHOULD BE INSERTED!
	 */
	public void insert(int i) {
		Node z = new Node(i, Color.RED);
		Node x = root;
		Node y = null;
		// Loop through the tree to look for the inserting position for node z
		while (x != null) {
			y = x;
			// x = (z.data < x.data) ? x.left : x.right; // Go to the left subtree when z is
			// smaller than the parent node, work
			// the same with right subtree
			if (z.data < x.data) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		z.parent = y; // z becomes y children when we found a place to insert z
		if (y == null) {
			root = z;
		} else if (z.data < y.data) { // Check to determine whether node z is inserted to y left or right child
			y.left = z;
		} else {
			y.right = z;
		}
		z.left = z.right = null; // leaves, NIL nodes of the inserted node
		insertFixup(z); // Fix the color so that the RBTree can maintain its properties
	}

	void insertFixup(Node z) {
		while (z.parent != null && z.parent.color == Color.RED) {
			// Parent is a left child case
			if (z.parent == z.parent.parent.left) {
				Node y = z.parent.parent.right; // Ancle node
				// Swapping color of grandparent and parent's node
				if (y.parent != null && y.color == Color.RED) { // Fixing case 1: The grandparent node is red
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					z = z.parent.parent;
				} else if (z == z.parent.right) { // Fixing case 2: the inserted node is a right child
					z = z.parent;
					leftRotate(z);
				} else { // Fixing case 3: the inserted node is a left child
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					rightRotate(z.parent.parent);
				}
				// Parent is a right child case
			} else {
				Node y = z.parent.parent.right;
				if (y.parent != null && y.color == Color.RED) {
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					z = z.parent.parent;
				} else if (z == z.parent.left) {
					z = z.parent;
					rightRotate(z);
				} else {
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					leftRotate(z.parent.parent);
				}
			}
		}
		root.color = Color.BLACK; // recolor the root node to black
	}

	/*
	 * This method should return a string representation of the tree from an in
	 * order traversal
	 * This method has to be completed iteratively (HINT: you might want to use a
	 * Stack)
	 * An in order traversal means that the data will be in sorted order
	 * An empty tree should return the String "{}"
	 * A tree with the nodes inserted in the order 2, 1, 3
	 * should return the String "{(1,RED),(2,BLACK),(3,RED)}"
	 */
	public String toString() {
		// returns an inOrder traversal of the tree (data will be in sorted order)
		if (root == null) {
			return "{}";
		} else {
			String node = "";
			ArrayList<String> sortedList = new ArrayList<>();
			Stack<Node> stackNode = new Stack<Node>();
			Node currNode = root;

			while (currNode != null || stackNode.size() > 0) {
				while (currNode != null) {
					stackNode.push(currNode);
					currNode = currNode.left;
				}
				currNode = stackNode.pop();
				node = "(" + currNode.data + "," + currNode.color + ")";
				currNode = currNode.right;
				sortedList.add(node);
			}
			return "{" + String.join(",", sortedList) + "}";
		}
	}

	public static void main(String[] args) throws Exception {
		RBTree testTree = new RBTree();
		testTree.insert(2);
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(0);
		testTree.insert(5);
		testTree.insert(22);
		String result = testTree.toString();
		System.out.println(result);
	}
}
