/**
 * Project:    BuddE Network
 * Filename:   SocialNetworkADT.java
 * Associated Files: Main.java, SocialNetwork.java, GraphADT.java, Graph.java, 
 * 					 GraphTest.java, User.java, SocialNetworkTest.java, 
 * 					 DuplicateFriendshipException.java, DuplicateUserException.java,
 * 					 FriendshipNotFoundException.java, UserNotFoundException.java,
 * 					 IllegalNullArgumentException.java
 *
 * Authors:    Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * 
 */
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author 
 *
 */
public interface SocialNetworkADT {
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
  public void addFriendship(String user1, String user2)
      throws IllegalNullArgumentException, DuplicateFriendshipException;

  /**
   * Given two names, find corresponding users and remove friendship between
   * them.
   *
   * If either username is null or empty string, throw
   * IllegalNullArgumentException. If either username does not exist in the
   * social network, throw UserNotFoundException. If friendship does not exist,
   * throw FriendshipNotFoundException.
   * 
   * Valid argument conditions: 1. neither username is null or empty string 2.
   * both users exist in the social network 3. friendship exists in the social
   * network.
   * 
   * @param user1 username of first user of this friendship.
   * @param user2 username of second user of this friendship.
   * 
   * @throws IllegalNullArgumentException if either argument is null or empty
   *                                      string.
   * @throws UserNotFoundException        if any user does not exist in the
   *                                      social network.
   * @throws FriendshipNotFoundException  if friendship does not exist in the
   *                                      social network.
   */
  public void removeFriendship(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException,
      FriendshipNotFoundException;

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
  public void addUser(String username)
      throws IllegalNullArgumentException, DuplicateUserException;

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
  public void removeUser(String username)
      throws IllegalNullArgumentException, UserNotFoundException;

  /**
   * Get all the users of the social network.
   *
   * @return a set of all users of the social network.
   */
  public Set<User> getAllUsers();

  /**
   * Get all the usernames of the social network.
   *
   * @return a set of all usernames of the social network.
   */
  public Set<String> getAllUsernames();

  /**
   * Given a name, find corresponding user, return all the friends with the
   * given user.
   *
   * If username is null or empty string, throw IllegalNullArgumentException. If
   * username does not exist in the social network, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * username exists in the social network.
   * 
   * @param username name of the user to return all the friends.
   * @return set of friends of the given user.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws UserNotFoundException        if username does not exist in the
   *                                      social network.
   */
  public Set<User> getFriends(String username)
      throws IllegalNullArgumentException, UserNotFoundException;

  /**
   * Given two names, find corresponding users, return all mutual friends
   * between them.
   *
   * If username is null or empty string, throw IllegalNullArgumentException. If
   * username does not exist in the social network, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * username exists in the social network.
   * 
   * @param user1 name of first user to find mutual friends.
   * @param user2 name of second user to find mutual friends.
   * @return set of friends of the given user.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws UserNotFoundException        if username does not exist in the
   *                                      social network.
   */
  public Set<String> getMutualFriends(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException;

  /**
   * Given two names, find corresponding users, then find the shortest path to
   * connect from one user to another.
   *
   * If username is null or empty string, throw IllegalNullArgumentException. If
   * username does not exist in the social network, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * username exists in the social network.
   * 
   * @param user1 name of first user to find mutual friends.
   * @param user2 name of second user to find mutual friends.
   * @return list of friends to connect two users.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws UserNotFoundException        if username does not exist in the
   *                                      social network.
   */
  public List<User> getShortestPath(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException;

  /**
   * Build graphs of connected components and return a set of those graphs.
   * 
   * @return set of graphs of connected of components.
   */
  public Set<Graph> getConnectedComponents();

  /**
   * Load commands from file to construct social network.
   *
   * If filename is null or empty string, throw IllegalNullArgumentException. If
   * file is not found, throw FileNotFoundException.
   * 
   * Valid argument conditions: 1. filename is neither null nor empty string 2.
   * file exists.
   * 
   * @param filename name of file to load commands from.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws IOException                  if there is error happening while
   *                                      reading file.
   */
  public void loadFromFile(String filename)
      throws IllegalNullArgumentException, IOException;

  /**
   * Save changes to the graph as commands to file.
   *
   * If filename is null or empty string, throw IllegalNullArgumentException. If
   * file is not found, throw FileNotFoundException.
   * 
   * Valid argument conditions: 1. filename is neither null nor empty string 2.
   * file exists.
   * 
   * @param filename name of file to load commands from.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws FileNotFoundException        if file does not exist.
   * @throws IOException                  if there is an error in saving changes
   *                                      to a file.
   */
  public void saveToFile(String filename)
      throws IllegalNullArgumentException, FileNotFoundException, IOException;

  /**
   * Gets the central user of the SocialNetwork.
   * 
   * @return the central user.
   */
  public User getCentralUser();

  /**
   * Sets the central user of the SocialNetwork.
   * 
   * If username is null or empty string, throw IllegalNullArgumentException. If
   * username does not exist in the social network, throw UserNotFoundException.
   * 
   * Valid argument conditions: 1. username is neither null nor empty string 2.
   * username exists.
   * 
   * @param username name of the user to be set as central user.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws UserNotFoundException        if username does not exist in the
   *                                      social network.
   */
  public void setCentralUser(String username)
      throws IllegalNullArgumentException, UserNotFoundException;
  
  /**
   * Return the number of users in the social network.
   * 
   * @return number of users in the social network.
   */
  public int numberUsers();
  
  /**
   * Return the number of connections/friendships in the social network.
   * @return the number of connections/friendships in the social network.
   */
  public int numberConnections();
}
