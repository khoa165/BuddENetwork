package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SocialNetwork implements SocialNetworkADT {

  private Graph graph;
  private ArrayList<String> commands;
  private User centralUser;

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
  public Set<User> getMutualFriends(String user1, String user2)
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
    Set<User> mutualFriends = new HashSet<User>();
    for (User friend : friendsOfUser1) {
      if (friendsOfUser2.contains(friend)) {
        mutualFriends.add(friend);
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
    ArrayList<User> shortestPath = new ArrayList<User>();

    return null;
  }

  /**
   * Build graphs of connected components and return a set of those graphs.
   * 
   * @return set of graphs of connected of components.
   */
  public Set<Graph> getConnectedComponents() {
    Set<Graph> graphs = new HashSet<Graph>();
    Set<User> users = this.graph.getAllVertices();
    for (User user : users) {
      try {
        Set<User> neighbors = this.graph.getNeighbors(user);
        Graph graphForUser = new Graph();
        for (User neighbor : neighbors) {
          try {
            graphForUser.addEdge(user, neighbor);
          } catch (DuplicateFriendshipException e) {
            // DuplicateFriendshipException should not be thrown
            e.printStackTrace();
          }
          graphs.add(graphForUser);
        }
      } catch (IllegalNullArgumentException e) {
        // IllegalNullArgumentException should not be thrown
        e.printStackTrace();
      } catch (UserNotFoundException e) {
        // UserNotFoundException should not be thrown
        e.printStackTrace();
      }
    }
    return graphs;
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
    try {
      while (scnr.hasNextLine()) {
        String line = scnr.nextLine().trim();
        String[] command = line.split(" ");
        if (command.length > 1) {
          // Add friendship command.
          if (command.length == 3 && command[0].contentEquals("a")) {
            this.addFriendship(command[1], command[2]);
          }
          // Add user command.
          else if (command.length == 2 && command[0].contentEquals("a")) {
            this.addUser(command[1]);
          }
          // Remove friendship command.
          else if (command.length == 3 && command[0].contentEquals("r")) {
            this.removeFriendship(command[1], command[2]);
          }
          // Remove user command.
          else if (command.length == 3 && command[0].contentEquals("r")) {
            this.removeUser(command[1]);
          }
          // Set central user command.
          else if (command.length == 2 && command[0].contentEquals("s")) {
            this.setCentralUser(command[1]);
          }
        }
      }
    } catch (Exception e) {

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
    // Sets the user while checking for UserNotFoundException
    this.centralUser = this.graph.getUser(username);
    // Add command to list of commands
    this.commands.add("s " + username);
  }

}
