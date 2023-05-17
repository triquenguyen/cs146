package finalExam;

import java.util.*;

public class Graph {

    public static class Vertex implements Comparable<Vertex> {

        int value;
        int discoveryTime; // for DFS and Shortest Path
        int finishTime; // for DFS
        Vertex p; // can be used for DFS Tree and Shortest Path
        int inDegree; // for topological sort

        Vertex(int val) {
            value = val;
        }

        public String toString() {
            return Integer.toString(value);
        }

        /****** NOTE THAT WE REALLY ONLY CARE ABOUT VALUES ***********/

        // This is not a great hashCode
        public int hashCode() {
            return value;
        }

        // Compare vertices
        public boolean equals(Object o) {
            Vertex v = (Vertex) o;
            return v.value == value;

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
            return weight + from.hashCode() + to.hashCode();
        }

        // auto-generated: compares all fields
        public boolean equals(Object o) {
            Edge e = (Edge) o;
            return from.equals(e.from) && to.equals(e.to) && weight == e.weight;
        }
    }

    Map<Vertex, ArrayList<Edge>> graph;

    public Graph(Collection<Vertex> vertices, Collection<Edge> edges) {
        Comparator<Edge> compareByNeighbor = (Edge e1, Edge e2) -> e1.to.compareTo(e2.to);
        graph = new TreeMap<>();
        for (Vertex v : vertices) {
            ArrayList<Edge> neighbors = new ArrayList<>();
            for (Edge e : edges) {
                if (v.equals(e.from))
                    neighbors.add(e);
            }
            neighbors.sort(compareByNeighbor);
            graph.put(v, neighbors);
        }
    }

    // convert Graph object into an undirected graph
    public Graph unDirectedGraph() {
        HashSet<Edge> allEdges = new HashSet<>();

        for (Vertex v : getVertices()) {
            for (Edge e : graph.get(v)) {
                allEdges.add(new Edge(e.from, e.to, e.weight));
                allEdges.add(new Edge(e.to, e.from, e.weight));

            }
        }

        return new Graph(getVertices(), allEdges);
    }

    public Collection<Vertex> getVertices() {
        return graph.keySet();
    }

    public Collection<Edge> getEdges() {
        Set<Edge> visitedEdge = new HashSet<>();
        Comparator<Edge> compareByNeighbor = (Edge e1, Edge e2) -> e1.from.compareTo(e2.from);

        for (ArrayList<Edge> edges : graph.values()) {
            for (Edge edge : edges) {
                if (!visitedEdge.contains(edge)) {
                    visitedEdge.add(edge);
                }
            }
        }

        ArrayList<Edge> edges = new ArrayList<>(visitedEdge);
        edges.sort(compareByNeighbor);

        return edges;
    }

    public String toString() {
        String s = "";
        for (Map.Entry<Vertex, ArrayList<Edge>> vert : graph.entrySet()) {
            Vertex key = vert.getKey();
            ArrayList<Edge> edges = vert.getValue();
            s = s + key.toString() + "->" + edges.toString() + "\n";

        }
        return s;
    }

    public Graph primsMST() {
        for (Vertex vertex : getVertices()) {
            vertex.discoveryTime = Integer.MAX_VALUE;
            vertex.p = null;
        }

        Vertex minVertex = Collections.min(getVertices());
        minVertex.discoveryTime = 0;

        Comparator<Vertex> compareDiscoveryTime = (Vertex v1, Vertex v2) -> v1.discoveryTime - v2.discoveryTime;
        PriorityQueue<Vertex> queue = new PriorityQueue<>(compareDiscoveryTime);
        Set<Vertex> visited = new HashSet<>();
        Set<Edge> visitedEdge = new HashSet<>();

        for (Vertex vertex : graph.keySet()) {
            queue.offer(vertex);
        }

        while (!visited.contains(minVertex)) {
            Vertex u = extractMin(queue, visited);

            for (Edge edge : graph.get(u)) {
                if (!visitedEdge.contains(edge)) {
                    Vertex v = edge.to;

                    if (queue.contains(v) && v.discoveryTime > edge.weight) {
                        v.p = u;
                        v.discoveryTime = edge.weight;
                        visitedEdge.add(edge);
                    }
                }
            }

            visited.add(u);
        }

        // stand in so the code compiles
        return new Graph(getVertices(), visitedEdge);
    }

    public Vertex extractMin(PriorityQueue<Vertex> queue, Set<Vertex> visited) {
        Vertex minDistVertex = null;

        if (!visited.contains(queue.peek())) {
            minDistVertex = queue.peek();
        }

        return minDistVertex;
    }

    public ArrayList<Integer> shortestPathBF(int source, int target) throws PathException {
        Vertex sourceVertex = findVertex(source);
        Vertex targetVertex = findVertex(target);
        ArrayList<Integer> result = new ArrayList<>();

        initSingleSource(sourceVertex);

        for (int i = 1; i < getVertices().size(); i++) {
            for (Vertex vertex : getVertices()) {
                Vertex u = vertex;
                for (Edge edge : graph.get(u)) {
                    Vertex v = edge.to;
                    relax(u, v, edge.weight);
                }
            }
        }

        for (Vertex vertex : getVertices()) {
            Vertex u = vertex;
            for (Edge edge : graph.get(u)) {
                Vertex v = edge.to;
                if (v.discoveryTime > u.discoveryTime + edge.weight) {
                    throw new PathException("Negative cycle occurs");
                }
            }
        }

        while (targetVertex != null) {
            result.add(0, targetVertex.value);
            targetVertex = targetVertex.p;
        }

        return result;
    }

    public void initSingleSource(Vertex s) {
        for (Vertex vertex : getVertices()) {
            vertex.discoveryTime = Integer.MAX_VALUE;
            vertex.p = null;
        }
        s.discoveryTime = 0;
    }

    public void relax(Vertex u, Vertex v, int weight) {
        if (v.discoveryTime > u.discoveryTime + weight) {
            v.discoveryTime = u.discoveryTime + weight;
            v.p = u;
        }
    }

    public Vertex findVertex(int val) {
        for (Vertex vertex : getVertices()) {
            if (vertex.value == val) {
                return vertex;
            }
        }
        return null;
    }

    public static void main(String[] args) throws PathException {
        // TreeSet<Vertex> vertices = new TreeSet<>();
        // Vertex vertex1 = new Vertex(1);
        // Vertex vertex2 = new Vertex(2);
        // Vertex vertex3 = new Vertex(3);
        // Vertex vertex4 = new Vertex(4);
        // Vertex vertex5 = new Vertex(5);
        // Vertex vertex6 = new Vertex(6);
        // Vertex vertex7 = new Vertex(7);
        // Vertex vertex8 = new Vertex(8);

        // vertices.add(vertex1);
        // vertices.add(vertex2);
        // vertices.add(vertex3);
        // vertices.add(vertex4);
        // vertices.add(vertex5);
        // vertices.add(vertex6);
        // vertices.add(vertex7);
        // vertices.add(vertex8);

        // ArrayList<Edge> edges = new ArrayList<>();
        // edges.add(new Edge(vertex1, vertex2, 0));
        // edges.add(new Edge(vertex6, vertex8, 2));
        // edges.add(new Edge(vertex3, vertex2, 1));
        // edges.add(new Edge(vertex4, vertex6, 3));
        // edges.add(new Edge(vertex5, vertex8, 4));
        // edges.add(new Edge(vertex5, vertex4, 1));
        // edges.add(new Edge(vertex6, vertex3, 3));
        // edges.add(new Edge(vertex6, vertex7, 3));

        // Graph testGraph = new Graph(vertices, edges);
        // testGraph.unDirectedGraph();

        // Graph testMST = testGraph.primsMST();

        // System.out.println(testGraph.getVertices());
        // System.out.println(testGraph.getEdges());

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
        edges.add(new Edge(vertex2, vertex3, 0));
        edges.add(new Edge(vertex3, vertex4, 2));
        edges.add(new Edge(vertex2, vertex6, 3));
        edges.add(new Edge(vertex1, vertex6, 4));
        edges.add(new Edge(vertex1, vertex7, 3));
        edges.add(new Edge(vertex6, vertex8, 3));
        edges.add(new Edge(vertex7, vertex8, 3));
        edges.add(new Edge(vertex5, vertex4, 3));
        edges.add(new Edge(vertex8, vertex5, 3));
        edges.add(new Edge(vertex7, vertex6, 3));
        edges.add(new Edge(vertex6, vertex3, 3));
        edges.add(new Edge(vertex6, vertex4, 3));

        Graph testGraph = new Graph(vertices, edges);

        System.out.println(testGraph.shortestPathBF(1, 5));

        // testGraph.unDirectedGraph();
        // Graph mst = testGraph.primsMST();
        // System.out.println(mst.graph.values());
    }         
}
