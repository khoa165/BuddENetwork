package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public class SocialNetwork implements SocialNetworkADT {

  @Override
  public void addFriendship(String user1, String user2)
      throws IllegalNullArgumentException, DuplicateFriendshipException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeFriendship(String user1, String user2)
      throws IllegalNullArgumentException, UserNotFoundException,
      FriendshipNotFoundException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addUser(String username)
      throws IllegalNullArgumentException, DuplicateUserException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeUser(String username)
      throws IllegalNullArgumentException, UserNotFoundException {
    // TODO Auto-generated method stub
    
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
  public void loadFromFile(File filename)
      throws IllegalNullArgumentException, FileNotFoundException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void saveToFile(File filename)
      throws IllegalNullArgumentException, FileNotFoundException {
    // TODO Auto-generated method stub
    
  }

}
