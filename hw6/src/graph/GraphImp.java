package graph;

import java.util.ArrayList;
import java.util.Arrays;
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
    this.vertices = vertices;
    this.edges = edges;
  }

  public TreeSet<Vertex> getVertices() {
    return vertices;
  }

  @Override
  public ArrayList<Integer> depthFirstSearch() {
    ArrayList<Integer> result = new ArrayList<>();
    Set<Vertex> visited = new HashSet<>();
    Stack<Vertex> upcoming = new Stack<>();

    for (Vertex vertex : vertices) {
      if (!visited.contains(vertex)) {
        visited.add(vertex);
        upcoming.push(vertex);
        vertex.color = Color.GREY;
        vertex.discoveryTime++;
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
            edge.to.discoveryTime++;
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

      for (Edge edge : edges) {
        if (edge.from.equals(visitedVertex)) {
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
    edges.add(new Edge(vertex2, vertex1, 0));
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
