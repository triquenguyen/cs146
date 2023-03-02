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

	// public boolean insert(int i) {
	// 	Node newNode = new Node(i, Color.RED);
	// 	Stack<Node> ancestors = new Stack<>();

	// 	if (root == null) {
	// 		root = newNode;
	// 		root.color = Color.BLACK;
	// 		root.left = NIL;
	// 		root.right = NIL;
	// 		return true;
	// 	} else {
	// 		Node currNode = root;
			
	// 		// Iterate through the tree and find the suitable place for the newNode
	// 		while (currNode != NIL) {
	// 			ancestors.push(currNode);
	// 			Node leftChild = currNode.left;
	// 			Node rightChild = currNode.right;

	// 			if (i < currNode.data) {
	// 				if (currNode.color == Color.BLACK && 
	// 					leftChild.color == Color.RED && 
	// 					rightChild.color == Color.RED) {
	// 					fixUp(currNode);
	// 				}	

	// 				if (currNode.color == Color.RED) {
	// 					Node parent = ancestors.pop();
	// 					if (parent.color == Color.RED) {
	// 						Node grandparent = ancestors.peek();
	// 						if (currNode == parent.left && parent == grandparent.left) {
	// 							singleRotationLeftChild(parent, grandparent, ancestors);
	// 						} else if (currNode == parent.right && parent == grandparent.right) {
	// 							singleRotationRightChild(parent, grandparent, ancestors);
	// 						} else if (currNode == parent.right && parent == grandparent.left) {
	// 							doubleRotationLeftChild(currNode, parent, grandparent, ancestors);
	// 						}	else if (currNode == parent.left && parent == grandparent.right) {
	// 							doubleRotationRightChild(currNode, parent, grandparent, ancestors);
	// 						}
	// 					}
	// 				}

	// 				//ancestors.push(currNode);
	// 				currNode = currNode.left;
	// 			} else if (i > currNode.data) {
	// 				if (currNode.color == Color.BLACK && 
	// 					leftChild.color == Color.RED && 
	// 					rightChild.color == Color.RED) {
	// 					fixUp(currNode);
	// 				}	
	// 				if (currNode.color == Color.RED) {
	// 					Node parent = ancestors.pop();
	// 					if (parent.color == Color.RED) {
	// 						Node grandparent = ancestors.peek();
	// 						if (currNode == parent.left && parent == grandparent.left) {
	// 							singleRotationLeftChild(parent, grandparent, ancestors);
	// 						} else if (currNode == parent.right && parent == grandparent.right) {
	// 							singleRotationRightChild(parent, grandparent, ancestors);
	// 						} else if (currNode == parent.right && parent == grandparent.left) {
	// 							doubleRotationLeftChild(currNode, parent, grandparent, ancestors);
	// 						}	else if (currNode == parent.left && parent == grandparent.right) {
	// 							doubleRotationRightChild(currNode, parent, grandparent, ancestors);
	// 						}
	// 					}
	// 				}
	// 				//ancestors.push(currNode);
	// 				currNode = currNode.right;
	// 			} else {
	// 				return false;
	// 			}
				 
	// 		}
			
	// 		// Inserting the newNode to the left or right of the parent node
	// 		if (i < ancestors.peek().data) {
	// 			ancestors.peek().left = newNode;
	// 		} else {
	// 			ancestors.peek().right = newNode;
	// 		}
	// 		newNode.left = NIL;
	// 		newNode.right = NIL;
	// 	}

	// 	/*
	// 	 * TODO:
	// 	 * Case 1: red nodes are both left children or both right children -> single
	// 	 * rotation
	// 	 * Case 2: otherwise double rotation
	// 	 */

	// 	 return false;
	// }

	// void singleRotationLeftChild(Node p, Node g, Stack<Node> ancestors) {
	// 	g.left = p.right;

	// 	Node ancestor = ancestors.peek();		
	// 	if (g == root) {
	// 		root = p;
	// 	} else if (g == ancestor.left) {
	// 		ancestor.left = p;
	// 	} else {
	// 		ancestor.right = p;
	// 	}

	// 	p.right = g;
	// 	p.color = Color.BLACK;
	// 	g.color = Color.RED;
	// 	ancestors.push(p);
	// 	ancestors.push(g);
	// }

	// void singleRotationRightChild(Node p, Node g, Stack<Node> ancestors) {
	// 	g.right = p.left;
		
	// 	ancestors.pop();
	// 	Node ancestor = ancestors.peek();

	// 	if (g == ancestor) {
	// 		root = p;
	// 	} else if (g == ancestor.left) {
	// 		ancestor.left = p;
	// 	} else {
	// 		ancestor.right = p;
	// 	}

	// 	p.left = g;
	// 	p.color = Color.BLACK;
	// 	g.color = Color.RED;
	// 	ancestors.push(p);
	// 	ancestors.push(g);
	// }

	// void doubleRotationLeftChild(Node c, Node p, Node g, Stack<Node> ancestors) {
	// 	p.right = c.left;
	// 	g.left = c;
	// 	c.left = p;

	// 	singleRotationLeftChild(c, g, ancestors);
	// }

	// void doubleRotationRightChild(Node c, Node p, Node g,  Stack<Node> ancestors) {
	// 	p.left = c.right;
	// 	g.right = c;
	// 	c.right = p;

	// 	singleRotationRightChild(c, g, ancestors);
	// }
	// void fixUp(Node currNode) {
	// 	currNode.color = Color.RED;
	// 	currNode.left.color = Color.BLACK;
	// 	currNode.right.color = Color.BLACK;		
	// }


  public boolean insert(int i) {
		Node newNode = new Node(i, Color.RED);
		Stack<Node> ancestors = new Stack<>();

		if (root == null) {
			root = newNode;
			root.color = Color.BLACK;
			root.left = NIL;
			root.right = NIL;
			return true;
		} else {
			Node currNode = root;
			
			// Iterate through the tree and find the suitable place for the newNode
			while (currNode != NIL) {
				ancestors.push(currNode);
				Node leftChild = currNode.left;
				Node rightChild = currNode.right;

				if (i < currNode.data) {
					if (currNode.color == Color.BLACK && 
						leftChild.color == Color.RED && 
						rightChild.color == Color.RED) {
						fixUp(currNode);
					}	
					if (currNode.color == Color.RED) {
            if (leftChild.color == Color.RED && leftChild.left.color == Color.RED) {
              singleRotationLeftChild(currNode, leftChild, ancestors);
              currNode = leftChild;
            } else if (rightChild.color == Color.RED && rightChild.right.color == Color.RED) {
              singleRotationRightChild(currNode, rightChild, ancestors);
              currNode = rightChild;
            } else if (leftChild.color == Color.RED && leftChild.right.color == Color.RED) {
              doubleRotationLeftChild(currNode, leftChild, ancestors);
              currNode = leftChild;
            }	else if (rightChild.color == Color.RED && rightChild.left.color == Color.RED) {
              doubleRotationRightChild(currNode, rightChild, ancestors);
              currNode = rightChild;
            }
          }
					currNode = currNode.left;
				} else if (i > currNode.data) {
					if (currNode.color == Color.BLACK && 
						leftChild.color == Color.RED && 
						rightChild.color == Color.RED) {
						fixUp(currNode);
					}	
					if (currNode.color == Color.RED) {
						if (leftChild.color == Color.RED && leftChild.left.color == Color.RED) {
							singleRotationLeftChild(currNode, leftChild, ancestors);
							currNode = leftChild;
						} else if (rightChild.color == Color.RED && rightChild.right.color == Color.RED) {
							singleRotationRightChild(currNode, rightChild, ancestors);
							currNode = rightChild;
						} else if (leftChild.color == Color.RED && leftChild.right.color == Color.RED) {
							doubleRotationLeftChild(currNode, leftChild, ancestors);
							currNode = leftChild;
						}	else if (rightChild.color == Color.RED && rightChild.left.color == Color.RED) {
							doubleRotationRightChild(currNode, rightChild, ancestors);
							currNode = rightChild;
						}
					} 
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

			newNode.left = NIL;
			newNode.right = NIL;
		}

		/*
		 * TODO:
		 * Case 1: red nodes are both left children or both right children -> single
		 * rotation
		 * Case 2: otherwise double rotation
		 */

		 return false;
	}

	void singleRotationLeftChild(Node g, Node p, Stack<Node> ancestors) {
		g.left = p.right;
		
		ancestors.pop();
		Node ancestor = ancestors.peek();

		if (g == ancestor) {
			root = p;
		} else if (g == ancestor.left) {
			ancestor.left = p;
		} else {
			ancestor.right = p;
		}

		p.right = g;
		p.color = Color.BLACK;
		g.color = Color.RED;
		ancestors.push(p);
	}

	void singleRotationRightChild(Node g, Node p, Stack<Node> ancestors) {
		g.right = p.left;
		
		ancestors.pop();
		Node ancestor = ancestors.peek();

		if (g == ancestor) {
			root = p;
		} else if (g == ancestor.left) {
			ancestor.left = p;
		} else {
			ancestor.right = p;
		}

		p.left = g;
		p.color = Color.BLACK;
		g.color = Color.RED;
		ancestors.push(p);
	}

	void doubleRotationLeftChild(Node g, Node p, Stack<Node> ancestors) {
		Node x = p.right;

		p.right = x.left;
		g.left = x;
		x.left = p;

		singleRotationLeftChild(g, x, ancestors);
	}

	void doubleRotationRightChild(Node g, Node p, Stack<Node> ancestors) {
		Node x = p.left;

		p.left = x.right;
		g.right = x;
		x.right = p;

		singleRotationRightChild(g, x, ancestors);
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
	// 	RBTreeInOrderIterator iter = new RBTreeInOrderIterator(root);
	// 	ArrayList<Node> inorder = new ArrayList<>();
	// 	while (iter.hasNext()) {
	// 		inorder.add(iter.next());
	// 	}
	// 	return inorder;
	// }

	// /*
	//  * This method uses the pre order iterator to create an ArrayList of Nodes
	//  */
	// public ArrayList<Node> preOrder() {
	// 	RBTreePreOrderIterator iter = new RBTreePreOrderIterator(root);
	// 	ArrayList<Node> preorder = new ArrayList<>();
	// 	while (iter.hasNext()) {
	// 		preorder.add(iter.next());
	// 	}
	// 	return preorder;
	// }

	// /*
	//  * This method uses the post order iterator to create an ArrayList of Nodes
	//  */
	// public ArrayList<Node> postOrder() {
	// 	RBTreePostOrderIterator iter = new RBTreePostOrderIterator(root);
	// 	ArrayList<Node> postorder = new ArrayList<>();
	// 	while (iter.hasNext()) {
	// 		postorder.add(iter.next());
	// 	}
	// 	return postorder;
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
		testTree.insert(22);
		testTree.insert(6);
		testTree.insert(9);
		testTree.insert(10);
		testTree.insert(22);

		System.out.println(testTree.toString());

		
		// {(0,RED),(1,BLACK),(2,RED),(3,BLACK),(5,BLACK),(6,BLACK),(9,RED),(10,RED),(22,BLACK)}
	}
}
