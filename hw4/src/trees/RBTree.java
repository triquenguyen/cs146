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
		int leftHeight, rightHeight = 1;
		if (currNode == NIL) {
			return 0;
		} else {
			leftHeight = getHeight(currNode.left);
			rightHeight = getHeight(currNode.right);
			return Math.max(leftHeight, rightHeight)+1;
		}
	}

	public int getBlackHeight(Node currNode) {
		int leftHeight, rightHeight = 1;
		if (currNode == NIL) {
			return 0;
		} else {
			leftHeight = getHeight(currNode.left);
			rightHeight = getHeight(currNode.right);
			return Math.max(leftHeight, rightHeight)+1;
		}
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
				plsFixMe(currNode, ancestors);

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

			currNode = newNode;
			plsFixMe(currNode, ancestors);
			ancestors.push(currNode);

			root.color = Color.BLACK;
		}
		return true;
	}

	void plsFixMe(Node currNode, Stack<Node> ancestors) {
		Node leftChild = currNode.left;
		Node rightChild = currNode.right;
		if (currNode.color == Color.BLACK &&
				leftChild.color == Color.RED &&
				rightChild.color == Color.RED) {
			fixUp(currNode);
		}
		if (currNode.color == Color.RED && ancestors.size() > 1 && ancestors.peek().color == Color.RED) {
			Node p = ancestors.pop();
			Node g = ancestors.pop();

			if (currNode == p.left && p == g.left) {
				singleRotationLeftChild(p, g, ancestors);
			} else if (currNode == p.right && p == g.right) {
				singleRotationRightChild(p, g, ancestors);
			} else if (currNode == p.right && p == g.left) {
				doubleRotationLeftChild(currNode, p, g, ancestors);
			} else if (currNode == p.left && p == g.right) {
				doubleRotationRightChild(currNode, p, g, ancestors);
			}

			ancestors.push(p);
			ancestors.push(g);
		}
	}

	void singleRotationLeftChild(Node p, Node g, Stack<Node> ancestors) {
		g.left = p.right;
		p.right = g;
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
		Stack<Node> stackNode = new Stack<>();

		public RBTreeInOrderIterator(Node root) {
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
			Node currNode = stackNode.pop();
			getSubtree(currNode.right);

			return currNode;
		}
	}

	public class RBTreePreOrderIterator implements Iterator<Node> {
		Stack<Node> stackNode = new Stack<>();

		public RBTreePreOrderIterator(Node root) {
			this.stackNode.push(root);
		}

		public boolean hasNext() {
			return stackNode.size() > 0;
		}

		public Node next() {
			Node currNode = this.stackNode.pop();
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
				this.stackNode.push(root);
				getSubtree(root.right);
				getSubtree(root.left);
			}
			
		}

		public void getSubtree(Node node) {
			while (node != NIL) {
				this.stackNode.push(node);
				if (node != NIL) {
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

		System.out.println(testTree.toString());
		System.out.println("Height: " + testTree.height());
		System.out.println("Black height: " + testTree.blackHeight());

		// System.out.print("Inorder: ");
		// ArrayList<Node> inorder = testTree.inOrder();
		// for (Node node : inorder) {
		// 	System.out.print(node.data + " ");
		// }
		// System.out.println();

		// ArrayList<Node> preorder = testTree.preOrder();
		// System.out.print("Preoder: ");
		// for (Node node : preorder) {
		// 	System.out.print(node.data + " ");
		// }
		// System.out.println();

		// ArrayList<Node> postorder = testTree.postOrder();
		// System.out.print("Postorder: ");
		// for (Node node : postorder) {
		// 	System.out.print(node.data + " ");
		// }
		// System.out.println();
		

		// {(0,RED),(1,BLACK),(2,RED),(3,BLACK),(5,BLACK),(6,BLACK),(9,RED),(10,RED),(22,BLACK)}
	}
}
