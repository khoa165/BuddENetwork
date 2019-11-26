package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Graph<T> {
  private HashMap<T, List<T>> adjacencyList;
  private int size;
  private int order;

  /**
   * Default no-argument constructor that initializes size and order to 0 and
   * sets to empty.
   */
  public Graph() {
    this.adjacencyList = new HashMap<T, List<T>>();
    this.size = 0;
    this.order = 0;
  }

  /**
   * Returns the number of edges in this graph.
   * 
   * @return number of edges.
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns the number of vertices in this graph.
   * 
   * @return number of vertices.
   */
  public int order() {
    return this.order;
  }
}
