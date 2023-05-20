package finalExam;

public class BinaryTree {

	public class Node {

		Node left;
		Node right;
		int value;

		public Node(int val) {
			value = val;
			left = null;
			right = null;
		}

	}

	Node root;

	public BinaryTree(int[] preOrder) {
		root = null;

		for (int i : preOrder) {
			root = createBinaryTree(root, i);
		}
	}

	public Node createBinaryTree(Node currNode, int value) {
		if (currNode == null) {
			return new Node(value);
		}

		if (value < currNode.value) {
			currNode.left = createBinaryTree(currNode.left, value);
		} else if (value > currNode.value) {
			currNode.right = createBinaryTree(currNode.right, value);
		}

		return currNode;
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

		if (node == null) {
			return "";
		} else {
			return "\n" + indent + node.value +

					recursiveToString(node.left, indent + "  ") +
					recursiveToString(node.right, indent + "  ");
		}
	}

	public static void main(String[] args) {
		int[] preOrderArr = { 1, 0, 2 };

		BinaryTree testTree = new BinaryTree(preOrderArr);

		System.out.println(testTree.toString());
	}
}
