package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SocialNetworkTest {

  public static SocialNetwork newBuddENetwork;
  public static SocialNetwork christmasBuddENetwork;
  public static SocialNetwork lonelERedNosedRudolphNetwork; 
  // 11 default users (9 are reindeer)
  User santaClaus = new User("Santa Claus");
  User grinch = new User("Grinch");
  User comet = new User("Comet");
  User rudolph = new User("Rudolph");
  User prancer = new User("Prancer");
  User blitzen = new User("Blitzen");
  User donder = new User("Donder");
  User vixen = new User("Vixen");
  User dancer = new User("Dancer");
  User cupid = new User("Cupid");
  User dasher = new User("Dasher");
  
  @BeforeEach
  void setUp() throws Exception {
    christmasBuddENetwork = new SocialNetwork();
    newBuddENetwork = new SocialNetwork();
    lonelERedNosedRudolphNetwork = new SocialNetwork();
  }

  @AfterEach
  void tearDown() throws Exception {
    christmasBuddENetwork = null; 
    newBuddENetwork = null;
    lonelERedNosedRudolphNetwork = null;
  }

  /**
   * This method tests to see if a valid friendship between 2 BuddEs is added to
   * the social network graph. 
   */
  @Test
  void test001_addValidFriendship() {
     
  }
  
  /**
   * This method tests to see if an IllegalNullArgumentException is properly 
   * thrown when the addFriendship method is called and at least 1 of the arguments is a null or empty string.  
   */
  @Test
  void test002_addFriendshipIllegalNullKeyException() {
     
  }
  
  /**
   * This method tests to see if an IllegalNullArgumentException is properly 
   * thrown when the addFriendship method is called.  
   */
  @Test
  void test003_addFriendshipDuplicateFriendshipException() {
     try {
       
       
     } catch (IllegalNullKeyException e1) {
       fail("test003_addFriendshipDuplicateFriendshipException(): FAILED! :( Threw an IllegalNullKeyException instead of a DuplicateFriendshipException()!");
       
     } catch (DuplicateFriendshipException e2) {
       System.out.println(":)");
     } catch (Exception e3) {
       fail("test003_addFriendshipDuplicateFriendshipException(): FAILED! :( Threw another Exception instead of a DuplicateFriendshipException()!");

     }
     
     
     
  }

}

