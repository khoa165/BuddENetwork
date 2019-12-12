/**
 * Project: BuddE Network
 * 
 * Filename: User.java
 * 
 * Associated Files: Main.java, SocialNetworkADT.java, SocialNetwork.java,
 * GraphADT.java, Graph.java, GraphTest.java, SocialNetworkTest.java,
 * DuplicateFriendshipException.java, DuplicateUserException.java,
 * FriendshipNotFoundException.java, UserNotFoundException.java,
 * IllegalNullArgumentException.java
 *
 * Authors: Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

import java.util.HashSet;
import java.util.Set;

/**
 * User class represents user in the social network. A user has name and many
 * friends.
 * 
 * @author Khoa Thien Le (Harry), Shannon Stiles.
 */
public class User {

  private String name;
  private Set<String> friendNames; // Set of friends' names.
  private Set<User> friends; // Set of friends' User objects.

  /**
   * Constructor that takes name of the user and initializes friend list.
   * 
   * @param name
   */
  public User(String name) {
    this.name = name;
    this.friends = new HashSet<User>();
    this.friendNames = new HashSet<String>();
  }

  /**
   * Getter method for name.
   * 
   * @return user's name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter method for friend list.
   * 
   * @return set of users that are friends with this user.
   */
  public Set<User> getFriends() {
    return this.friends;
  }

  /**
   * Add a new friend.
   * 
   * @param newFriend user instance of new friend.
   * @return true if adding friend successfully, false otherwise.
   */
  public boolean addFriend(User newFriend) {
    if (this.friendNames.contains(newFriend.getName())) {
      return false;
    } else {
      this.friendNames.add(newFriend.getName());
      this.friends.add(newFriend);
      return true;
    }
  }

  /**
   * Remove a friend.
   * 
   * @param friend user instance of friend to be removed.
   * @return true if removing friend successfully, false otherwise.
   */
  public boolean removeFriend(User friend) {
    if (this.friendNames.contains(friend.getName())) {
      this.friendNames.remove(friend.getName());
      this.friends.remove(friend);
      return true;
    } else {
      return false;
    }
  }
}
