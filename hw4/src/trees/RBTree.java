package trees;

import java.util.ArrayList;
import java.util.Iterator;
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
		int height = 0;

		return height;
	}

	public int blackHeight() {
		return 0;
	}

	public boolean insert(int i) {
		Node newNode = new Node(i, Color.RED);

		if (root == null) {
			root = newNode;
			root.color = Color.BLACK;
			root.left = NIL;
			root.right = NIL;
			return true;
		} else {
			Node currNode = root;
			Stack<Node> ancestors = new Stack<>();

			// Iterate through the tree and find the suitable place for the newNode
			while (currNode != NIL) {
				Node leftChild = currNode.left;
				Node rightChild = currNode.right;
				if (currNode.color == Color.BLACK &&
						leftChild.color == Color.RED &&
						rightChild.color == Color.RED) {
					fixUp(currNode);
				} else if (currNode.color == Color.RED && ancestors.size() > 1 && ancestors.peek().color == Color.RED) {
					Node parent = ancestors.pop();
					Node grandparent = ancestors.pop();

					if (currNode == parent.left && parent == grandparent.left) {
						System.out.println("Single Rotation on Left Child");
						singleRotationLeftChild(parent, grandparent, ancestors);
					} else if (currNode == parent.right && parent == grandparent.right) {
						System.out.println("Single Rotation on Right Child");
						singleRotationRightChild(parent, grandparent, ancestors);
					} else if (currNode == parent.right && parent == grandparent.left) {
						System.out.println("Double Rotation on Left Child");
						doubleRotationLeftChild(currNode, parent, grandparent, ancestors);
					} else if (currNode == parent.left && parent == grandparent.right) {
						System.out.println("Double Rotation on Right Child");
						doubleRotationRightChild(currNode, parent, grandparent, ancestors);
					}
				}

				if (i < currNode.data) {
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
			newNode.right = NIL;
			newNode.left = NIL;

			root.color = Color.BLACK;
		}
		return true;
	}

	void singleRotationLeftChild(Node p, Node g, Stack<Node> ancestors) {
		g.left = p.right;
		p.right = g;
		Node ancestor = ancestors.peek();
		if (g == root) {
			root = p;
		} else if (g == ancestor.left) {
			ancestor.left = p;
		} else {
			ancestor.right = p;
		}
		
		// Color temp = p.color;
		// p.color = g.color;
		// g.color = temp;

		p.color = Color.BLACK;
		g.color = Color.RED;
		ancestors.push(p);
		ancestors.push(g);
	}

	void singleRotationRightChild(Node p, Node g, Stack<Node> ancestors) {
		g.right = p.left;
		p.left = g;
		Node ancestor = ancestors.peek();

		if (g == root) {
			root = p;
		} else if (g == ancestor.left) {
			ancestor.left = p;
		} else {
			ancestor.right = p;
		}
		
		// Color temp = p.color;
		// p.color = g.color;
		// g.color = temp;
		p.color = Color.BLACK;
		g.color = Color.RED;
		ancestors.push(p);
		ancestors.push(g);
	}

	void doubleRotationLeftChild(Node c, Node p, Node g, Stack<Node> ancestors) {
		p.right = c.left;
		g.left = c;
		c.left = p;
		singleRotationLeftChild(c, g, ancestors);
	}

	void doubleRotationRightChild(Node c, Node p, Node g, Stack<Node> ancestors) {
		p.left = c.right;
		g.right = c;
		c.right = p;
		singleRotationRightChild(c, g, ancestors);
	}

	void fixUp(Node currNode) {
		currNode.color = Color.RED;
		currNode.left.color = Color.BLACK;
		currNode.right.color = Color.BLACK;
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
	// public ArrayList<Node> inOrder() {
	// RBTreeInOrderIterator iter = new RBTreeInOrderIterator(root);
	// ArrayList<Node> inorder = new ArrayList<>();
	// while (iter.hasNext()) {
	// inorder.add(iter.next());
	// }
	// return inorder;
	// }

	// /*
	// * This method uses the pre order iterator to create an ArrayList of Nodes
	// */
	// public ArrayList<Node> preOrder() {
	// RBTreePreOrderIterator iter = new RBTreePreOrderIterator(root);
	// ArrayList<Node> preorder = new ArrayList<>();
	// while (iter.hasNext()) {
	// preorder.add(iter.next());
	// }
	// return preorder;
	// }

	// /*
	// * This method uses the post order iterator to create an ArrayList of Nodes
	// */
	// public ArrayList<Node> postOrder() {
	// RBTreePostOrderIterator iter = new RBTreePostOrderIterator(root);
	// ArrayList<Node> postorder = new ArrayList<>();
	// while (iter.hasNext()) {
	// postorder.add(iter.next());
	// }
	// return postorder;
	// }

	public class RBTreeInOrderIterator implements Iterator<Node> {

		public boolean hasNext() {
			return false;
		}

		public Node next() {
			return null;
		}
	}

	public class RBTreePreOrderIterator implements Iterator<Node> {

		public boolean hasNext() {
			return false;
		}

		public Node next() {
			return null;
		}

	}

	public class RBTreePostOrderIterator implements Iterator<Node> {

		public boolean hasNext() {
			return false;
		}

		public Node next() {
			return null;
		}
	}

	public static void main(String[] args) {
		RBTree testTree = new RBTree();
		testTree.insert(2);
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(0);
		testTree.insert(5);
		testTree.insert(6);
		testTree.insert(9);
		testTree.insert(10);
		testTree.insert(22);

		System.out.println(testTree.toString());

		// {(0,RED),(1,BLACK),(2,RED),(3,BLACK),(5,BLACK),(6,BLACK),(9,RED),(10,RED),(22,BLACK)}
	}
}
