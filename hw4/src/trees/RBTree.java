package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.xml.namespace.QName;

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
		Integer data;
		Color color;

		public Node(int data, Color c) {
			this.data = data;
			color = c;
			left = right = NIL;
		}

		public Node(Color c) {
			color = c;
		}

		public String toString() {
			return "(Data: " + data + ", Color: " + color + ")";
		}

	}

	Node root;

	// NIL node pointed to by all leaves
	// May be helpful for insert method when you collect ancestors
	static final Node NIL = new Node(Color.BLACK);

	public int height() {
		Node currNode = root;
		if (currNode == null) {
			return 0;
		} else {
			return getHeight(currNode);
		}
	}

	public int blackHeight() {
		Node currNode = root;
		if (currNode == null) {
			return 0;
		} else if (currNode.color == Color.BLACK) {
			return getBlackHeight(currNode);
		} else {
			return 0;
		}
	}

	public static int getHeight(Node currNode) {
		int leftHeight = 0, rightHeight = 0;
		// Checking root is Null case
		if (currNode == NIL) {
			return 0;
			// checking 1 node tree case
		} else if (currNode != NIL && currNode.left == NIL && currNode.right == NIL) {
			return 1;
		} else {
			leftHeight = getHeight(currNode.left); // Recursively find the height of the left subtree
			rightHeight = getHeight(currNode.right); // Recursively find the height of the right subtree
			return Math.max(leftHeight, rightHeight) + 1; // Find the max between the left subtree and the right subtree, plus
																										// 1 for the root node
		}
	}

	public int getBlackHeight(Node currNode) {
		int leftHeight = 0, rightHeight = 0;
		// Checking root is Null case
		if (currNode == NIL) {
			return 0;
			// checking 1 node tree case
		} else if (currNode != NIL && currNode.left == NIL && currNode.right == NIL) {
			return 1;
		} else {
			// Checking the left node color is black and recursively find the height of the
			// left subtree
			if (currNode.left.color == Color.BLACK) {
				leftHeight = getBlackHeight(currNode.left);
			} else if (currNode.left.left != null && currNode.left.left.color == Color.BLACK) {
				leftHeight = getBlackHeight(currNode.left.left);
			}
			// Checking the right node color is black and recursively find the height of the
			// right subtree
			if (currNode.right.color == Color.BLACK) {
				rightHeight = getBlackHeight(currNode.right);
			} else if (currNode.right.right != null && currNode.right.right.color == Color.BLACK) {
				rightHeight = getBlackHeight(currNode.right.right);
			}
			return Math.max(leftHeight, rightHeight) + 1; // Find the max between the left subtree and the right subtree, plus
																										// 1 for the root node
		}
	}

	public boolean insert(int i) {
		Node newNode = new Node(i, Color.RED);

		// Tree has nothing case: Inserting the node directly and make it the root node
		if (root == null) {
			root = newNode;
			root.color = Color.BLACK;
			root.left = NIL;
			root.right = NIL;
			return true;
		} else {
			Node currNode = root;
			// Create a stack of ancestors to manage the parent nodes
			Stack<Node> ancestors = new Stack<>();

			// Iterate through the tree and find the suitable place for the newNode
			while (currNode != NIL) {
				// Fix up the tree by rotating and recoloring from top down to the currNode
				// To manage the redblack properties of the tree before inserting
				plsFixMe(currNode, ancestors);

				// Move to the next node depends on the data of inserting node and the currNode
				if (i < currNode.data) {
					// Push in the currNode before moving to the next node
					// currNode now becomes the parent node
					ancestors.push(currNode);
					currNode = currNode.left;
				} else if (i > currNode.data) {
					ancestors.push(currNode);
					currNode = currNode.right;
				} else {
					return false;
				}
			}

			// Inserting the newNode to the left or right of the parent node
			if (i < ancestors.peek().data) {
				ancestors.peek().left = newNode;
			} else {
				ancestors.peek().right = newNode;
			}
			// Create NIL nodes for the inserting node
			newNode.right = NIL;
			newNode.left = NIL;

			// Make the newNode become the currNode
			// Fixup the tree again after the newNode is inserted to maintain the RB tree
			// properties
			currNode = newNode;
			plsFixMe(currNode, ancestors);
			ancestors.push(currNode);

			// Recolor the root to black to maintain the RB tree properties
			root.color = Color.BLACK;
		}
		return true;
	}

	void plsFixMe(Node currNode, Stack<Node> ancestors) {
		Node leftChild = currNode.left;
		Node rightChild = currNode.right;
		// Recoloring for the case black parent has two red children
		if (currNode.color == Color.BLACK && leftChild.color == Color.RED && rightChild.color == Color.RED) {
			currNode.color = Color.RED;
			leftChild.color = Color.BLACK;
			rightChild.color = Color.BLACK;
		}
		// 2 children node are red case
		if (currNode.color == Color.RED && ancestors.size() > 1 && ancestors.peek().color == Color.RED) {
			Node p = ancestors.pop(); // parent node
			Node g = ancestors.pop(); // grandparent node

			if (currNode == p.left && p == g.left) {
				singleRotationLeftChild(p, g, ancestors);
			} else if (currNode == p.right && p == g.right) {
				singleRotationRightChild(p, g, ancestors);
			} else if (currNode == p.right && p == g.left) {
				doubleRotationLeftChild(currNode, p, g, ancestors);
			} else if (currNode == p.left && p == g.right) {
				doubleRotationRightChild(currNode, p, g, ancestors);
			}

			// Push parent and grandparent back in the stack
			// After rotating, parent node becomes the parent of the grandparent
			ancestors.push(p);
			ancestors.push(g);
		}
	}

	void singleRotationLeftChild(Node p, Node g, Stack<Node> ancestors) {
		g.left = p.right;
		p.right = g;
		Node ancestor = null;

		// Checking if the grandparent is the root
		if (g == root) {
			root = p; // Make the parent become root node
		} else {
			ancestor = ancestors.peek(); // Have greatgrandparent node to connect with the parent after rotating parent
																		// and grandparent nodes
		}

		// Determine whether connect parent to greatgrand left or right
		if (ancestor != null) {
			if (p.data < ancestor.data) {
				ancestor.left = p;
			} else {
				ancestor.right = p;
			}
		} else {
			root = p;
		}

		// recolor the 2 nodes
		p.color = Color.BLACK;
		g.color = Color.RED;
	}

	void singleRotationRightChild(Node p, Node g, Stack<Node> ancestors) {
		g.right = p.left;
		p.left = g;
		Node ancestor = null;
		if (g == root) {
			root = p;
		} else {
			ancestor = ancestors.peek();
		}

		if (ancestor != null) {
			if (p.data < ancestor.data) {
				ancestor.left = p;
			} else {
				ancestor.right = p;
			}
		} else {
			root = p;
		}

		p.color = Color.BLACK;
		g.color = Color.RED;

	}

	void doubleRotationLeftChild(Node c, Node p, Node g, Stack<Node> ancestors) {
		p.right = c.left;
		g.left = c;
		c.left = p;
		// currNode become the parent of the parent and we rotate currNode and
		// grandparent node
		singleRotationLeftChild(c, g, ancestors);
	}

	void doubleRotationRightChild(Node c, Node p, Node g, Stack<Node> ancestors) {
		p.left = c.right;
		g.right = c;
		c.right = p;
		singleRotationRightChild(c, g, ancestors);
	}

	/*
	 * String representation of the tree
	 * This can be especially helpful for debugging
	 * Each indentation represents a new level of the tree
	 * The tree represent like this...
	 * 1
	 * 0
	 * 2
	 * is the tree
	 * 1
	 * / \
	 * 0 2
	 */
	public String toString() {
		return "Tree:" + recursiveToString(root, "");
	}

	private String recursiveToString(Node node, String indent) {

		if (node == NIL) {
			return "";
		} else {
			return "\n" + indent + node.data + ", " + node.color +

					recursiveToString(node.left, indent + "  ") +
					recursiveToString(node.right, indent + "  ");
		}
	}

	/*
	 * This method uses the in order iterator to create an ArrayList of Nodes
	 */
	public ArrayList<Node> inOrder() {
		RBTreeInOrderIterator iter = new RBTreeInOrderIterator(root);
		ArrayList<Node> inorder = new ArrayList<>();
		while (iter.hasNext()) {
			inorder.add(iter.next());
		}
		return inorder;
	}

	/*
	 * This method uses the pre order iterator to create an ArrayList of Nodes
	 */
	public ArrayList<Node> preOrder() {
		RBTreePreOrderIterator iter = new RBTreePreOrderIterator(root);
		ArrayList<Node> preorder = new ArrayList<>();
		while (iter.hasNext()) {
			preorder.add(iter.next());
		}
		return preorder;
	}

	/*
	 * This method uses the post order iterator to create an ArrayList of Nodes
	 */
	public ArrayList<Node> postOrder() {
		RBTreePostOrderIterator iter = new RBTreePostOrderIterator(root);
		ArrayList<Node> postorder = new ArrayList<>();
		while (iter.hasNext()) {
			postorder.add(iter.next());
		}
		return postorder;
	}

	public class RBTreeInOrderIterator implements Iterator<Node> {
		// Create a stack to manage the nodes
		Stack<Node> stackNode = new Stack<>();

		public RBTreeInOrderIterator(Node root) {
			// Push all the left nodes of root into the stack
			getSubtree(root);
		}

		public void getSubtree(Node node) {
			while (node != NIL) {
				this.stackNode.push(node);
				node = node.left;
			}
		}

		public boolean hasNext() {
			return stackNode.size() > 0;
		}

		public Node next() {
			// Pop out the smallest nodes then push its right node into the stack
			Node currNode = stackNode.pop();
			getSubtree(currNode.right);

			// The smaller nodes will be return first
			return currNode;
		}
	}

	public class RBTreePreOrderIterator implements Iterator<Node> {
		Stack<Node> stackNode = new Stack<>();

		public RBTreePreOrderIterator(Node root) {
			// Push in the root node of the root
			this.stackNode.push(root);
		}

		public boolean hasNext() {
			return stackNode.size() > 0;
		}

		public Node next() {
			Node currNode = this.stackNode.pop();
			// Push in the right and left nodes of the subtree
			// When pop, we return the left node
			if (currNode.right != NIL) {
				this.stackNode.push(currNode.right);
			}
			if (currNode.left != NIL) {
				this.stackNode.push(currNode.left);
			}

			return currNode;
		}

	}

	public class RBTreePostOrderIterator implements Iterator<Node> {
		Stack<Node> stackNode = new Stack<>();

		public RBTreePostOrderIterator(Node root) {
			if (root != NIL) {
				// Push the root node into the stack
				this.stackNode.push(root);
				// Iteratively push the right and left nodes into the stack
				getSubtree(root.right);
				getSubtree(root.left);
			}

		}

		public void getSubtree(Node node) {
			while (node != NIL) {
				this.stackNode.push(node);
				// Push the left nodes into the stack, otherwise, push the right nodes
				if (node.left != NIL) {
					node = node.left;
				} else {
					node = node.right;
				}
			}
		}

		public boolean hasNext() {
			return stackNode.size() > 0;
		}

		public Node next() {
			Node currNode = stackNode.pop();
			// Checking if the node just popped out is the left node of the peek, then push
			// in the right node of that peek node
			if (stackNode.size() > 0 && currNode == this.stackNode.peek().left) {
				getSubtree(this.stackNode.peek().right);
			}
			return currNode;
		}
	}

	public static void main(String[] args) {
		RBTree testTree = new RBTree();
		testTree.insert(2);
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(3);
		testTree.insert(0);
		testTree.insert(5);
		testTree.insert(22);
		testTree.insert(6);
		testTree.insert(9);
		testTree.insert(10);
		testTree.insert(7);
		testTree.insert(123);
		testTree.insert(1000);
		testTree.insert(1234);
		testTree.insert(156);

		System.out.println(testTree.toString());
		System.out.println("Height: " + testTree.height());
		System.out.println("Black height: " + testTree.blackHeight());

		System.out.print("Inorder: ");
		ArrayList<Node> inorder = testTree.inOrder();
		for (Node node : inorder) {
			System.out.print(node.data + " ");
		}
		System.out.println();

		ArrayList<Node> preorder = testTree.preOrder();
		System.out.print("Preoder: ");
		for (Node node : preorder) {
			System.out.print(node.data + " ");
		}
		System.out.println();

		ArrayList<Node> postorder = testTree.postOrder();
		System.out.print("Postorder: ");
		for (Node node : postorder) {
			System.out.print(node.data + " ");
		}
		System.out.println();

	}
}
