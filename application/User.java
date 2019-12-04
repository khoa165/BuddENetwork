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
}
