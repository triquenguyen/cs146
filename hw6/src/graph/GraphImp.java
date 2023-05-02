package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class GraphImp implements Graph {
  public Map<Vertex, ArrayList<Edge>> graph;

  TreeMap<Integer, Vertex> vertices;
  ArrayList<Edge> edges;

  public GraphImp(TreeMap<Integer, Vertex> vertices, ArrayList<Edge> edges) {
    this.vertices = vertices;
    this.edges = edges;
  }

  public TreeMap<Integer, Vertex> getVertices() {
    return vertices;
  }

  @Override
  public ArrayList<Integer> depthFirstSearch() {
    ArrayList<Integer> result = new ArrayList<>();
    Set<Vertex> visited = new HashSet<>();
    Stack<Vertex> upcoming = new Stack<>();

    for (Entry<Integer, Vertex> entry : vertices.entrySet()) {
      Vertex vertex = entry.getValue();

      if (!visited.contains(vertex)) {
        visited.add(vertex);
        upcoming.push(vertex);
        vertex.color = Color.GREY;
      }

      while (!upcoming.empty()) {
        Vertex visitedVertex = upcoming.pop();
        vertex.color = Color.BLACK;
        result.add(visitedVertex.value);

        for (Edge edge : edges) {
          if (edge.from.equals(visitedVertex) && !visited.contains(edge.to)) {
            visited.add(edge.to);
            upcoming.push(edge.to);
            edge.to.color = Color.GREY;
          }
        }
      }
    }

    return result;
  }

  @Override
  public ArrayList<Integer> topologicalSortDFS() {
    return new ArrayList<>(null);
  }

  @Override
  public ArrayList<Integer> topologicalSortQueue() {
    ArrayList<Integer> result = new ArrayList<>();
    Queue<Vertex> vertexQueue = new ArrayDeque<>();

    int inDegree[] = new int[vertices.size()];
    Arrays.fill(inDegree, 0);

    for (Entry<Integer, Vertex> entry : vertices.entrySet()) {
      Integer index = entry.getKey();
      Vertex vertex = entry.getValue();
      for (Edge edge : edges) {
        if (edge.to.equals(vertex)) {
          inDegree[index] += 1;
        }
      }
    }

    for (Entry<Integer, Vertex> entry : vertices.entrySet()) {
      Integer index = entry.getKey();
      Vertex vertex = entry.getValue();
      if (inDegree[index] == 0) {
        vertexQueue.offer(vertex);
      }
    }

    while (!vertexQueue.isEmpty()) {
      Vertex sortedVertex = vertexQueue.poll();
      result.add(sortedVertex.value);

      for (Edge edge : edges) {
        if (edge.to.equals(sortedVertex)) {
          for (Entry<Integer, Vertex> entry : vertices.entrySet()) {
            Integer index = entry.getKey();
            Vertex vertex = entry.getValue();

            if (edge.from.equals(vertex)) {
              inDegree[index] -= 1;
              ;
            }

            if (inDegree[index] == 0) {
              vertexQueue.offer(vertex);
            }
          }
        }
      }

      // for (Entry<Integer, Vertex> entry : vertices.entrySet()) {
      // Integer index = entry.getKey();
      // Vertex vertex = entry.getValue();

      // for (Edge edge : edges) {
      // if (edge.from.equals(vertex) && edge.to.equals(sortedVertex)) {
      // inDegree[index] -= 1;
      // }
      // }

      // if (inDegree[index] == 0) {
      // vertexQueue.offer(vertex);
      // }
      // }
    }

    // for (int i : inDegree) {
    // result.add(i);
    // }

    return result;
  }

  public static void main(String[] args) {
    TreeMap<Integer, Vertex> vertices = new TreeMap<>();
    Vertex vertex1 = new Vertex(1);
    Vertex vertex2 = new Vertex(2);
    Vertex vertex3 = new Vertex(3);
    Vertex vertex4 = new Vertex(4);
    Vertex vertex5 = new Vertex(5);
    Vertex vertex6 = new Vertex(6);
    Vertex vertex7 = new Vertex(7);
    Vertex vertex8 = new Vertex(8);

    vertices.put(0, vertex1);
    vertices.put(1, vertex2);
    vertices.put(2, vertex3);
    vertices.put(3, vertex4);
    vertices.put(4, vertex5);
    vertices.put(5, vertex6);
    vertices.put(6, vertex7);
    vertices.put(7, vertex8);

    ArrayList<Edge> edges = new ArrayList<>();
    edges.add(new Edge(vertex1, vertex2, 0));
    edges.add(new Edge(vertex6, vertex8, 2));
    edges.add(new Edge(vertex3, vertex2, 1));
    edges.add(new Edge(vertex4, vertex6, 3));
    edges.add(new Edge(vertex5, vertex8, 4));
    edges.add(new Edge(vertex5, vertex4, 1));
    edges.add(new Edge(vertex6, vertex3, 3));
    edges.add(new Edge(vertex6, vertex7, 3));

    GraphImp testGraph = new GraphImp(vertices, edges);

    System.out.println(testGraph.topologicalSortQueue());
  }

  @Override
  public ArrayList<Integer> shortestPath(int source, int target) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'shortestPath'");
  }
}
