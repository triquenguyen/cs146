package graph;

import java.util.*;

public interface Graph {
	
	Collection<Vertex> getVertices();
	ArrayList<Integer> depthFirstSearch();
	ArrayList<Integer> topologicalSortDFS();
	ArrayList<Integer> topologicalSortQueue();
	ArrayList<Integer> shortestPath(int source, int target);
	
	
	

}
