/**
 * Project:    BuddE Network
 * Filename:   User.java
 * Associated Files: Main.java, SocialNetworkADT.java, SocialNetwork.java, GraphADT.java,
 * 					 Graph.java, GraphTest.java, SocialNetworkTest.java, 
 * 					 DuplicateFriendshipException.java, DuplicateUserException.java,
 * 					 FriendshipNotFoundException.java, UserNotFoundException.java,
 * 					 IllegalNullArgumentException.java
 *
 * Authors:    Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

import java.util.HashSet;
import java.util.Set;

public class User {

  private String name;
  private Set<String> friendNames;
  private Set<User> friends;

  public User(String name) {
    this.name = name;
    this.friends = new HashSet<User>();
    this.friendNames = new HashSet<String>();
  }

  public String getName() {
    return this.name;
  }

  public Set<User> getFriends() {
    return this.friends;
  }

  public boolean addFriend(User newFriend) {
    if (this.friendNames.contains(newFriend.getName())) {
      return false;
    } else {
      this.friendNames.add(newFriend.getName());
      this.friends.add(newFriend);
      return true;
    }
  }
  
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
