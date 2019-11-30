package application;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface SocialNetworkADT {
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
  public boolean addFriendship(String user1, String user2);
  
  public boolean removeFriendship(String user1, String user2);
  
  public boolean addUser(String username);
  
  public boolean removeUser(String username);
  
  public Set<User> getFriends(String username);
  
  public Set<User> getMutualFriends(String user1, String user2);
  
  public List<User> getShortestPath(String user1, String user2);
  
  public Set<Graph> getConnectedComponents();
  
  public void loadFromFile(File filename);
  
  public void saveToFile(File filename);
}
