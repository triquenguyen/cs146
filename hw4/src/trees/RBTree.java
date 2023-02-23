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
			return "(Data: " + data +", Color: "+color+")";
		}

	}

	Node root;

	//NIL node pointed to by all leaves
	//May be helpful for insert method when you collect ancestors
	static final Node NIL = new Node(Color.BLACK);
	
	public int height() {
		return 0;
	}

	public int blackHeight() {
		return 0;
	}

	public boolean insert(int i) {
		
        return false;

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

		if(node == NIL) {return "";}
		else {
			return "\n" + indent + node.data + ", "+node.color+

					recursiveToString(node.left,indent + "  ")+
					recursiveToString(node.right,indent + "  ");
		}	
	}

	/* 
	 * This method uses the in order iterator to create an ArrayList of Nodes
	 */
	public ArrayList<Node> inOrder() {
		RBTreeInOrderIterator iter = new RBTreeInOrderIterator(root);
		ArrayList<Node> inorder = new ArrayList<>();
		while(iter.hasNext()) {
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
		while(iter.hasNext()) {
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
		while(iter.hasNext()) {
			postorder.add(iter.next());
		}
		return postorder;
	}

	public class RBTreeInOrderIterator implements Iterator<Node>{

		
		public boolean hasNext(){
			return false;
		}

        public Node next(){
			return null;
		}
	}

	public class RBTreePreOrderIterator implements Iterator<Node>{

        public boolean hasNext(){
            return false;
        }

        public Node next(){
            return null;
        }

	}

	public class RBTreePostOrderIterator implements Iterator<Node>{

        public boolean hasNext(){
            return false;
        }

        public Node next(){
            return null;
        }

	}

}
