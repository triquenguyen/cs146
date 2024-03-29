package graphs;

import java.util.*;

public class UndirectedGraph {

	class Vertex {
		int val;
		Vertex next; // next vertex in the list
		Node edge; // pointer to first edge

		Vertex(int u, Vertex next) {
			val = u;
			this.next = next;
			edge = null;
		}

		// helpful for printing and debugging
		public String toString() {
			String s = val + "->";
			Node node = edge;
			while (node != null) {
				s = s + node.vert.val + ",";
				node = node.next;
			}
			return s.substring(0, s.length() - 1);
		}
	}

	static class Node {
		Vertex vert;
		Node next;

		Node(Vertex v, Node next) {
			this.vert = v;
			this.next = next;
		}
	}

	static Vertex vertices;// pointer to first vertex in the graph (vertex with the smallest val)

	public UndirectedGraph() {
		vertices = null;
	}

	public boolean addVertex(int value) {
		// add a new vertex with val value
		// return true if a vertex is added
		// return false if a vertex with val value already exists
		// the vertex list is kept in sorted order based on the value (smallest to
		// largest)

		if (vertices == null) {
			vertices = new Vertex(value, null);
			return true;
		}

		if (vertices.val == value) {
			return false;
		}

		Vertex currVertex = vertices;
		Vertex prevVertex = null;

		while (currVertex != null && currVertex.val < value) {
			prevVertex = currVertex;
			currVertex = currVertex.next;
		}

		if (currVertex != null && currVertex.val == value) {
			return false;
		}
		
		Vertex newVertex = new Vertex(value, currVertex);

		if (prevVertex != null) {
			prevVertex.next = newVertex;
		} else {
			vertices = newVertex;
		}
		
		return true;
	}

	public boolean addEdge(int val1, int val2) {
		// You can assume there exists vertices with vals equal to val1 and val2
		// (i.e., there are Vertex objects in vertices that have vals equal to val1 and
		// val2
		// You might want a helper method here that can find a vertex with val v
		// The Nodes in the edge list should be stored in ascending order by vert.val
		// If the edge already exists return false
		// Note that this is an undirected graph so you should create an edge from
		// val1 to val2 and val2 to val1.

		Vertex vertex1 = findVertex(val1);
		Vertex vertex2 = findVertex(val2);
		Node edge1 = vertex1.edge;

		while (edge1 != null) {
			if (edge1.vert == vertex2) {
				return false;
			}
			edge1 = edge1.next;
		}

		vertex1.edge = addEdgeNode(vertex1.edge, vertex2);
		vertex2.edge = addEdgeNode(vertex2.edge, vertex1);

		return true;
	}

	public Node addEdgeNode(Node node, Vertex vertex) {
		Node newNode = new Node(vertex, null);

		if (node == null || node.vert.val > vertex.val) {
			newNode.next = node;
			return newNode;
		}

		Node currNode = node;

		while (currNode.next != null && currNode.next.vert.val < vertex.val) {
			currNode = currNode.next;
		}

		newNode.next = currNode.next;
		currNode.next = newNode;
		return node;
	}

	public Vertex findVertex(int val) {
		Vertex currVertex = vertices;

		while (currVertex != null && currVertex.val != val) {
			currVertex = currVertex.next;
		}

		return currVertex;
	}

	public ArrayList<Integer> breadthFirstSearch(int initial) {
		// This method should return an ArrayList of integers corresponding to
		// the values associated with the vertices in the graph visited in breadth first
		// order
		// You can assume that the graph contains a vertex with value initial
		// Instead of changing the colors of the nodes we will keep track of the values
		// of the nodes we've visited in the visited ArrayList.
		// You must implement this using a queue
		// enqueue -> offer
		// deque -> poll
		ArrayList<Integer> visited = new ArrayList<>();
		ArrayList<Integer> bfs = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>();

		Vertex s = findVertex(initial);
		queue.offer(s); // mark s gray
		visited.add(s.val); // mark s black

		while (!queue.isEmpty()) {
			Vertex u = queue.poll();
			bfs.add(u.val);
			Node edge = u.edge;
			while (edge != null) {
				Vertex v = edge.vert;
				if (!visited.contains(v.val)) {
					visited.add(v.val);
					queue.offer(v);
				}
				edge = edge.next;
			}
		}

		return bfs;

	}

	// String representation of the graph
	public String toString() {
		String s = "";
		Vertex vert = vertices;
		while (vert != null) {
			s = s + vert.toString() + "\n";
			vert = vert.next;
		}
		return s;
	}

	public static void main(String[] args) {
		UndirectedGraph test = new UndirectedGraph();
		test.addVertex(4);
		test.addVertex(14);
		test.addVertex(13);
		test.addVertex(4);
		test.addVertex(9);
		test.addVertex(1);

		test.addEdge(4, 1);
		test.addEdge(1,4);
		test.addEdge(1,9);
		test.addEdge(9,13);
		test.addEdge(13,14);
		test.addEdge(4,9);
		test.addEdge(14,4);
		
		ArrayList<Integer> testlist = test.breadthFirstSearch(4);
		for(int i = 0; i < testlist.size(); ++i) {
				System.out.print(testlist.get(i) + " ");
		}
		System.out.println("ToString: " + test);
}

}
