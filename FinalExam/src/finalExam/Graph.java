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
        Comparator<Vertex> compareDiscoveryTime = Comparator.comparing((Vertex v) -> v.discoveryTime);
        PriorityQueue<Vertex> queue = new PriorityQueue<>(compareDiscoveryTime);
        Vertex minVertex = Collections.min(getVertices());

        for (Vertex vertex : getVertices()) {
            vertex.discoveryTime = Integer.MAX_VALUE;
            vertex.p = null;

            if (vertex.equals(minVertex)) {
                vertex.discoveryTime = 0;
            }

            queue.offer(vertex);
        }

        Graph MSTGraph = new Graph(getVertices(), new HashSet<>());
        Set<Vertex> visited = new HashSet<>();
        Set<Edge> visitedEdge = new HashSet<>();

        while (visited.size() != graph.keySet().size()) {
            Vertex u = extractMin(queue, visited);

            if (u.p != null) {
                MSTGraph.addMinimumEdge(u.p, u);
                MSTGraph.addMinimumEdge(u, u.p);
            }

            for (Edge edge : graph.get(u)) {
                if (!visitedEdge.contains(edge)) {
                    Vertex v = edge.to;

                    if (queue.contains(v) && v.discoveryTime > edge.weight) {
                        v.p = u;
                        v.discoveryTime = edge.weight;
                        queue.remove(v);
                        queue.offer(v);
                    }
                }
            }
            visited.add(u);
        }

        // stand in so the code compiles
        return MSTGraph;
    }

    public Vertex extractMin(PriorityQueue<Vertex> queue, Set<Vertex> visited) {
        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll();
            if (!visited.contains(minVertex)) {
                return minVertex;
            }
        }
        return null;
    }

    public void addMinimumEdge(Vertex from, Vertex to) {
        Edge edge = new Edge(from, to);
        ArrayList<Edge> edges;

        if (graph.containsKey(from)) {
            edges = graph.get(from);
        } else {
            edges = new ArrayList<>();
        }
        
        edges.add(edge);
        graph.put(from, edges);
    }

    public ArrayList<Integer> shortestPathBF(int source, int target) throws PathException {
        Vertex sourceVertex = findVertex(source);
        Vertex targetVertex = findVertex(target);
        ArrayList<Integer> result = new ArrayList<>();

        initSingleSource(sourceVertex);

        for (int i = 1; i < getVertices().size(); i++) {
            for (Edge edge : getEdges()) {
                relax(edge.from, edge.to, edge.weight);
            }
        }

        for (Edge edge : getEdges()) {
            if (edge.from.discoveryTime != Integer.MAX_VALUE && edge.from.discoveryTime + edge.weight < edge.to.discoveryTime) {
                throw new PathException("Negative cycle occurs");
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
        if (u.discoveryTime != Integer.MAX_VALUE && v.discoveryTime > u.discoveryTime + weight) {
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
        // Test MST
        // TreeSet<Vertex> vertices = new TreeSet<>();
        // Vertex vertex1 = new Vertex(1);
        // Vertex vertex2 = new Vertex(2);
        // Vertex vertex3 = new Vertex(3);
        // Vertex vertex4 = new Vertex(4);
        // Vertex vertex5 = new Vertex(5);
        // Vertex vertex6 = new Vertex(6);
        // Vertex vertex7 = new Vertex(7);
        // Vertex vertex8 = new Vertex(8);
        // Vertex vertex9 = new Vertex(9);

        // vertices.add(vertex1);
        // vertices.add(vertex2);
        // vertices.add(vertex3);
        // vertices.add(vertex4);
        // vertices.add(vertex5);
        // vertices.add(vertex6);
        // vertices.add(vertex7);
        // vertices.add(vertex8);
        // vertices.add(vertex9);

        // ArrayList<Edge> edges = new ArrayList<>();
        // edges.add(new Edge(vertex1, vertex2, 4));
        // edges.add(new Edge(vertex2, vertex3, 8));
        // edges.add(new Edge(vertex3, vertex4, 7));
        // edges.add(new Edge(vertex4, vertex5, 9));
        // edges.add(new Edge(vertex5, vertex6, 10));
        // edges.add(new Edge(vertex6, vertex7, 2));
        // edges.add(new Edge(vertex7, vertex8, 1));
        // edges.add(new Edge(vertex8, vertex9, 7));
        // edges.add(new Edge(vertex8, vertex1, 8));
        // edges.add(new Edge(vertex8, vertex2, 11));
        // edges.add(new Edge(vertex9, vertex3, 2));
        // edges.add(new Edge(vertex6, vertex3, 4));
        // edges.add(new Edge(vertex9, vertex7, 6));
        // edges.add(new Edge(vertex6, vertex4, 14));

        // Graph testGraph = new Graph(vertices, edges);
        // Graph undirectedGraph = testGraph.unDirectedGraph();
        // Graph testMST = undirectedGraph.primsMST();

        // System.out.println(testMST.getVertices());
        // System.out.println(testMST.getEdges());

        //Test Shortest Path
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

        System.out.println(testGraph.shortestPathBF(1, 4));
    }
}
