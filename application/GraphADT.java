package application;

import java.util.Set;

public interface GraphADT {
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
  public void addVertex(User user)
      throws IllegalNullArgumentException, DuplicateUserException;

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
  public void removeVertex(User user)
      throws IllegalNullArgumentException, UserNotFoundException;

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
  public void addEdge(User user1, User user2)
      throws IllegalNullArgumentException, DuplicateFriendshipException;

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
  public void removeEdge(User user1, User user2)
      throws IllegalNullArgumentException, UserNotFoundException,
      FriendshipNotFoundException;

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
  public User getUser(String name)
      throws IllegalNullArgumentException;

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
  public Set<User> getNeighbors(User user)
      throws IllegalNullArgumentException, UserNotFoundException;

  /**
   * Get all the vertices/users of the graph.
   *
   * @return a set of all vertices/users of the graph.
   */
  public Set<User> getAllVertices();

  /**
   * Returns the number of edges/friendships in this graph.
   * 
   * @return number of edges/friendships in the graph.
   */
  public int size();


  /**
   * Returns the number of vertices/users in this graph.
   * 
   * @return number of vertices/users in graph.
   */
  public int order();
}
