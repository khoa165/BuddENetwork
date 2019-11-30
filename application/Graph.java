package application;

import java.util.HashSet;
import java.util.Set;

public class Graph implements GraphADT {
  // Set of vertices/users in the graph.
  private Set<User> vertices;
  // Set of users' names to avoid duplicate name.
  private Set<String> userNames;
  // Number of edges/connections in the graph.
  private int size;
  // Number of vertices/users in the graph.
  private int order;

  /**
   * Default no-argument constructor that initializes size and order to 0 and
   * map, set to empty.
   */
  public Graph() {
    this.vertices = new HashSet<User>();
    this.userNames = new HashSet<String>();
    this.size = 0;
    this.order = 0;
  }

  /**
   * Add new vertex/user to the graph.
   *
   * If vertex/user is null, throw IllegalNullArgumentException. If vertex/user
   * already exists, throw DuplicateUserException.
   * 
   * Valid argument conditions: 1. vertex/user is not null 2. vertex/user is not
   * already in the graph.
   * 
   * @param user new vertex/user to be added to the graph.
   * 
   * @throws IllegalNullArgumentException if argument is null.
   * @throws DuplicateUserException       if vertex/user already exists in
   *                                      graph.
   */
  @Override
  public void addVertex(User user)
      throws IllegalNullArgumentException, DuplicateUserException {
    if (user == null) {
      throw new IllegalNullArgumentException();
    }
    boolean success = this.userNames.add(user.getName()); // Add name.
    if (success) { // Add name successfully means name is not duplicate.
      this.vertices.add(user); // Then add user.
      this.order++; // Increment number of vertices.
    } else { // Fail to add name, that means name is duplicate.
      throw new DuplicateUserException();
    }
  }

  /**
   * Remove vertex/user and all of its associated edges/friendships in the
   * graph.
   *
   * If vertex/user is null, throw IllegalNullArgumentException. If vertex/user
   * does not exist in the graph, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. vertex/user is not null 2. vertex/user exist
   * in the graph.
   * 
   * @param user vertex/user to be removed from the graph.
   * 
   * @throws IllegalNullArgumentException if argument is null.
   * @throws UserNotFoundException        if vertex/user does not exist in the
   *                                      graph.
   */
  @Override
  public void removeVertex(User user)
      throws IllegalNullArgumentException, UserNotFoundException {
    if (user == null) {
      throw new IllegalNullArgumentException();
    }
    if (!this.vertices.contains(user)
        || !this.userNames.contains(user.getName())) {
      throw new UserNotFoundException();
    }
    // Loop through all friends of given user, remove given user from his/her
    // friend's friend list and remove his/her friend from given user's friend
    // list, then decrement size.
    for (User userFriend : user.getFriends()) {
      userFriend.getFriends().remove(user);
      user.getFriends().remove(userFriend);
      this.size--;
    }
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

  /**
   * Returns the number of edges/friendships in this graph.
   * 
   * @return number of edges/friendships in the graph.
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the number of vertices/users in this graph.
   * 
   * @return number of vertices/users in graph.
   */
  @Override
  public int order() {
    return this.order;
  }
}
