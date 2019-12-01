package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
        // DuplicateUserException should not be thrown
        e1.printStackTrace();
      }
    }
    try {
      userInstance2 = this.graph.getUser(user2);
    } catch (UserNotFoundException e) {
      userInstance2 = new User(user2);
      try {
        this.graph.addVertex(userInstance2);
      } catch (DuplicateUserException e1) {
        // DuplicateUserException should not be thrown
        e1.printStackTrace();
      }
    }

    // Add edge in the graph while checking for a DuplicateFriendshipException
    this.graph.addEdge(userInstance1, userInstance2);

    // Add command to list of commands
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
  @Override
  public void removeFriendship(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException,
      FriendshipNotFoundException {
    // Get user instances while checking for IllegalNullArgumentException and
    // UserNotFoundException
    User userInstance1 = this.graph.getUser(user1);
    User userInstance2 = this.graph.getUser(user2);

    // Remove the friendship while checking for FriendshipNotFoundException
    this.graph.removeEdge(userInstance1, userInstance2);

    // Add command to list of commands
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
  @Override
  public void addUser(String username) throws IllegalNullArgumentException,
      DuplicateUserException {
    // Create an instance of User from the username
    User newUser = new User(username);
    // Adds user while checking for IllegalNullArgumentException and
    // DuplicateUserException
    this.graph.addVertex(newUser);

    // Add command to list of commands
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
  @Override
  public void removeUser(String username) throws IllegalNullArgumentException,
      UserNotFoundException {
    if (username == null || username.length() == 0) {
      throw new IllegalNullArgumentException();
    }
    // Gets the instance of the user while checking for
    // IllegalNullArgumentException and UserNotFoundException
    User userToRemove = this.graph.getUser(username);
    this.graph.removeVertex(userToRemove);

    // Add command to list of commands
    this.commands.add("r " + username);
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
  @Override
  public Set<User> getFriends(String username)
      throws IllegalNullArgumentException, UserNotFoundException {
    // Gets the instance of the user while checking for
    // IllegalNullArgumentException and UserNotFoundException
    User user = this.graph.getUser(username);
    return user.getFriends();
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
  @Override
  public Set<User> getMutualFriends(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException {
    // Get user instances checking for IllegalNullArgumentException and
    // UserNotFoundException
    User userInstance1 = this.graph.getUser(user1);
    User userInstance2 = this.graph.getUser(user2);

    // Get friends of both users
    Set<User> friendsOfUser1 = userInstance1.getFriends();
    Set<User> friendsOfUser2 = userInstance2.getFriends();

    // Get mutual friends
    Set<User> mutualFriends = new HashSet<User>();
    for (User friendOf1 : friendsOfUser1) {
      for (User friendOf2 : friendsOfUser2) {
        if (friendOf1.equals(friendOf2)) {
          mutualFriends.add(friendOf1);
        }
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
  @Override
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
  @Override
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
   * @throws FileNotFoundException        if file does not exist.
   */
  @Override
  public void loadFromFile(String filename) throws IllegalNullArgumentException,
      FileNotFoundException {
    if (filename == null || filename.length() == 0) {
      throw new IllegalNullArgumentException();
    }

    Scanner scnr = new Scanner(filename);
    // Parse the file
    while (scnr.hasNextLine()) {
      // Add user and add friendship command
      String command = scnr.next();
      if (command.equals("a")) {
        User user1 = new User(scnr.next());
        if (scnr.hasNext()) {
          User user2 = new User(scnr.next());
          try {
            this.graph.addEdge(user1, user2);
          } catch (DuplicateFriendshipException e) {
            e.printStackTrace();
          }
        } else {
          try {
            this.graph.addVertex(user1);
          } catch (DuplicateUserException e) {
            e.printStackTrace();
          }
        }
      }
      // Remove user and remove friendship command
      User user1 = null;
      if (command.equals("r")) {
        try {
          user1 = this.graph.getUser(scnr.next());
        } catch (UserNotFoundException e) {
          e.printStackTrace();
        }
        if (scnr.hasNext()) {
          try {
            User user2 = this.graph.getUser(scnr.next());
            try {
              this.graph.removeEdge(user1, user2);
            } catch (FriendshipNotFoundException e) {
              e.printStackTrace();
            }
          } catch (UserNotFoundException e) {
            e.printStackTrace();
          }
        } else {
          try {
            this.graph.removeVertex(user1);
          } catch (UserNotFoundException e) {
            e.printStackTrace();
          }
        }
      }
      // Set central user command
      if (command.equals("s")) {
        try {
          User centralUser = this.graph.getUser(scnr.next());
          this.centralUser = centralUser;
        } catch (UserNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
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
   * @throws IOException if there is an error in saving changes to a file.
   */
  @Override
  public void saveToFile(String filename) throws IllegalNullArgumentException,
      IOException {
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
  @Override
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
  @Override
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
