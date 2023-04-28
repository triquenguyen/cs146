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
		
		//This is not a great hashCode 
		public int hashCode() {
			return value;
		}

		//Compare vertices
		public boolean equals(Object o) {
			Vertex v = (Vertex)o;
			return compareTo(v)==0;
			
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
		
		public Edge(Vertex from, Vertex to) {
			this.from = from;
			this.to = to;
			this.weight = 1;
		}

		public String toString() {
			return "(" + from + "<->" + to + ", " + weight + ")";
		}
		
	}
	
	static class DisjointSet {
		Map<Vertex,Vertex> source = new HashMap<>();
		
		public DisjointSet(Set<Vertex> vertices) {
			for(Vertex v: vertices)
				source.put(v,v);
		}
		
		// Find the root of the set in which Vertex v belongs.
	    private Vertex find(Vertex v)
	    {
	        //Vertex v is the root
	        if (source.get(v).equals(v)) {
	            return v;
	        }
	 
	        //recurse until we find the root
	        return find(source.get(v));
	    }
	 
	    //merge the two sets
	    private void union(Vertex u, Vertex v)
	    {
	        //find the root of the sets in which Vertex u and Vertex v belong
	        Vertex x = find(u);
	        Vertex y = find(v);
	 
	        source.put(x, y); //now x and y have the same root
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
	
	public Graph kruskalMST(){
        //use this to sort the edges
		Comparator<Edge> compareWeights = (Edge e1, Edge e2) -> e1.weight-e2.weight;

		return new Graph(vertices,edges)
		
	}

}
