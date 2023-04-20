package graphs;

import java.util.*;

public class UndirectedGraph {

	static class Vertex {
		int val;
		Vertex next; //next vertex in the list
		Node edge; //pointer to first edge


		Vertex(int u,Vertex next){
			val = u; 
			this.next = next;
			edge = null;
		}

		//helpful for printing and debugging
		public String toString() {
			String s = val+"->";
			Node node = edge;
			while(node!=null) {
				s = s+ node.vert.val+",";
				node = node.next;
			}
			return s.substring(0,s.length()-1);

		}

	}
	static class Node {
		Vertex vert;
		Node next;

		Node(Vertex v, Node next){
			this.vert = v;
			this.next = next;
		}
	}

	Vertex vertices;//pointer to first vertex in the graph (vertex with the smallest val)


	public UndirectedGraph() {
		vertices = null;
	}


	public boolean addVertex(int value) {
		//add a new vertex with val value
		//return true if a vertex is added
		//return false if a vertex with val value already exists
		//the vertex list is kept in sorted order based on the value (smallest to largest)
		
		return true;
	}

	



	public boolean addEdge(int val1, int val2) {
		//You can assume there exists vertices with vals equal to val1 and val2
		//(i.e., there are Vertex objects in vertices that have vals equal to val1 and val2
		//You might want a helper method here that can find a vertex with val v
		//The Nodes in the edge list should be stored in ascending order by vert.val
		//If the edge already exists return false
		//Note that this is an undirected graph so you should create an edge from
		//val1 to val2 and val2 to val1.
		return true;
	}

	public ArrayList<Integer> breadthFirstSearch(int initial){
		//This method should return an ArrayList of integers corresponding to
		//the values associated with the vertices in the graph visited in breadth first order
		//You can assume that the graph contains a vertex with value initial
		//Instead of changing the colors of the nodes we will keep track of the values
		//of the nodes we've visited in the visited ArrayList.
		//You must implement this using a queue
		// enqueue -> offer
		// deque -> poll
		ArrayList<Integer> visited = new ArrayList<>();
		ArrayList<Integer> bfs = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>();
		


		return bfs;

	}

	//String representation of the graph
	public String toString() {
		String s = "";
		Vertex vert = vertices;
		while(vert!=null) {
			s = s + vert.toString() + "\n";
			vert = vert.next;
		}
		return s;
	}


}
