/**
 * Project: BuddE Network
 * 
 * Filename: SocialNetwork.java
 * 
 * Associated Files: Main.java, SocialNetworkADT.java, GraphADT.java,
 * Graph.java, GraphTest.java, User.java, SocialNetworkTest.java,
 * DuplicateFriendshipException.java, DuplicateUserException.java,
 * FriendshipNotFoundException.java, UserNotFoundException.java,
 * IllegalNullArgumentException.java
 *
 * Authors: Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * SocialNetwork represents the buddE network that allows users to add/remove
 * users, add/remove friendships, see mutual buddies, and find shortest path
 * from one user to another.
 * 
 * @author Khoa Thien Le (Harry), Kenneth Mui.
 */
public class SocialNetwork implements SocialNetworkADT {

  private Graph graph; // Graph instance.
  private ArrayList<String> commands; // Commands to save to file.
  private User centralUser; // Central user.

  /**
   * Default constructor that initializes instance fields for social network.
   */
  public SocialNetwork() {
    this.graph = new Graph();
    this.commands = new ArrayList<String>();
    this.centralUser = null;
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
  public void addFriendship(String user1, String user2)
      throws IllegalNullArgumentException, DuplicateFriendshipException {
    // Get user instances, IllegalNullArgumentException may be thrown.
    User userInstance1 = this.graph.getUser(user1);
    User userInstance2 = this.graph.getUser(user2);

    if (userInstance1 == null) { // User not found, create user.
      userInstance1 = new User(user1);
    }
    if (userInstance2 == null) { // User not found, create user.
      userInstance2 = new User(user2);
    }

    // Add friendship, DuplicateFriendshipException may be thrown.
    this.graph.addEdge(userInstance1, userInstance2);

    // Add command to list of commands.
    this.commands.add("a " + user1 + " " + user2);
  }

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
      FriendshipNotFoundException {
    // Get user instances, IllegalNullArgumentException may be thrown.
    User userInstance1 = this.graph.getUser(user1);
    User userInstance2 = this.graph.getUser(user2);

    if (userInstance1 == null || userInstance2 == null) { // User not found.
      throw new UserNotFoundException();
    }

    // Remove friendship, FriendshipNotFoundException may be thrown.
    this.graph.removeEdge(userInstance1, userInstance2);

    // Add command to list of commands.
    this.commands.add("r " + user1 + " " + user2);
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
  public void addUser(String username)
      throws IllegalNullArgumentException, DuplicateUserException {
    if (username == null || username.length() == 0) {
      throw new IllegalNullArgumentException();
    }

    // Create an instance of User from the username.
    User newUser = new User(username);

    // Adds user to the graph, DuplicateUserException may be thrown.
    this.graph.addVertex(newUser);

    // Add command to list of commands.
    this.commands.add("a " + username);
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
  public void removeUser(String username)
      throws IllegalNullArgumentException, UserNotFoundException {
    // Get user instance, IllegalNullArgumentException may be thrown.
    User userToRemove = this.graph.getUser(username);

    if (userToRemove == null) { // User not found.
      throw new UserNotFoundException();
    } else { // User found.
      this.graph.removeVertex(userToRemove);
    }

    // Add command to list of commands.
    this.commands.add("r " + username);
  }

  /**
   * Get all the users of the social network.
   *
   * @return a set of all users of the social network.
   */
  public Set<User> getAllUsers() {
    return this.graph.getAllVertices();
  }

  /**
   * Get all the usernames of the social network.
   *
   * @return a set of all usernames of the social network.
   */
  public Set<String> getAllUsernames() {
    return this.graph.getAllUsernames();
  }

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
      throws IllegalNullArgumentException, UserNotFoundException {
    // Get user instance, IllegalNullArgumentException may be thrown.
    User user = this.graph.getUser(username);

    if (user == null) { // User not found.
      throw new UserNotFoundException();
    }

    return user.getFriends(); // User found.
  }

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
      throws IllegalNullArgumentException, UserNotFoundException {
    // Get user instances, IllegalNullArgumentException may be thrown.
    User userInstance1 = this.graph.getUser(user1);
    User userInstance2 = this.graph.getUser(user2);

    if (userInstance1 == null || userInstance2 == null) { // User not found.
      throw new UserNotFoundException();
    }

    // Get friends of both users
    Set<User> friendsOfUser1 = userInstance1.getFriends();
    Set<User> friendsOfUser2 = userInstance2.getFriends();

    // Get mutual friends
    Set<String> mutualFriends = new HashSet<String>();
    for (User friend : friendsOfUser1) {
      if (friendsOfUser2.contains(friend)) {
        mutualFriends.add(friend.getName());
      }
    }
    return mutualFriends;
  }

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
      throws IllegalNullArgumentException, UserNotFoundException {
    List<User> path = new LinkedList<User>();
    // Get user instances, IllegalNullArgumentException may be thrown.
    User startUser = this.graph.getUser(user1);
    User endUser = this.graph.getUser(user2);

    if (startUser == null || endUser == null) { // User not found.
      throw new UserNotFoundException();
    }

    // Keep track if each user is visited.
    HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    // Mark each user unvisited.
    for (String username : graph.getAllUsernames()) {
      visited.put(username, false);
    }

    // Keep track of weight relative to startUser.
    HashMap<String, Integer> weight = new HashMap<String, Integer>();
    // Mark each user's weight to be max value of integer.
    for (String username : graph.getAllUsernames()) {
      weight.put(username, Integer.MAX_VALUE);
    }

    // Keep track of predecessor.
    HashMap<String, User> predecessor = new HashMap<String, User>();
    // Mark each user's predecessor as null.
    for (String username : graph.getAllUsernames()) {
      predecessor.put(username, null);
    }

    weight.put(user1, 0); // Set start vertex weight to 0.


    class UserWeightComparator implements Comparator<User> {
      public int compare(User u1, User u2) {
        if (weight.get(u1.getName()) < weight.get(u2.getName()))
          return -1;
        else if (weight.get(u1.getName()) > weight.get(u2.getName()))
          return 1;
        return 0;
      }
    }

    PriorityQueue<User> pq =
        new PriorityQueue<User>(new UserWeightComparator());
    pq.add(startUser);

    while (!pq.isEmpty()) {
      User minUser = pq.remove();
      visited.put(minUser.getName(), true);

      for (User friend : minUser.getFriends()) {
        if (!visited.get(friend.getName())) {
          int currentWeight = weight.get(friend.getName());
          int newWeight = weight.get(minUser.getName()) + 1;
          if (currentWeight > newWeight) {
            weight.put(friend.getName(), newWeight);
            predecessor.put(friend.getName(), minUser);
            pq.add(friend);
          }
        }
      }
    }

    String pointerUser = user2;
    path.add(endUser);
    while (true) {
      User currentUser = predecessor.get(pointerUser);
      if (currentUser != null && currentUser.getName().equals(user1)) {
        path.add(0, startUser);
        break;
      }
      path.add(0, currentUser);
      pointerUser = currentUser.getName();

    }
    return path;
  }

  /**
   * Build graphs of connected components and return a set of those graphs.
   * 
   * @return set of graphs of connected of components.
   */
  public Set<Graph> getConnectedComponents() {
    Set<Graph> connectedComponents = new HashSet<Graph>();
    // HashMap for visited users.
    HashMap<String, Boolean> visited = new HashMap<String, Boolean>();

    // Mark each user unvisited.
    for (String username : graph.getAllUsernames()) {
      visited.put(username, false);
    }

    for (String username : graph.getAllUsernames()) {
      if (!visited.get(username)) {
        Graph componentGraph = traverse(username, visited);
        connectedComponents.add(componentGraph);
      }
    }
    return connectedComponents;
  }

  private Graph traverse(String username, HashMap<String, Boolean> visited) {
    User user = null;
    Graph componentGraph = new Graph();
    try {
      user = this.graph.getUser(username);
      Stack<User> path = new Stack<User>();
      path.add(user);
      componentGraph.addVertex(user);

      while (!path.isEmpty()) {
        User current = path.pop();
        visited.put(current.getName(), true);
        for (User friend : current.getFriends()) {
          try {
            if (!visited.get(friend.getName())) {
              path.add(friend);
              componentGraph.addVertex(friend);
            }
          } catch (Exception e) {
          }
        }
      }
    } catch (Exception e) {
      System.out.println(
          "Error happened. This line should have never been printed out!");
    }

    return componentGraph;
  }

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
      throws IllegalNullArgumentException, IOException {
    if (filename == null || filename.length() == 0) {
      throw new IllegalNullArgumentException();
    }

    FileInputStream file = null; // File input stream.
    Scanner scnr; // Define scanner.

    file = new FileInputStream(filename); // Read a file.
    // Assign variable to scanner reading from a file.
    scnr = new Scanner(file);
    while (scnr.hasNextLine()) {
      String line = scnr.nextLine().trim();
      String[] command = line.split(" ");
      if (command.length > 1) {
        // Add friendship command.
        if (command.length == 3 && command[0].contentEquals("a")) {
          try {
            this.addFriendship(command[1], command[2]);
          } catch (Exception e) {

          }
        }
        // Add user command.
        else if (command.length == 2 && command[0].contentEquals("a")) {
          try {
            this.addUser(command[1]);
          } catch (Exception e) {
          }
        }
        // Remove friendship command.
        else if (command.length == 3 && command[0].contentEquals("r")) {
          try {
            this.removeFriendship(command[1], command[2]);
          } catch (Exception e) {
          }
        }
        // Remove user command.
        else if (command.length == 3 && command[0].contentEquals("r")) {
          try {
            this.removeUser(command[1]);
          } catch (Exception e) {
          }
        }
        // Set central user command.
        else if (command.length == 2 && command[0].contentEquals("s")) {
          try {
            this.setCentralUser(command[1]);
          } catch (Exception e) {
          }
        }
      }
    }
    scnr.close(); // Close scanner.
    file.close(); // Close file.
  }

  /**
   * Save changes to the graph as commands to file.
   *
   * If filename is null or empty string, throw IllegalNullArgumentException. If
   * file is not found, throw FileNotFoundException.
   * 
   * Valid argument conditions: 1. filename is neither null nor empty string 2.
   * file exists.
   * 
   * @param filename name of file to save the changes in the SocialNetwork.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws IOException                  if there is an error in saving changes
   *                                      to a file.
   */
  public void saveToFile(String filename)
      throws IllegalNullArgumentException, IOException {
    if (filename == null || filename.length() == 0) {
      throw new IllegalNullArgumentException();
    }
    File file = new File(filename);
    BufferedWriter out = new BufferedWriter(new FileWriter(file));
    for (String command : this.commands) {
      out.write(command + "\n");
    }
    out.close();
  }

  /**
   * Gets the central user of the SocialNetwork.
   * 
   * @return the central user.
   */
  public User getCentralUser() {
    return this.centralUser;
  }

  /**
   * Sets the central user of the SocialNetwork.
   * 
   * @throws IllegalNullArgumentException if argument is null or empty string.
   * @throws UserNotFoundException        if username does not exist in the
   *                                      social network.
   * 
   */
  public void setCentralUser(String username)
      throws IllegalNullArgumentException, UserNotFoundException {
    if (username == null || username.length() == 0) {
      throw new IllegalNullArgumentException();
    }
    // Get user from username.
    User central = this.graph.getUser(username);
    if (central == null) {
      throw new UserNotFoundException();
    }
    this.centralUser = central;
    // Add command to list of commands
    this.commands.add("s " + username);
  }

  /**
   * Return the number of users in the social network.
   * 
   * @return number of users in the social network.
   */
  public int numberUsers() {
    return this.graph.order();
  }

  /**
   * Return the number of connections/friendships in the social network.
   * 
   * @return the number of connections/friendships in the social network.
   */
  public int numberConnections() {
    return this.graph.size();
  }
}
