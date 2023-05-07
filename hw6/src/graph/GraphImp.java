package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Map.Entry;

public class GraphImp implements Graph {
  public Map<Vertex, ArrayList<Edge>> graph;

  TreeSet<Vertex> vertices;
  ArrayList<Edge> edges;

  public GraphImp(TreeSet<Vertex> vertices, ArrayList<Edge> edges) {
    graph = new HashMap<>();

    for (Vertex vertex : vertices) {
      graph.put(vertex, new ArrayList<>());
    }

    for (Edge edge : edges) {
      graph.get(edge.from).add(edge);
    }

    for (ArrayList<Edge> edgeList : graph.values()) {
      edgeList.sort((edge1, edge2) -> edge1.to.compareTo(edge2.to));
    }

    this.vertices = vertices;
    this.edges = edges;
  }

  public TreeSet<Vertex> getVertices() {
    return vertices;
  }

  @Override
  public ArrayList<Integer> depthFirstSearch() {
    for (Vertex vertex : vertices) {
      vertex.color = Color.WHITE;
      vertex.p = null;
    }

    int time = 0;
    ArrayList<Integer> result = new ArrayList<>();
    Stack<Vertex> upcoming = new Stack<>();

    for (Vertex vertex : vertices) {
      if (vertex.color == Color.WHITE) {
        time++;
        vertex.discoveryTime = time;
        upcoming.push(vertex);

        while (!upcoming.isEmpty()) {
          Vertex discoveredVertex = upcoming.peek();
          boolean isDeadend = true;

          for (Edge edge : graph.get(discoveredVertex)) {
            Vertex upcomingVertex = edge.to;

            if (upcomingVertex.color == Color.WHITE) {
              time++;
              upcomingVertex.color = Color.GREY;
              upcomingVertex.discoveryTime = time;
              upcomingVertex.p = discoveredVertex;
              upcoming.push(upcomingVertex);
              isDeadend = false;
              break;
            }
          }

          if (isDeadend) {
            upcoming.pop();
            time++;
            discoveredVertex.finishTime = time;
            discoveredVertex.color = Color.BLACK;
            result.add(discoveredVertex.value);
          }
        }
      }
    }

    return result;
  }

  @Override
  public ArrayList<Integer> topologicalSortDFS() {

    ArrayList<Integer> dfs = depthFirstSearch();
    ArrayList<Vertex> vertexList = new ArrayList<>(graph.keySet());
    vertexList.sort((vertex1, vertex2) -> vertex2.finishTime - vertex1.finishTime);

    LinkedList<Integer> result = new LinkedList<>();

    for (Vertex vertex : vertexList) {
      result.add(vertex.value);
    }

    return new ArrayList<>(result);
  }

  // Yes this method can detect a cycle presents in the graph
  // It is because when we manage vertex adjacent to the vertex v
  // using the inDegree, if a cycle presents, those vertexes can't be
  // enqueued or cancel out because they are pointing at each other,
  // causing the inDegree values stay at 1s and the queue only enqueues
  // the vertex with inDegree value of 0

  @Override
  public ArrayList<Integer> topologicalSortQueue() {
    ArrayList<Integer> result = new ArrayList<>();
    Queue<Vertex> vertexQueue = new LinkedList<>();

    for (Vertex vertex : vertices) {
      vertex.inDegree = 0;
    }

    for (Vertex vertex : vertices) {
      for (Edge edge : edges) {
        if (edge.to.equals(vertex)) {
          vertex.inDegree++;
        }
      }
    }

    for (Vertex vertex : vertices) {
      if (vertex.inDegree == 0) {
        vertexQueue.offer(vertex);
      }
    }

    while (!vertexQueue.isEmpty()) {
      Vertex visitedVertex = vertexQueue.poll();
      result.add(visitedVertex.value);

      for (Edge edge : graph.get(visitedVertex)) {
        for (Vertex vertex : vertices) {
          if (edge.to.equals(vertex)) {
            vertex.inDegree -= 1;
            if (vertex.inDegree == 0) {
              vertexQueue.offer(vertex);
            }
          }
        }
      }
    }

    if (result.size() != vertices.size()) {
      return new ArrayList<>();
    }

    return result;

  }

  @Override
  public ArrayList<Integer> shortestPath(int source, int target) {
    initSingleSource(new Vertex(source));
    ArrayList<Integer> result = new ArrayList<>();
    HashMap<Vertex, Integer> queue = new HashMap<>();
    Set<Vertex> visited = new HashSet<>();

    Vertex targetVertex = null;

    for (Vertex vertex : vertices) {
      if (vertex.value == target) {
        targetVertex = vertex;
      }
    }

    for (Vertex vertex : vertices) {
      queue.put(vertex, vertex.discoveryTime);
    }

    while (!visited.contains(targetVertex)) {
      Vertex u = extractMin(queue, visited);

      if (u == null) {
        return new ArrayList<>();
      }

      visited.add(u);

      for (Edge edge : graph.get(u)) {
        Vertex v = edge.to;

        if (queue.get(v) > u.discoveryTime + edge.weight) {
          v.p = u;
          queue.put(v, u.discoveryTime + edge.weight);
        }
      }
    }

    while (targetVertex != null) {
      result.add(0, targetVertex.value);
      targetVertex = targetVertex.p;
    }    
     
    return result;
  }

  public Vertex extractMin(HashMap<Vertex, Integer> queue, Set<Vertex> visited) {
    int minDist = Integer.MAX_VALUE;
    Vertex minDistVertex = null;

    for (Vertex vertex : vertices) {
      if (!visited.contains(vertex) && queue.get(vertex) < minDist) {
        minDistVertex = vertex;
        minDist = queue.get(vertex);
      }
    }

    queue.remove(minDistVertex);

    return minDistVertex;
  }

  public void initSingleSource(Vertex s) {
    for (Vertex vertex : vertices) {
      if (vertex.equals(s)) {
        s.discoveryTime = 0;
      } else {
        vertex.discoveryTime = Integer.MAX_VALUE;
        vertex.p = null;
      }
    }
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

    GraphImp testGraph = new GraphImp(vertices, edges);

    // System.out.println(testGraph.depthFirstSearch());
    // System.out.println(testGraph.topologicalSortDFS());
    // System.out.println(testGraph.topologicalSortQueue());
    System.out.println(testGraph.shortestPath(1, 4));
  }

}
