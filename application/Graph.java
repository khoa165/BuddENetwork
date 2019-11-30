package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph implements GraphADT {
  // Keep track of friends of a User.
  private HashMap<User, HashSet<User>> adjacencyList;
  // Keep track of mapping from name to user.
  private HashMap<String, User> mapNameToUser;
  // Number of edges/connections in the graph.
  private int size;
  // Number of vertices/users in the graph.
  private int order;

  /**
   * Default no-argument constructor that initializes size and order to 0 and
   * map, set to empty.
   */
  public Graph() {
    this.adjacencyList = new HashMap<User, HashSet<User>>();
    this.mapNameToUser = new HashMap<String, User>();
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

@Override
public void removeVertex(User user)
		throws IllegalNullArgumentException, UserNotFoundException {
	// TODO Auto-generated method stub
	
}

@Override
public void addEdge(User user1, User user2)
		throws IllegalNullArgumentException, DuplicateFriendshipException {
	// TODO Auto-generated method stub
	
}

@Override
public void removeEdge(User user1, User user2)
		throws IllegalNullArgumentException, UserNotFoundException,
		FriendshipNotFoundException {
	// TODO Auto-generated method stub
	
}

@Override
public User getUser(String name) throws IllegalNullArgumentException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Set<User> getNeighbors(User user)
		throws IllegalNullArgumentException, UserNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Set<User> getAllVertices() {
	// TODO Auto-generated method stub
	return null;
}
}
