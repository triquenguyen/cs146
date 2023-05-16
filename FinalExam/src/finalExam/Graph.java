package finalExam;

import java.util.*;



public class Graph {
    
    public static class Vertex implements Comparable<Vertex>{
        
        int value;
        int discoveryTime; //for DFS and Shortest Path
        int finishTime; //for DFS
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
    
    public static class Edge {
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
    
    
    Map<Vertex, ArrayList<Edge>> graph;
    
    
    public Graph(Collection<Vertex> vertices, Collection<Edge> edges) {
        Comparator<Edge> compareByNeighbor = (Edge e1, Edge e2) -> e1.to.compareTo(e2.to);
        graph = new TreeMap<>();
        for(Vertex v: vertices) {
            ArrayList<Edge> neighbors = new ArrayList<>();
            for(Edge e: edges) {
                if(v.equals(e.from))
                    neighbors.add(e);
            }
            neighbors.sort(compareByNeighbor);
            graph.put(v,neighbors);
        }
    }
    
    //convert Graph object into an undirected graph
    public Graph unDirectedGraph() {
        HashSet<Edge> allEdges = new HashSet<>();
        
        
        for(Vertex v: getVertices()) {
            for(Edge e: graph.get(v)) {
                allEdges.add(new Edge(e.from,e.to,e.weight));
                allEdges.add(new Edge(e.to,e.from,e.weight));
                
            }
        }
        
        return new Graph(getVertices(),allEdges);
    }
    
    public Collection<Vertex> getVertices(){
        return graph.keySet();
    }
    
    
    public String toString() {
        String s = "";
        for (Map.Entry<Vertex, ArrayList<Edge>> vert : graph.entrySet()) {
            Vertex key = vert.getKey();
            ArrayList<Edge> edges = vert.getValue();
            s = s+ key.toString() + "->"+edges.toString()+"\n";
            
        }
        return s;
    }
    
    public Graph primsMST() {
        
        //stand in so the code compiles
        return new Graph(getVertices(),new HashSet<Edge>());
        
    }
    
    public ArrayList<Integer> shortestPathBF(int source, int target) throws PathException{
        return new ArrayList<Integer>();
        
    }
    
    
    
}
