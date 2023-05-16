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
		
	}
		
	/* String representation of the tree
	 * This can be especially helpful for debugging
	 * Each indentation represents a new level of the tree
	 * The tree represent like this...
	 * 1
	 *   0
	 *   2
	 * is the tree
	 *    1
	 *   / \
	 *  0   2
	 */
	public String toString() {
		return "Tree:" + recursiveToString(root, "");		
	}	

	private String recursiveToString(Node node, String indent) {

		if(node == null) {return "";}
		else {
			return "\n" + indent + node.value +

					recursiveToString(node.left,indent + "  ")+
					recursiveToString(node.right,indent + "  ");
		}	
	}
	
	
}
