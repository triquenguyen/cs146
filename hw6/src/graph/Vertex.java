package graph;


public class Vertex implements Comparable<Vertex>{
	
	int value;
	int discoveryTime; //for DFS and Shortest Path
	int finishTime; //for DFS
	Color color; 
	Vertex p; //can be used for DFS Tree and Shortest Path
	int inDegree; //for topological sort
	
	Vertex(int val) {
		value = val;
	}

	public String toString() {
		return Integer.toString(value);
	}

	
/****** NOTE THAT WE REALLY ONLY CARE ABOUT VALUES ***********/
	
	//This is not a great hashCode 
	public int hashCode() {
		return value;
	}

	//Compare vertices
	public boolean equals(Object o) {
		Vertex v = (Vertex)o;
		return v.value==value;
		
	}
	
	public int compareTo(Vertex v) {
		return value - v.value;
	}

	
}