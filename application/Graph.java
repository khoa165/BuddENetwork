package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Graph {
  // Keep track of friends of a User.
  private HashMap<User, List<User>> adjacencyList;
  // Store name of the User in a set to avoid name repetition.
  private HashSet<String> uniqueNames;
  // Number of edges/connections in the graph.
  private int size;
  // Number of vertices/users in the graph.
  private int order;

  /**
   * Default no-argument constructor that initializes size and order to 0 and
   * map, set to empty.
   */
  public Graph() {
    this.adjacencyList = new HashMap<User, List<User>>();
    this.uniqueNames = new HashSet<String>();
    this.size = 0;
    this.order = 0;
  }

  /**
   * Add new vertex to the graph.
   *
   * If user is null, throw NullPointerException. If vertex already exists,
   * throw DuplicateUserException.
   * 
   * Valid argument conditions: 1. vertex/user is non-null 2. vertex/user is not
   * already in the graph.
   * 
   * @param user new user to be added to the graph.
   */
  public void addVertex(User user)
      throws IllegalNullArgumentException, DuplicateUserException {
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
