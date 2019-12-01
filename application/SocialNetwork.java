package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SocialNetwork implements SocialNetworkADT {

  private Graph graph;

  public SocialNetwork() {
    this.graph = new Graph();
  }

  /**
   * Given two names, find corresponding users and add friendship between them.
   *
   * If username is null or empty string, throw IllegalNullArgumentException.
   * 
   * Special case: if there is no existing user that correspond to any given
   * name, then add the user(s) first and then add friendship between them.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * two users do not have existing friendship yet.
   * 
   * @param user1 username of first user of this friendship.
   * @param user2 username of second user of this friendship.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws DuplicateFriendshipException if friendship between the two users
   *                                      exists in the social network.
   */
  @Override
  public void addFriendship(String user1, String user2)
      throws IllegalNullArgumentException, DuplicateFriendshipException {
    if (user1 == null || user2 == null) {
      throw new IllegalNullArgumentException();
    }
    if (user1.length() == 0 || user2.length() == 0) {
      throw new IllegalNullArgumentException();
    }
    
    // Get user instances
    User userInstance1 = null;
    User userInstance2 = null;
    try {
      userInstance1 = this.graph.getUser(user1);
    } catch (UserNotFoundException e) {
      userInstance1 = new User(user1);
      try {
        this.graph.addVertex(userInstance1);
      } catch (DuplicateUserException e1) {
        // DuplicateUserException soh
        e1.printStackTrace();
      }
    }
    try {
      userInstance2 = this.graph.getUser(user2);
    } catch (UserNotFoundException e) {
      userInstance2 = new User(user2);
      this.graph.addVertex(userInstance2);
    }

    // Add edge in the graph while checking for a DuplicateFriendshipException
    this.graph.addEdge(userInstance1, userInstance2);
  }

  @Override
  public void removeFriendship(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException,
      FriendshipNotFoundException {
    // TODO Auto-generated method stub

  }

  /**
   * Given a name, add new user to the social network.
   *
   * If username is null or empty string, throw IllegalNullArgumentException. If
   * username already exists, throw DuplicateUserException.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * username is not already in the social network.
   * 
   * @param username name of new user to be added to the social network.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws DuplicateUserException       if username already exists in social
   *                                      network.
   */
  @Override
  public void addUser(String username) throws IllegalNullArgumentException,
      DuplicateUserException {
    // Create an instance of User from the username
    User newUser = new User(username);
    this.users.add(newUser);
  }
  
  /**
   * Given a name, find corresponding user, remove user and all of his/her
   * associated friendships in the social network.
   *
   * If username is null or empty string, throw IllegalNullArgumentException. If
   * username does not exist in the social network, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * username exists in the social network.
   * 
   * @param username name of the user to be removed from the social network.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws UserNotFoundException        if username does not exist in the
   *                                      social network.
   */
  @Override
  public void removeUser(String username) throws IllegalNullArgumentException,
      UserNotFoundException {
    if (username == null || username.length() == 0) {
      throw new IllegalNullArgumentException();
    }
    
  }

  @Override
  public Set<User> getFriends(String username)
      throws IllegalNullArgumentException, UserNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<User> getMutualFriends(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<User> getShortestPath(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Graph> getConnectedComponents() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void loadFromFile(File filename) throws IllegalNullArgumentException,
      FileNotFoundException {
    // TODO Auto-generated method stub

  }

  @Override
  public void saveToFile(File filename) throws IllegalNullArgumentException,
      FileNotFoundException {
    // TODO Auto-generated method stub

  }
}
