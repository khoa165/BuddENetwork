package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



class SocialNetworkTest {

  public static SocialNetwork newBuddENetwork;
  public static SocialNetwork christmasBuddENetwork;
  public static SocialNetwork lonelERedNosedRudolphNetwork;
  // 11 default users (9 are reindeer)
  // User santaClaus = new User("Santa Claus");
  // User grinch = new User("Grinch");
  // User comet = new User("Comet");
  // User rudolph = new User("Rudolph");
  // User prancer = new User("Prancer");
  // User blitzen = new User("Blitzen");
  // User donder = new User("Donder");
  // User vixen = new User("Vixen");
  // User dancer = new User("Dancer");
  // User cupid = new User("Cupid");
  // User dasher = new User("Dasher");

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
   * the social network graph. Here, both buddEs are already in the social
   * network.
   */
  @Test
  void test001_addValidFriendshipFor2BuddiesInNetwork() {

    try {
      christmasBuddENetwork.addFriendship("santa", "grinch");
      christmasBuddENetwork.addFriendship("santa", "rudolph");
      christmasBuddENetwork.addFriendship("comet", "santa");
      christmasBuddENetwork.addFriendship("prancer", "grinch");
      christmasBuddENetwork.addFriendship("prancer", "comet");


      // no exception should be thrown because this is a different network:
      lonelERedNosedRudolphNetwork.addFriendship("santa", "grinch");
      // adding another friendship to the different network
      lonelERedNosedRudolphNetwork.addFriendship("blitzen", "donder");
      // the lonelERedNosedRudolphNetwork should have 4 buddEs: santa, grinch,
      // blitzen, and donder
      Set<User> lonelERudolphSet = lonelERedNosedRudolphNetwork.getAllUsers();
      int numLonelERudolphBuddEs = lonelERudolphSet.size();
      if (numLonelERudolphBuddEs != 4) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did "
            + "NOT return 4 for the # of BuddEs in the "
            + "lonelERedNosedRudolphNetwork but instead returned: "
            + numLonelERudolphBuddEs);
      }
      // we should see that blitzen and donder each just have 1 buddE in the
      // lonelERedNosedRudolphNetwork:
      lonelERedNosedRudolphNetwork.setCentralUser("blitzen");
      User centralUserBlitzen = lonelERedNosedRudolphNetwork.getCentralUser();
      Set<User> blitzensFriends = centralUserBlitzen.getFriends();
      int numFriendsOfBlitzen = blitzensFriends.size(); // please note this
                                                        // should be 1

      if (numFriendsOfBlitzen != 1) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 1 for the # of buddEs Blitzen has (Donder) and "
            + "instead returned: " + numFriendsOfBlitzen);
      }
      for (User i : blitzensFriends)

        if ((!i.getName().equals("donder"))) {
          fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
              + " Blitzens buddE list is NOT correct");

        }

      lonelERedNosedRudolphNetwork.setCentralUser("donder");
      User centralUserDonder = lonelERedNosedRudolphNetwork.getCentralUser();
      Set<User> dondersFriends = centralUserDonder.getFriends();
      int numFriendsOfDonder = dondersFriends.size(); // please note this should
                                                      // be 1

      if (numFriendsOfDonder != 1) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 1 for the # of buddEs Donder has (Blitzen) and "
            + "instead returned: " + numFriendsOfDonder);
      }

      for (User i : dondersFriends) {
        if ((!i.getName().equals("blitzen"))) {
          fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
              + " Donders buddE list is NOT correct");

        }
      }



      // ensuring we have added all 5 buddEs to the christmasBuddENetwork
      // because addFriendship should do
      // this :)
      // we should have 5 buddEs: santa, grinch, rudolph, comet, and prancer in
      // the network
      Set<User> allChristmasBuddEsSet = christmasBuddENetwork.getAllUsers();
      int numOfBuddEs = allChristmasBuddEsSet.size();
      if (numOfBuddEs != 5) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did "
            + "NOT return 5 for the # of BuddEs in the christmasBuddENetwork "
            + "but instead returned: " + numOfBuddEs);
      }

      // checking Santa's friendships:
      // Santa's friends: grinch, rudolph, and comet (3 friends)
      christmasBuddENetwork.setCentralUser("santa");
      User centralUserSanta = christmasBuddENetwork.getCentralUser();
      Set<User> santasFriends = centralUserSanta.getFriends();
      int numFriendsOfSanta = santasFriends.size(); // please note this should
                                                    // be 3

      if (numFriendsOfSanta != 3) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 3 for the # of friends Santa has (Grinch, Rudolph, "
            + "and Comet) and instead returned: " + numFriendsOfSanta);
      }

      ArrayList<String> santaBudsList = new ArrayList<String>();
      for (User i : santasFriends) {
        santaBudsList.add(i.getName());
      }

      if ((!santaBudsList.contains("grinch"))
          || (!santaBudsList.contains("comet"))
          || (!santaBudsList.contains("rudolph"))) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
            + " Santas buddE list is NOT correct");

      }

      // checking Grinch's friendships:
      // we should have 2 friends: santa and prancer
      christmasBuddENetwork.setCentralUser("grinch");
      User centralUserGrinch = christmasBuddENetwork.getCentralUser();
      Set<User> grinchsFriends = centralUserGrinch.getFriends();
      int numFriendsOfGrinch = grinchsFriends.size(); // please note this should
                                                      // be 2

      if (numFriendsOfGrinch != 2) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 2 for the # of friends Grinch has (Santa and "
            + "Prancer) and instead returned: " + numFriendsOfGrinch);
      }

      ArrayList<String> grinchBudsList = new ArrayList<String>();
      for (User i : grinchsFriends) {
        grinchBudsList.add(i.getName());
      }


      if ((!grinchBudsList.contains("santa"))
          || (!grinchBudsList.contains("prancer"))) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
            + " Grinchs buddE list is NOT correct");

      }

      // checking Comet's friendships:
      // Comet's friends: Santa and Prancer (2 buddEs)
      christmasBuddENetwork.setCentralUser("comet");
      User centralUserComet = christmasBuddENetwork.getCentralUser();
      Set<User> cometsFriends = centralUserComet.getFriends();
      int numFriendsOfComet = cometsFriends.size(); // please note this should
                                                    // be 2

      if (numFriendsOfComet != 2) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 2 for the # of buddEs reindeer Comet has (Santa and "
            + "Prancer) and instead returned: " + numFriendsOfComet);
      }

      ArrayList<String> cometBudsList = new ArrayList<String>();
      for (User i : cometsFriends) {
        cometBudsList.add(i.getName());
      }

      if ((!cometBudsList.contains("santa"))
          || (!cometBudsList.contains("prancer"))) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
            + " Comets buddE list is NOT correct");

      }

      // checking Prancer's friendships:
      // Prancer's friends: Grinch and Comet (2 buddEs)
      christmasBuddENetwork.setCentralUser("prancer");
      User centralUserPrancer = christmasBuddENetwork.getCentralUser();
      Set<User> prancersFriends = centralUserPrancer.getFriends();
      int numFriendsOfPrancer = cometsFriends.size(); // please note this should
                                                      // be 2

      if (numFriendsOfPrancer != 2) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 2 for the # of buddEs reindeer Prancer has (Grinch "
            + "and Comet) and instead returned: " + numFriendsOfPrancer);
      }

      ArrayList<String> prancerBudsList = new ArrayList<String>();
      for (User i : prancersFriends) {
        prancerBudsList.add(i.getName());
      }

      if ((!prancerBudsList.contains("grinch"))
          || (!prancerBudsList.contains("comet"))) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
            + " Prancers buddE list is NOT correct");
      }

      // checking Rudolph's friendships:
      // Rudolph's friends: Santa (1 buddE)
      christmasBuddENetwork.setCentralUser("rudolph");
      User centralUserRudolph = christmasBuddENetwork.getCentralUser();
      Set<User> rudolphsFriends = centralUserRudolph.getFriends();
      int numFriendsOfRudolph = rudolphsFriends.size(); // please note this
                                                        // should be 1

      if (numFriendsOfRudolph != 1) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Did"
            + " NOT return 1 for the # of buddEs Red-Nose reindeer Rudolph has "
            + "(Santa) and instead returned: " + numFriendsOfRudolph);
      }

      ArrayList<String> rudolphBudsList = new ArrayList<String>();
      for (User i : rudolphsFriends) {
        rudolphBudsList.add(i.getName());
      }


      if ((!rudolphBudsList.contains("santa"))) {
        fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :("
            + " Rudolphs buddE list is NOT correct");
      }

    } catch (IllegalNullArgumentException e1) {
      fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Threw "
          + "an IllegalNullArgumentException when trying to add a valid "
          + "friendship!");
    } catch (UserNotFoundException e2) {
      fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Threw "
          + "a UserNotFoundException when trying to add a valid friendship!");
    } catch (Exception e3) {
      fail("test001_addValidFriendshipFor2BuddiesInNetwork(): FAILED! :( Threw "
          + "an Exception when trying to add a valid friendship!");
    }



    // please see if Grinch is in this set


  }

  /**
   * This method tests to see if an IllegalNullArgumentException is properly
   * thrown when the addFriendship method is called and at least 1 of the
   * arguments is a null or empty string.
   */
  @Test
  void test002_addFriendshipIllegalNullArgumentException() {
    try {
      // inserted a null argument for a buddE, so an
      // IllegalNullArgumentException should be thrown
      newBuddENetwork.addFriendship("Saniya", null);
      // if we get here, we have failed this test because we did not throw an
      // IllegalNullArgument
      // Exception.
      fail("test002_addFriendshipIllegalNullArgumentException(): FAILED! :( Did"
          + " NOT throw an IllegalNullArgumentException when a null argument "
          + "was given for a buddE!");

    } catch (DuplicateFriendshipException e1) {
      fail("test002_addFriendshipIllegalNullArgumentException(): FAILED! :( "
          + "Threw a DuplicateFriendshipException instead of a "
          + "IllegalNullArgumentException() when a null argument was given for "
          + "a buddE!");

    } catch (IllegalNullArgumentException e2) {
      // passed the test for the null key argument.
      // next, we try to see if the test passes for an empty string and throws
      // the correct
      // IllegalNullArgumentException
      // trying to insert an empty string for a buddE:
      try {
        newBuddENetwork.addFriendship("", "Shannon");
        // if we get here, we have failed this test because we did not throw an
        // IllegalNullArgument
        // Exception.
        fail("test002_addFriendshipIllegalNullArgumentException(): FAILED! :( "
            + "Did NOT throw an IllegalNullArgumentException when an empty "
            + "string argument was given for a buddE!");

      } catch (DuplicateFriendshipException e2a) {
        fail("test002_addFriendshipIllegalNullArgumentException(): FAILED! :( "
            + "Threw a DuplicateFriendshipException instead of a "
            + "IllegalNullArgumentException() when an empty string argument "
            + "was given for a buddE!");

      } catch (IllegalNullArgumentException e2b) {
      } catch (Exception e2c) {
        fail("test002_addFriendshipIllegalNullArgumentException(): FAILED! :( "
            + "Threw another Exception instead of a "
            + "IllegalNullArgumentException() when an empty string argument was"
            + " given for a buddE!");

      }

    } catch (Exception e3) {
      fail("test002_addFriendshipIllegalNullArgumentException(): FAILED! :( "
          + "Threw another Exception instead of a "
          + "IllegalNullArgumentException() when a null argument was given for "
          + "a buddE!");

    }
  }

  /**
   * This method tests to see if a DuplicateFriendshipExcepton is properly
   * thrown when the addFriendship method is called on a friendship that already
   * exists
   */
  @Test
  void test003_addFriendshipDuplicateFriendshipException() {
    try {
      try {
        christmasBuddENetwork.addFriendship("santa", "grinch");
        christmasBuddENetwork.addFriendship("santa", "rudolph");
        christmasBuddENetwork.addFriendship("comet", "santa");
        christmasBuddENetwork.addFriendship("prancer", "grinch");
        christmasBuddENetwork.addFriendship("prancer", "comet");

        // no exception should be thrown because this is a different network:
        // lonelERedNosedRudolphNetwork.addFriendship("santa", "grinch");

        christmasBuddENetwork.addFriendship("santa", "rudolph"); // duplicate
                                                                 // friendship
        // if we get here, we did NOT throw any DuplicateFriendshipException()
        // and fail this method :(
        fail("test003_addFriendshipDuplicateFriendshipException(): FAILED! :( "
            + "Did NOT throw a DuplicateFriendshipException() when trying to "
            + "add a friendship between Santa and Rudolph again!");

      } catch (IllegalNullArgumentException e1) {
        fail("test003_addFriendshipDuplicateFriendshipException(): FAILED! :( "
            + "Threw an IllegalNullKeyException instead of a "
            + "DuplicateFriendshipException()!");

      } catch (DuplicateFriendshipException e2) {
      }

    } catch (Exception e3) {
      fail("test003_addFriendshipDuplicateFriendshipException(): FAILED! :( "
          + "Threw another Exception instead of a "
          + "DuplicateFriendshipException()!");

    }
  }

  /**
   * This method tests to see if a valid friendship between 2 BuddEs is added to
   * the social network graph when 1 or both buddEs are NOT in the social
   * network. It checks that we add the buddEs to the social network and then
   * add a friendship between them.
   * 
   */
  @Test
  void test004_addValidFriendshipWhereAtLeastOneBuddEIsNotInNetwork() {
    try {
      // Special case: if there is no existing user that correspond to any given
      // * name, then add the user(s) first and then add friendship between
      // them.
      christmasBuddENetwork.addUser("grinch");
      christmasBuddENetwork.addFriendship("grinch", "santa");

      Set<User> allChristmasBuddEs = christmasBuddENetwork.getAllUsers();
      ArrayList<String> allChristmasBuddEsList = new ArrayList<String>();
      for (User i : allChristmasBuddEs) {
        allChristmasBuddEsList.add(i.getName());
      }
      // please make sure that the size of the network is 2
      if ((!allChristmasBuddEsList.contains("grinch"))
          || (!allChristmasBuddEsList.contains("santa"))) {
        fail("test004_addValidFriendshipWhereAtLeastOneBuddEIsNotInNetwork(): "
            + "FAILED! :(  Did NOT add Santa to the network when trying to add "
            + "a friendship where Santa was not in the network.");
      }

      int numChristmasbuddEs = allChristmasBuddEs.size();
      if ((numChristmasbuddEs != 2)) {
        fail("test004_addValidFriendshipWhereAtLeastOneBuddEIsNotInNetwork(): "
            + "FAILED! :(  Did NOT have a size of 2 for the "
            + "christmasBuddENetwork but instead had a size of "
            + numChristmasbuddEs);
      }


      // Set<User> getAllUsers();

    } catch (IllegalNullArgumentException e1) {
      fail("test004_addValidFriendshipWhereAtLeastOneBuddEIsNotInNetwork(): "
          + "FAILED! :(  Incorrectly threw an IllegalNullArgumentException.");


      // IllegalNullArgumentException, DuplicateUserException
      // DuplicateFriendshipException
    } catch (DuplicateUserException e2) {
      fail("test004_addValidFriendshipWhereAtLeastOneBuddEIsNotInNetwork(): "
          + "FAILED! :(  Incorrectly threw a DuplicateUserException.");

    } catch (DuplicateFriendshipException e3) {
      fail("test004_addValidFriendshipWhereAtLeastOneBuddEIsNotInNetwork(): "
          + "FAILED! :(  Incorrectly threw a DuplicateFriendshipException.");

    }

  }


  // remove friendship where a username is null or empty string -->
  // IllegalNullArgumentException
  /**
   * This method tests to see if a we throw an IllegalNullArgumentException
   * whenever we try to remove a friendship where one user is null or the empty
   * string.
   * 
   */
  @Test
  void test005_removeFriendshipThrowsIllegalNullArgumentException() {
    try {
      // set should be updated.
      christmasBuddENetwork.addFriendship("santa", "grinch");
      christmasBuddENetwork.addFriendship("prancer", "comet");
      christmasBuddENetwork.addFriendship("santa", "rudolph");
      // please try to remove a friendship that doesn't exist in the network
      // with a null
      christmasBuddENetwork.removeFriendship(null, "comet");
      fail("test005_removeFriendshipThrowsIllegalNullArgumentException(): "
          + "FAILED! :( Did NOT throw an IllegalNullArgumentException when "
          + "trying to remove a null friendship.");
    } catch (IllegalNullArgumentException e) {
    } catch (Exception e) {
      fail("test005_removeFriendshipThrowsIllegalNullArgumentException(): "
          + "FAILED! :( Threw the wrong exception.");
    }
  }



  // remove friendship where a username is one of the buddEs is NOT in the
  // network -->
  // UserNotFoundException
  /**
   * This method tests to see if a we throw a UserNotFoundException whenever we
   * try to remove the friendship where at least one buddE is NOT in the
   * network.
   * 
   */
  @Test
  void test006_removeFriendshipThrowsUserNotFoundException() {
    try {
      // set should be updated.
      christmasBuddENetwork.addFriendship("santa", "grinch");
      christmasBuddENetwork.addFriendship("prancer", "comet");
      christmasBuddENetwork.addFriendship("santa", "rudolph");
      // please try to remove a friendship that doesn't exist in the network
      christmasBuddENetwork.removeFriendship("santa", "comet");
      fail("test006_removeFriendshipThrowsUserNotFoundException(): FAILED! :( "
          + "Did NOT throw a UserNotFoundException when trying to remove a "
          + "friendship that's not in the network.");
    } catch (UserNotFoundException e) {
    } catch (FriendshipNotFoundException e) {
    } catch (Exception e) {
      fail("test006_removeFriendshipThrowsUserNotFoundException(): FAILED! :( "
          + "Threw the wrong exception.");
    }

  }


  // remove friendship where a username is one of the buddEs is NOT in the
  // network -->
  // UserNotFoundException
  /**
   * This method tests to see if removeFriendship works properly
   * 
   */
  @Test
  void test007_removeValidFriendship() {
    try {
      // set should be updated.
      christmasBuddENetwork.addFriendship("santa", "grinch");
      christmasBuddENetwork.addFriendship("santa", "rudolph");
      christmasBuddENetwork.addFriendship("comet", "rudolph");
      christmasBuddENetwork.addFriendship("rudolph", "grinch");
      christmasBuddENetwork.addFriendship("prancer", "comet");


      // rudolph has 3 friends: santa, comet, and grinch

      christmasBuddENetwork.setCentralUser("rudolph");
      User centralUserRudolph = christmasBuddENetwork.getCentralUser();
      Set<User> rudolphsFriends = centralUserRudolph.getFriends();
      int numFriendsOfRudolph = rudolphsFriends.size(); // please note this
                                                        // should be 3

      if (numFriendsOfRudolph != 3) {
        fail("test007_removeValidFriendship(): FAILED! :( Did"
            + " NOT return 3 for the # of buddEs Red-Nose reindeer Rudolph has "
            + "(Santa, Comet, Grinch) and instead returned: "
            + numFriendsOfRudolph);
      }

      ArrayList<String> rudolphBudsList = new ArrayList<String>();
      for (User i : rudolphsFriends) {
        rudolphBudsList.add(i.getName());
      }


      if ((!rudolphBudsList.contains("santa"))
          || (!rudolphBudsList.contains("comet"))
          || (!rudolphBudsList.contains("grinch"))) {
        fail("test007_removeValidFriendship(): FAILED! :("
            + " Rudolphs buddE list is NOT correct");

      }

      // please remove friendship between Santa and Rudolph as the 2
      // unfortunately get into an argument :(
      christmasBuddENetwork.removeFriendship("santa", "rudolph");
      // Rudolph should just have 2 friends now: Comet and Grinch
      rudolphsFriends = centralUserRudolph.getFriends();
      numFriendsOfRudolph = rudolphsFriends.size(); // please note this should
                                                    // be 2

      if (numFriendsOfRudolph != 2) {
        fail("test007_removeValidFriendship(): FAILED! :( Did NOT return 2 for "
            + "the # of buddEs Red-Nose reindeer Rudolph has after breaking off"
            + " with Santa and instead returned: " + numFriendsOfRudolph);
      }

      ArrayList<String> rudolphBudsListNew = new ArrayList<String>();
      for (User i : rudolphsFriends) {
        rudolphBudsListNew.add(i.getName());
      }


      if ((!rudolphBudsListNew.contains("comet"))
          || (!rudolphBudsListNew.contains("grinch"))) {
        fail("test007_removeValidFriendship(): FAILED! :( Rudolphs buddE list "
            + "is NOT correct after we removed friendship with Santa");

      }



    } catch (Exception e) {
      fail("test007_removeValidFriendship(): FAILED! Threw incorrect exceptions"
          + " when removing a valid friendship");
    }

  }


  // addUser tests:



  /**
   * Please note that this test ensures that we can successfully add new users
   * to the BuddE network and that the getAllUsers increases.
   */
  @Test
  void test008_addValidUsers() {
    // adding new user (check that allUsers has increased).

    // (Adding same user name but to different networks shouldnt throw any
    // exception.

    try {
      christmasBuddENetwork.addUser("Santa Claus");
      christmasBuddENetwork.addUser("Grinch");
      christmasBuddENetwork.addUser("Comet");
      christmasBuddENetwork.addUser("Rudolph");
      christmasBuddENetwork.addUser("Prancer");
      christmasBuddENetwork.addUser("Blitzen");
      christmasBuddENetwork.addUser("Donder");
      christmasBuddENetwork.addUser("Vixen");
      christmasBuddENetwork.addUser("Dancer");
      christmasBuddENetwork.addUser("Cupid");

      Set<User> allUsersChristmasSet = christmasBuddENetwork.getAllUsers();
      int numUsersChristmas = allUsersChristmasSet.size();
      if (numUsersChristmas != 10) {
        fail("test008_addValidUsers(): FAILED!  Did NOT return 10 users for "
            + "the network, but instead: " + numUsersChristmas);
      }


    } catch (Exception e) {
      fail("test008_addValidUsers(): FAILED! Threw an improper exception! :(");

    }


  }


  /**
   * Please note that this test ensures that we throw an
   * IllegalNullArgumentException if we add a null user or empty string user
   */
  @Test
  void test009_addUsersIllegalNullArgumentException() {
    // null addUser or empty string addUser --> IllegalNullArgumentException
    try {
      christmasBuddENetwork.addUser("Santa Claus");
      christmasBuddENetwork.addUser("Grinch");
      christmasBuddENetwork.addUser("Comet");
      christmasBuddENetwork.addUser("Rudolph");
      christmasBuddENetwork.addUser("Prancer");
      christmasBuddENetwork.addUser("Blitzen");
      christmasBuddENetwork.addUser("Donder");
      christmasBuddENetwork.addUser("Vixen");
      christmasBuddENetwork.addUser("Dancer");
      christmasBuddENetwork.addUser("Cupid");
      christmasBuddENetwork.addUser(null);
      // if we get here, then we did NOT throw an illegalNullArgumentException
      // when we should have! :(
      fail("test009_addUsersIllegalNullArgumentException(): FAILED! :( Did NOT"
          + " throw an IllegalNullArgumentException when adding a null user");
    } catch (IllegalNullArgumentException e1) {
      try {
        christmasBuddENetwork.addUser("");
      } catch (IllegalNullArgumentException e1a) {
      } catch (Exception e2) {
        fail("test009_addUsersIllegalNullArgumentException(): FAILED! :( Did "
            + "NOT throw an IllegalNullArgumentException when adding an empty "
            + "string user");
      }


    } catch (Exception e2) {
      fail("test009_addUsersIllegalNullArgumentException(): FAILED! :( Threw "
          + "the wrong exception!");
    }



  }



  /**
   * Please note that this test ensures that we throw a DuplicateUserException
   * if we try to add a duplicate user to a network
   */
  @Test
  void test010_addUsersDuplicateUserException() {
    // adding duplicate user --> DuplicateUserException

  }


  /**
   * This test checks to make sure we throw an IllegalNullArgumentException when
   * trying to set the central user as an empty string or null argument.
   */
  @Test
  void test011_setCentralUserIllegalNullArgumentException() {
    try {
      christmasBuddENetwork.addUser("Santa Claus");
      christmasBuddENetwork.addUser("Grinch");
      christmasBuddENetwork.addUser("Comet");
      christmasBuddENetwork.addUser("Rudolph");
      christmasBuddENetwork.addUser("Prancer");
      christmasBuddENetwork.addUser("Blitzen");
      christmasBuddENetwork.addUser("Donder");
      christmasBuddENetwork.addUser("Vixen");
      christmasBuddENetwork.addUser("Dancer");
      christmasBuddENetwork.addUser("Cupid");
      christmasBuddENetwork.addUser("Dasher");
      christmasBuddENetwork.setCentralUser(""); // making empty string the
                                                // central user

      // if we get here, then we did not properly throw an
      // IllegalNullArgumentException on empty string,
      // so we failed tests.
      fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :(.  "
          + "Did NOT throw an IllegalNullArgumentException when finding central"
          + " user of empty string.");
    } catch (UserNotFoundException e1) {
      fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :(. "
          + "Threw a UserNotFoundException instead of an "
          + "IllegalNullArgumentException when making the central user an empty"
          + " string!");
    } catch (IllegalNullArgumentException e2) {
      // properly caught this Exception :) for empty string
      try {
        christmasBuddENetwork.setCentralUser(null); // making a null buddE the
                                                    // central user
        fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :(."
            + " Did NOT throw an IllegalNullArgumentException when calling "
            + "setCentralUser(null)!");

      } catch (UserNotFoundException e2a) {
        fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :(."
            + " Threw a UserNotFoundException instead of an "
            + "IllegalNullArgumentException when calling "
            + "setCentralUser(null)!");
      } catch (IllegalNullArgumentException e2b) {
      } catch (Exception e2c) {
        fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :(."
            + " Threw another Exception instead of an "
            + "IllegalNullArgumentException when calling "
            + "setCentralUser(null)!");
      }

    } catch (Exception e3) {
      fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :( "
          + "Threw another Exception instead of an IllegalNullArgumentException"
          + " when calling setCentralUser for an empty String!");
    }
  }

  @Test
  void test012_setCentralUserUserNotFoundException() {
    // try adding to different networks and then ensure that the networks that
    // have that person throw no
    // exception.
    try {
      christmasBuddENetwork.addUser("Santa Claus");
      christmasBuddENetwork.addUser("Grinch");
      christmasBuddENetwork.addUser("Comet");

      christmasBuddENetwork.setCentralUser("Rudolph"); // making Rudolph a
                                                       // central user, but he
                                                       // is NOT in the network
                                                       // :(

      // if we get here, then we did not properly throw a UserNotFoundException
      // on a user not in the
      // network,
      // so we failed tests.
      fail("test012_setCentralUserUserNotFoundException(): FAILED :(.  "
          + "Did NOT throw an UserNotFoundException when setting central"
          + " user who is NOT in the christmasBuddENetwork network.");
    } catch (IllegalNullArgumentException e1) {
      fail("test012_setCentralUserUserNotFoundException(): FAILED :(. Threw a "
          + "IllegalNullArgumentException instead of an UserNotFoundException "
          + "when making the central user someone not in the network!");
    } catch (UserNotFoundException e2) {
    } catch (Exception e3) {
      fail("test012_setCentralUserUserNotFoundException(): FAILED :( Threw "
          + "another Exception instead of a UserNotFoundException when calling "
          + "setCentralUser for Rudolph who wasn't in the network!");
    }
  }


  /**
   * 
   */
  @Test
  void test013_setValidCentralUser() {
    try {
      christmasBuddENetwork.addUser("Santa Claus");
      christmasBuddENetwork.addUser("Grinch");
      christmasBuddENetwork.addUser("Comet");
      christmasBuddENetwork.addUser("Rudolph");
      christmasBuddENetwork.addUser("Prancer");
      christmasBuddENetwork.addUser("Blitzen");
      christmasBuddENetwork.addUser("Donder");
      christmasBuddENetwork.addUser("Vixen");
      christmasBuddENetwork.addUser("Dancer");
      christmasBuddENetwork.addUser("Cupid");
      christmasBuddENetwork.addUser("Dasher");
      christmasBuddENetwork.setCentralUser("Santa Claus"); // making Santa the
                                                           // Central User

    } catch (UserNotFoundException e1) {
      fail("test013_setValidCentralUser(): FAILED :(.  Threw a "
          + "UserNotFoundException when making a valid buddE (Santa Claus) "
          + "a central user!");
    } catch (IllegalNullArgumentException e2) {
      fail("test013_setValidCentralUser(): FAILED :(.  "
          + "Threw a IllegalNullArgumentException when making a valid buddE "
          + "(Santa Claus) a central user!");

    } catch (Exception e3) {
      fail("test011_setCentralUserIllegalNullArgumentException(): FAILED :(  "
          + "Threw another Exception instead of an IllegalNullArgumentException"
          + " when calling setCentralUser for an empty String!");
    }
  }

  /**
   * This test checks the getConnectedComponents and ensures we have 2 connected
   * components here.
   * 
   * @return set of graphs of connected of components.
   */
  @Test
  void test014_testGetConnectedComponents() {
    try {
      christmasBuddENetwork.addFriendship("HarrE", "Prancer");

      christmasBuddENetwork.addFriendship("Santa", "Rudolph");
      christmasBuddENetwork.addFriendship("Comet", "Santa");
      christmasBuddENetwork.addFriendship("Rudolph", "Comet");
      christmasBuddENetwork.addFriendship("Grinch", "Comet");

      // There should be 2 connected components.
      Set<Graph> christmasComponents =
          christmasBuddENetwork.getConnectedComponents();
      int numComponents = christmasComponents.size();
      //
      if (numComponents != 2) {
        fail("");
      }
      Iterator<Graph> christmasGraphIterator = christmasComponents.iterator();

      boolean firstCompFound = false;
      boolean secondCompFound = false;
      int componentsFound = 0;
      while (christmasGraphIterator.hasNext()) {
        Graph christmasGraph = christmasGraphIterator.next();
        componentsFound = componentsFound + 1;
        //
        if (christmasGraph.size() == 1) {
          if (firstCompFound == true) {
            fail("test014_testGetConnectedComponents(): FAILED! Duplicate "
                + "component (size 1) found! :(");
          }
          Set<User> oneConnectedComponentVertices =
              christmasGraph.getAllVertices();
          ArrayList<String> componentUsersList = new ArrayList<String>();
          for (User userName : oneConnectedComponentVertices) {
            componentUsersList.add(userName.getName());
          }
          if ((!componentUsersList.contains("Harry"))
              || (!componentUsersList.contains("Prancer"))) {
            fail("test014_testGetConnectedComponents(): Failed! :( Did NOT "
                + "have Harry and Prancer");
          } else {
            firstCompFound = true;

          }
        }

        if (christmasGraph.size() == 4) {
          if (secondCompFound == true) {
            fail("test014_testGetConnectedComponents: FAILED! Duplicate "
                + "component (size 4) found! :(");
          }
          Set<User> secondConnectedComponentVertices =
              christmasGraph.getAllVertices();
          ArrayList<String> componentUsersList2 = new ArrayList<String>();
          for (User userName : secondConnectedComponentVertices) {
            componentUsersList2.add(userName.getName());
          }
          if ((!componentUsersList2.contains("santa"))
              || (!componentUsersList2.contains("rudolph"))
              || (!componentUsersList2.contains("comet"))
              || (!componentUsersList2.contains("grinch"))) {
            fail("test014_testGetConnectedComponents(): FAILED! Did NOT have "
                + "Santa, Rudolph, Comet, and Grinch!");
          } else {
            secondCompFound = true;
          }
        }
      }

      if (componentsFound != 2) {
        fail("test014_testGetConnectedComponents(): Did NOT return 2 for the "
            + "connected components but instead returned: " + componentsFound);
      }
    } catch (Exception e) {
      fail("test014_testGetConnectedComponents(): Failed! :(. Threw unexpected "
          + "exception");
    }

  }


  /**
   * Please note that this test checks to see if the shortest path method works:
   * Here, we have the network where Santa --> Comet --> Grinch --> Donder We
   * want to see the shortest path from Santa to Donder. It should be: [Comet,
   * Grinch]
   */
  @Test
  void test015_testGetShortestPath() {
    try {
      christmasBuddENetwork.addFriendship("HarrE", "Prancer");

      christmasBuddENetwork.addFriendship("Santa", "Rudolph");
      christmasBuddENetwork.addFriendship("Comet", "Santa");
      christmasBuddENetwork.addFriendship("Rudolph", "Comet");

      christmasBuddENetwork.addFriendship("Grinch", "Comet");
      christmasBuddENetwork.addFriendship("Grinch", "Donder");

      List<User> shortestPathFromSantaToDonder =
          christmasBuddENetwork.getShortestPath("Santa", "Donder");
      ArrayList<String> shortestPathFromSantaToGrinchList =
          new ArrayList<String>();
      for (int i = 0; i < shortestPathFromSantaToDonder.size(); i++) {
        User currentUser = shortestPathFromSantaToDonder.get(i);
        String userName = currentUser.getName();
        shortestPathFromSantaToGrinchList.add(userName);
      }
      int numElementsShortestPath = shortestPathFromSantaToDonder.size();
      if (numElementsShortestPath != 2) {
        fail("test015_testGetShortestPath: FAILED! :(. Did NOT return 2 "
            + "elements for the shortest path, but instead: "
            + numElementsShortestPath);
      }
      if ((!shortestPathFromSantaToDonder.get(0).equals("Comet"))
          || (!shortestPathFromSantaToDonder.get(1).equals("Grinch"))) {
        fail("test015_testGetShortestPath: FAILED! :( Did NOT return the proper"
            + " BuddEs on the way!");
      }
    } catch (Exception e) {
      fail("test015_testGetShortestPath:  FAILED! :( Threw an incorrect "
          + "exception!");
    }
  }


  // tests of mutual friendships

}


