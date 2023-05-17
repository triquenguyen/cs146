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
		root = createBinaryTree(preOrder, 0, preOrder.length - 1, 0);
	}

	public Node createBinaryTree(int[] preOrder, int startIndex, int endIndex, int currIndex) {

		if (startIndex > endIndex) {
			return null;
		}

		Node currNode = new Node(preOrder[currIndex]);

		if (startIndex == endIndex) {
			return currNode;
		}

		int rootIndex = 0;

		for (int i = startIndex; i <= endIndex; i++) {
			if (currNode.value == preOrder[i]) {
				rootIndex = i;
			}
		}

		currIndex++;
		currNode.left = createBinaryTree(preOrder, startIndex, rootIndex - 1, currIndex);
		currNode.right = createBinaryTree(preOrder, rootIndex + 1, endIndex, currIndex);

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
		int[] preOrderArr = { 7, 3, 1, 5, 10, 8, 12 };

		BinaryTree testTree = new BinaryTree(preOrderArr);

		System.out.println(testTree.toString());
	}
}
