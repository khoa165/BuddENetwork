package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

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
  public void addUser(String username) throws IllegalNullArgumentException,
      DuplicateUserException;

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
  public void removeUser(String username) throws IllegalNullArgumentException,
      UserNotFoundException;

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
  public Set<User> getMutualFriends(String user1, String user2)
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
   * @throws FileNotFoundException        if file does not exist.
   */
  public void loadFromFile(File filename) throws IllegalNullArgumentException,
      FileNotFoundException;

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
   */
  public void saveToFile(File filename) throws IllegalNullArgumentException,
      FileNotFoundException;
}
