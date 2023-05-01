package mst;

import java.util.*;

public class Graph {

	static class Vertex implements Comparable<Vertex> {

		int value;

		Vertex(int val) {
			value = val;
		}

		public String toString() {
			return Integer.toString(value);
		}

		// This is not a great hashCode
		public int hashCode() {
			return value;
		}

		// Compare vertices
		public boolean equals(Object o) {
			Vertex v = (Vertex) o;
			return compareTo(v) == 0;

		}

		public int compareTo(Vertex v) {
			return value - v.value;
		}
	}

	static class Edge {
		Vertex from, to;
		int weight;

		public Edge(Vertex from, Vertex to, int weight) {

			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public Edge(Vertex i, Vertex j) {
			this.from = i;
			this.to = j;
			this.weight = 1;
		}

		public String toString() {
			return "(" + from + "<->" + to + ", " + weight + ")";
		}

	}

	static class DisjointSet {
		Map<Vertex, Vertex> source = new HashMap<>();

		public DisjointSet(Set<Vertex> vertices) {
			for (Vertex v : vertices)
				source.put(v, v);
		}

		// Find the root of the set in which Vertex v belongs.
		private Vertex find(Vertex v) {
			// Vertex v is the root
			if (source.get(v).equals(v)) {
				return v;
			}

			// recurse until we find the root
			return find(source.get(v));
		}

		// merge the two sets
		private void union(Vertex u, Vertex v) {
			// find the root of the sets in which Vertex u and Vertex v belong
			Vertex x = find(u);
			Vertex y = find(v);

			source.put(x, y); // now x and y have the same root
		}

	}

	TreeSet<Vertex> vertices;
	ArrayList<Edge> edges;

	public Graph(TreeSet<Vertex> vertices, ArrayList<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public boolean connected() {
		Vertex initialVertex = vertices.first();
		Set<Vertex> visited = new HashSet<>();

		depthFirstSearch(initialVertex, visited);

		return visited.size() == vertices.size();
	}

	public void depthFirstSearch(Vertex vertex, Set<Vertex> visited) {
		visited.add(vertex);

		for (Edge edge : edges) {
			if (edge.from == vertex && !visited.contains(edge.to)) {
				depthFirstSearch(edge.to, visited);
			} else if (edge.to == vertex && !visited.contains(edge.from)) {
				depthFirstSearch(edge.from, visited);
			}
		}
	}

	public Graph kruskalMST() {
		// use this to sort the edges
		Comparator<Edge> compareWeights = (Edge e1, Edge e2) -> e1.weight - e2.weight;
		Graph resultGraph = new Graph(new TreeSet<>(), new ArrayList<>());
		Collections.sort(edges, compareWeights);
		DisjointSet vertexSet = new DisjointSet(vertices);

		for (Edge edge : edges) {
			if (vertexSet.find(edge.from) != vertexSet.find(edge.to)) {
				resultGraph.vertices.add(edge.from);
				resultGraph.vertices.add(edge.to);
				resultGraph.edges.add(edge);
				vertexSet.union(edge.from, edge.to);
			}
		}

		return new Graph(vertices, resultGraph.edges);
	}

	public static void main(String[] args) {
		TreeSet<Vertex> vertices = new TreeSet<>();
		Vertex vertex1 = new Vertex(1);
		Vertex vertex2 = new Vertex(2);
		Vertex vertex3 = new Vertex(3);
		Vertex vertex4 = new Vertex(4);
		Vertex vertex5 = new Vertex(5);
		Vertex vertex6 = new Vertex(6);
		Vertex vertex7 = new Vertex(7);
		Vertex vertex8 = new Vertex(8);

		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);
		vertices.add(vertex4);
		vertices.add(vertex5);
		vertices.add(vertex6);
		vertices.add(vertex7);
		vertices.add(vertex8);

		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(vertex1, vertex2, 0));
		edges.add(new Edge(vertex6, vertex8, 2));
		edges.add(new Edge(vertex3, vertex2, 1));
		edges.add(new Edge(vertex4, vertex6, 3));
		edges.add(new Edge(vertex5, vertex8, 4));
		edges.add(new Edge(vertex5, vertex4, 1));
		edges.add(new Edge(vertex6, vertex3, 3));
		edges.add(new Edge(vertex6, vertex7, 3));

		Graph testGraph = new Graph(vertices, edges);

		Graph testMST = testGraph.kruskalMST();

		System.out.println(testMST.connected());

		System.out.println(testMST.vertices);
		System.out.println(testMST.edges);
	}
}
