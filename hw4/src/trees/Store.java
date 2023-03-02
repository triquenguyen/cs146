// package trees;

// public class Store {
//   public boolean insert(int i) {
// 		Node newNode = new Node(i, Color.RED);
// 		Stack<Node> ancestors = new Stack<>();

// 		if (root == null) {
// 			root = newNode;
// 			root.color = Color.BLACK;
// 			root.left = NIL;
// 			root.right = NIL;
// 			return true;
// 		} else {
// 			Node currNode = root;
			
// 			// Iterate through the tree and find the suitable place for the newNode
// 			while (currNode != NIL) {
// 				ancestors.push(currNode);
// 				Node leftChild = currNode.left;
// 				Node rightChild = currNode.right;

// 				if (i < currNode.data) {
// 					if (currNode.color == Color.BLACK && 
// 						leftChild.color == Color.RED && 
// 						rightChild.color == Color.RED) {
// 						fixUp(currNode);
// 					}	
// 					if (currNode.color == Color.RED) {
//             if (leftChild.color == Color.RED && leftChild.left.color == Color.RED) {
//               singleRotationLeftChild(currNode, leftChild, ancestors);
//               currNode = leftChild;
//             } else if (rightChild.color == Color.RED && rightChild.right.color == Color.RED) {
//               singleRotationRightChild(currNode, rightChild, ancestors);
//               currNode = rightChild;
//             } else if (leftChild.color == Color.RED && leftChild.right.color == Color.RED) {
//               doubleRotationLeftChild(currNode, leftChild, ancestors);
//               currNode = leftChild;
//             }	else if (rightChild.color == Color.RED && rightChild.left.color == Color.RED) {
//               doubleRotationRightChild(currNode, rightChild, ancestors);
//               currNode = rightChild;
//             }
//           }
// 					currNode = currNode.left;
// 				} else if (i > currNode.data) {
// 					if (currNode.color == Color.BLACK && 
// 						leftChild.color == Color.RED && 
// 						rightChild.color == Color.RED) {
// 						fixUp(currNode);
// 					}	
// 					if (currNode.color == Color.RED) {
// 						if (leftChild.color == Color.RED && leftChild.left.color == Color.RED) {
// 							singleRotationLeftChild(currNode, leftChild, ancestors);
// 							currNode = leftChild;
// 						} else if (rightChild.color == Color.RED && rightChild.right.color == Color.RED) {
// 							singleRotationRightChild(currNode, rightChild, ancestors);
// 							currNode = rightChild;
// 						} else if (leftChild.color == Color.RED && leftChild.right.color == Color.RED) {
// 							doubleRotationLeftChild(currNode, leftChild, ancestors);
// 							currNode = leftChild;
// 						}	else if (rightChild.color == Color.RED && rightChild.left.color == Color.RED) {
// 							doubleRotationRightChild(currNode, rightChild, ancestors);
// 							currNode = rightChild;
// 						}
// 					} 
// 					currNode = currNode.right;
// 				} else {
// 					return false;
// 				}
				 
// 			}
			
// 			// Inserting the newNode to the left or right of the parent node
// 			if (i < ancestors.peek().data) {
// 				ancestors.peek().left = newNode;
// 			} else {
// 				ancestors.peek().right = newNode;
// 			}

// 			newNode.left = NIL;
// 			newNode.right = NIL;
// 		}

// 		/*
// 		 * TODO:
// 		 * Case 1: red nodes are both left children or both right children -> single
// 		 * rotation
// 		 * Case 2: otherwise double rotation
// 		 */

// 		 return false;
// 	}

// 	void singleRotationLeftChild(Node g, Node p, Stack<Node> ancestors) {
// 		g.left = p.right;
		
// 		ancestors.pop();
// 		Node ancestor = ancestors.peek();

// 		if (g == ancestor) {
// 			root = p;
// 		} else if (g == ancestor.left) {
// 			ancestor.left = p;
// 		} else {
// 			ancestor.right = p;
// 		}

// 		p.right = g;
// 		p.color = Color.BLACK;
// 		g.color = Color.RED;
// 		ancestors.push(p);
// 	}

// 	void singleRotationRightChild(Node g, Node p, Stack<Node> ancestors) {
// 		g.right = p.left;
		
// 		ancestors.pop();
// 		Node ancestor = ancestors.peek();

// 		if (g == ancestor) {
// 			root = p;
// 		} else if (g == ancestor.left) {
// 			ancestor.left = p;
// 		} else {
// 			ancestor.right = p;
// 		}

// 		p.left = g;
// 		p.color = Color.BLACK;
// 		g.color = Color.RED;
// 		ancestors.push(p);
// 	}

// 	void doubleRotationLeftChild(Node g, Node p, Stack<Node> ancestors) {
// 		Node x = p.right;

// 		p.right = x.left;
// 		g.left = x;
// 		x.left = p;

// 		singleRotationLeftChild(g, x, ancestors);
// 	}

// 	void doubleRotationRightChild(Node g, Node p, Stack<Node> ancestors) {
// 		Node x = p.left;

// 		p.left = x.right;
// 		g.right = x;
// 		x.right = p;

// 		singleRotationRightChild(g, x, ancestors);
// 	}
// 	void fixUp(Node currNode) {
// 		currNode.color = Color.RED;
// 		currNode.left.color = Color.BLACK;
// 		currNode.right.color = Color.BLACK;		
// 	}
// }
