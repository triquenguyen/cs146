package graph;


public class Edge {
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
		return "(" + from + "->" + to + ", " + weight + ")";
	}

	// auto-generated: hashes on all fields
	public int hashCode() {
		return weight+from.hashCode()+to.hashCode();
	}

	// auto-generated: compares all fields
	public boolean equals(Object o) {
		Edge e = (Edge)o;
		return from.equals(e.from)&&to.equals(e.to)&& weight==e.weight; 
		
	}
	
}
