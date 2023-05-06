package graph;

import java.util.ArrayList;
import java.util.Arrays;
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

    edges.sort((edge1, edge2) -> edge1.to.compareTo(edge2.to));

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

      System.out.println(vertex + " " + vertex.discoveryTime + " / " +
          vertex.finishTime);
    }
    return result;
  }

  @Override
  public ArrayList<Integer> topologicalSortDFS() {

    return new ArrayList<>(null);
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
    Set<Vertex> visited = new HashSet<>();

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
      visited.add(visitedVertex);

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

    System.out.println(testGraph.depthFirstSearch());

  }

  @Override
  public ArrayList<Integer> shortestPath(int source, int target) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'shortestPath'");
  }
}
