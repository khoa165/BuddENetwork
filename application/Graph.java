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

  /**
   * Add edge/friendship between two vertices/users to the graph.
   *
   * If either vertex/user is null, throw IllegalNullArgumentException. If
   * edge/friendship already exists, throw DuplicateFriendshipException.
   * 
   * Special case: if neither of vertex/user exists, then add vertices/users and
   * then add edge/friendship between them.
   * 
   * Valid argument conditions: 1. neither vertex/user is null 2.
   * edge/friendship is not already in the graph.
   * 
   * @param user1 first vertex/user of this edge/friendship.
   * @param user2 second vertex/user of this edge/friendship.
   * 
   * @throws IllegalNullArgumentException if either argument is null.
   * @throws DuplicateFriendshipException if edge/friendship already exists in
   *                                      graph.
   */
  @Override
  public void addEdge(User user1, User user2)
      throws IllegalNullArgumentException, DuplicateFriendshipException {
    if (user1 == null || user2 == null) {
      throw new IllegalNullArgumentException();
    }
    if (user1.getFriends().contains(user2)
        || user2.getFriends().contains(user1)) {
      throw new DuplicateFriendshipException();
    }
    // Add user1 to the graph if user1 does not exist yet.
    if (!this.vertices.contains(user1)
        && !this.userNames.contains(user1.getName())) {
      this.vertices.add(user1);
      this.userNames.add(user1.getName());
      this.order++; // Increment number of vertices.
    }
    // Add user2 to the graph if user2 does not exist yet.
    if (!this.vertices.contains(user2)
        && !this.userNames.contains(user2.getName())) {
      this.vertices.add(user2);
      this.userNames.add(user2.getName());
      this.order++; // Increment number of vertices.
    }
    // Add user1 and user2 to each other's friend list, then increment size.
    user1.addFriend(user2);
    user2.addFriend(user1);
    this.size++;
  }

  /**
   * Remove edge/friendship between two users to the graph.
   *
   * If either vertex/user is null, throw IllegalNullArgumentException. If
   * either vertex/user does not exist in the graph, throw
   * UserNotFoundException. If edge/friendship does not exist, throw
   * FriendshipNotFoundException.
   * 
   * Valid argument conditions: 1. neither vertex/user is null 2. both
   * vertices/users exist in the graph 3. edge/friendship exists in the graph.
   * 
   * @param user1 first vertex/user of this edge/friendship.
   * @param user2 second vertex/user of this edge/friendship.
   * 
   * @throws IllegalNullArgumentException if either argument is null.
   * @throws UserNotFoundException        if vertex/user does not exist in the
   *                                      graph.
   * @throws FriendshipNotFoundException  if edge/friendship does not exist in
   *                                      the graph.
   */
  @Override
  public void removeEdge(User user1, User user2)
      throws IllegalNullArgumentException, UserNotFoundException,
      FriendshipNotFoundException {
    if (user1 == null || user2 == null) {
      throw new IllegalNullArgumentException();
    }
    if (!this.vertices.contains(user1)
        || !this.userNames.contains(user1.getName())
        || !this.vertices.contains(user2)
        || !this.userNames.contains(user2.getName())) {
      throw new UserNotFoundException();
    }
    // If both users exist in each other friend list, then remove them from each
    // other's friend list, then decrement size.
    if (user1.getFriends().contains(user2)
        && user2.getFriends().contains(user1)) {
      user1.getFriends().remove(user2);
      user2.getFriends().remove(user1);
      this.size--;
    } else { // Otherwise, friendship is not found between given users.
      throw new FriendshipNotFoundException();
    }
  }

  /**
   * Return the User object that corresponds to the given name.
   *
   * If the given name is null, throw IllegalNullArgumentException. If the given
   * name does not match with any existing User, simply return null.
   * 
   * Valid argument conditions: 1. given name is not null.
   * 
   * @param name name to find corresponding user.
   * 
   * @throws IllegalNullArgumentException if argument is null.
   */
  @Override
  public User getUser(String name) throws IllegalNullArgumentException {
    if (name == null) {
      throw new IllegalNullArgumentException();
    }
    for (User user : this.vertices) { // Loop through all users, compare name.
      if (user.getName().equals(name)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Get all the vertices/users that connect to the give vertex/user.
   *
   * If vertex/user is null, throw IllegalNullArgumentException. If vertex/user
   * does not exist in the graph, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. vertex/user is not null 2. vertex/user exist
   * in the graph.
   * 
   * @param user vertex/user to get all the neighbors.
   * @return a set of all vertices/users that connect to the given user.
   * 
   * @throws IllegalNullArgumentException if argument is null.
   * @throws UserNotFoundException        if vertex/user does not exist in the
   *                                      graph.
   */
  @Override
  public Set<User> getNeighbors(User user)
      throws IllegalNullArgumentException, UserNotFoundException {
    if (user == null) {
      throw new IllegalNullArgumentException();
    }
    if (this.vertices.contains(user)
        || this.userNames.contains(user.getName())) {
      throw new UserNotFoundException();
    }
    return user.getFriends();
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
