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

		return false;
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

		return resultGraph;
	}

	public static void main(String[] args) {
		TreeSet<Vertex> vertices = new TreeSet<>();

		vertices.add(new Vertex(0));
		vertices.add(new Vertex(3));
		vertices.add(new Vertex(2));
		vertices.add(new Vertex(5));
		vertices.add(new Vertex(6));
		vertices.add(new Vertex(1));
		vertices.add(new Vertex(4));
		vertices.add(new Vertex(7));

		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(new Vertex(0), new Vertex(3), 0));
		edges.add(new Edge(new Vertex(1), new Vertex(7), 2));
		edges.add(new Edge(new Vertex(2), new Vertex(3), 1));
		edges.add(new Edge(new Vertex(5), new Vertex(1), 3));
		edges.add(new Edge(new Vertex(6), new Vertex(7), 4));
		edges.add(new Edge(new Vertex(4), new Vertex(5), 1));
		edges.add(new Edge(new Vertex(1), new Vertex(2), 3));
		edges.add(new Edge(new Vertex(2), new Vertex(7), 4));

		Graph testGraph = new Graph(vertices, edges);

		Graph testMST = testGraph.kruskalMST();

		System.out.println(testMST.vertices);
		System.out.println(testMST.edges);
	}

}
