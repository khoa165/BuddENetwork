/**
 * Project: BuddE Network
 * 
 * Filename: GraphTest.java
 * 
 * Associated Files: Main.java, SocialNetworkADT.java, SocialNetwork.java,
 * GraphADT.java, Graph.java, User.java, SocialNetworkTest.java,
 * DuplicateFriendshipException.java, DuplicateUserException.java,
 * FriendshipNotFoundException.java, UserNotFoundException.java,
 * IllegalNullArgumentException.java
 *
 * Authors: Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the functionalities of Graph data structure.
 * 
 * @author Shannon Stiles.
 */
class GraphTest {

  // Empty graph that can be used by tests
  static Graph graph;
  // Users that can be used by tests
  static User harry;
  static User kenny;
  static User saniya;
  static User shannon;

  /**
   * Constructs a graph and users before each test method that can be used in
   * tests
   * 
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    graph = new Graph();
    harry = new User("Harry");
    kenny = new User("Kenny");
    saniya = new User("Saniya");
    shannon = new User("Shannon");
  }

  /**
   * Tears down graph and users after each test method
   * 
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    graph = null;
    harry = null;
    kenny = null;
    saniya = null;
    shannon = null;
  }

  /**
   * This method tests to see if a valid vertex is added to the graph
   */
  @Test
  void test001_AddValidVertex() {
    try {
      graph.addVertex(harry);
      Set<User> vertices = graph.getAllVertices();
      // one vertex was added to graph, so order should be one
      assertEquals(graph.order(), 1);
      if (!vertices.contains(harry)) {
        fail("The graph should contain vertex harry, but it does not.");
      }
    } catch (DuplicateUserException e) {
      fail("The graph already contains the vertex harry - cannot add.");
    } catch (IllegalNullArgumentException e) {
      fail("Cannot add a vertex for this user; the user is null.");
    }
  }

  /**
   * This method tests to see if a null vertex is not added to the graph
   */
  @Test
  void test002_AddNullVertex() {
    try {
      graph.addVertex(null);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown");
    } catch (IllegalNullArgumentException e) {
      // IllegalNullArgumentException should be caught here, since User is
      // null
    } finally {
      // no vertex should have been added to graph, so order should be
      // zero
      assertEquals(graph.order(), 0);
    }
  }

  /**
   * This method tests to see if a DuplicateUserException is thrown when vertex
   * already in graph is attempted to be added
   */
  @Test
  void test003_AddDuplicateVertex() {
    try {
      graph.addVertex(harry);
      graph.addVertex(harry);
    } catch (DuplicateUserException e) {
      // DuplicateUserException should be caught here, since duplicate
      // user was
      // added
    } catch (IllegalNullArgumentException e) {
      fail("Cannot add a vertex for this user; the user is null.");
    } finally {
      // duplicate vertex should not have been added, so graph order
      // should be 1
      assertEquals(graph.order(), 1);
    }
  }

  /**
   * This method tests to see if a vertex is removed from graph after it has
   * been added
   */
  @Test
  void test004_RemoveVertex_in_graph() {
    try {
      graph.addVertex(harry);
      graph.removeVertex(harry);
      Set<User> vertices = graph.getAllVertices();
      if (vertices.contains(harry)) {
        fail("The graph should not contain vertex harry, but it does.");
      }
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown");
    } catch (IllegalNullArgumentException e) {
      fail("Cannot add a vertex for this user; the user is null.");
    } catch (UserNotFoundException e) {
      fail("The User harry is not already in the graph, so cannot remove"
          + " vertex.");
    } finally {
      // Vertex harry in graph should have been removed, so graph order
      // should
      // be 0
      assertEquals(graph.order(), 0);
    }
  }

  /**
   * This test checks to see if there is no change to the graph when a vertex
   * that is not in the graph is attempted to be removed.
   */
  @Test
  void test005_RemoveVertex_not_in_graph() {
    try {
      graph.addVertex(harry);
      graph.removeVertex(kenny);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown");
    } catch (IllegalNullArgumentException e) {
      fail("Cannot add a vertex for this user; the user is null.");
    } catch (UserNotFoundException e) {
      // UserNotFoundException should be caught here, since kenny is not
      // already
      // in graph
    } finally {
      assertEquals(graph.order(), 1);
    }
  }

  /**
   * This test checks to see if an IllegalNullArgument Exception is thrown if
   * the vertex to be removed is null
   */
  @Test
  void test006_RemoveNullVertex_from_graph() {
    try {
      graph.addVertex(harry);
      graph.removeVertex(null);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown");
    } catch (IllegalNullArgumentException e) {
      // IllegalNullArgumentException should be caught here, since User is
      // null
    } catch (UserNotFoundException e) {
      fail("UserNotFoundException thrown.");
    } finally {
      assertEquals(graph.order(), 1);
    }
  }

  /**
   * This test checks to see if the edge has been added to the graph by checking
   * that the size of the graph has increased by one.
   */
  @Test
  void test007_AddValidEdge() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, kenny);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentExceptoin thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown, but this should not be a "
          + "duplicate friendship.");
    } finally {
      assertEquals(graph.size(), 1);
    }
  }

  /**
   * This test checks to see that a duplicate edge throws a
   * DuplicateFriendshipException and does not add an edge to the graph, i.e.
   * increment the size of the graph.
   */
  @Test
  void test008_AddDuplicateEdge() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, kenny);
      assertEquals(graph.size(), 1);
      graph.addEdge(harry, kenny);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      // DuplicateFriendshipException should be caught here, since harry
      // and
      // kenny are friends
    } finally {
      assertEquals(graph.size(), 1);
    }
  }

  /**
   * This test checks to see that an edge is not added to the graph when one of
   * the arguments is null by checking that an IllegalNullArgumentException is
   * thrown and that the size of the graph is not incremented by one.
   */
  @Test
  void test009_AddNullEdge() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, null);
      fail("IllegalArgumentException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      // IllegalNullArgumentException should be caught here, since one
      // vertex of edge is null
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } finally {
      assertEquals(graph.size(), 0);
    }
  }

  /**
   * This test checks to see if the edge between a vertex in the graph and one
   * not in graph has been added to the graph by checking that the new vertex
   * has incremented the order of the graph by one and the new edge has
   * incremented the size of the graph by one.
   */
  @Test
  void test010_AddEdgeWithVertexNotInGraph() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      assertEquals(graph.order(), 2);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, saniya);
      assertEquals(graph.order(), 3);
      assertEquals(graph.size(), 1);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown, but this should not be a "
          + "duplicate friendship.");
    }
  }

  /**
   * This test checks that an edge in the graph has been removed from the graph
   * by checking that the size of the graph decreases by one.
   *
   */
  @Test
  void test011_RemoveValidEdge_in_graph() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, kenny);
      assertEquals(graph.size(), 1);
      graph.removeEdge(harry, kenny);
      assertEquals(graph.size(), 0);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (FriendshipNotFoundException e) {
      fail("FriendshipNotFoundException thrown.");
    } catch (UserNotFoundException e) {
      fail("UserNotFoundException thrown.");
    }
  }

  /**
   * This test checks that an IllegalArgumentException is thrown and the size of
   * the graph does not change when an edge that contains a null vertex is
   * attempted to be removed from the graph.
   */
  @Test
  void test012_RemoveNullEdge_from_graph() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, kenny);
      assertEquals(graph.size(), 1);
      graph.removeEdge(harry, null);
      fail("IllegalArgumentException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      // IllegalNullArgumentException should be caught here, since one
      // vertex in
      // edge is null
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (FriendshipNotFoundException e) {
      fail("FriendshipNotFoundException thrown.");
    } catch (UserNotFoundException e) {
      fail("UserNotFoundException thrown.");
    } finally {
      assertEquals(graph.size(), 1);
    }
  }

  /**
   * The method tests that the size of the graph does not change when an edge
   * that is not in the graph is attempted to be removed from the graph.
   */
  @Test
  void test013_RemoveEdge_not_in_graph() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, kenny);
      assertEquals(graph.size(), 1);
      graph.removeEdge(harry, saniya);
      fail("FriendshipNotFoundException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (FriendshipNotFoundException e) {
      // FriendshipNotFoundException should be caught here, since edge
      // does not
      // exist in graph
    } catch (UserNotFoundException e) {
      fail("UserNotFoundException thrown.");
    } finally {
      assertEquals(graph.size(), 1);
    }
  }

  /**
   * The method tests that a UserNotFoundException is thrown and the size and
   * order of the graph does not change when an edge that is not in graph that
   * has a user that is not in the graph is attempted to be removed from the
   * graph.
   */
  @Test
  void test014_RemoveEdgeWithUser_not_in_graph() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      assertEquals(graph.order(), 3);
      assertEquals(graph.size(), 0);
      graph.addEdge(harry, kenny);
      assertEquals(graph.size(), 1);
      graph.removeEdge(harry, shannon);
      fail("UserNotFoundException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (FriendshipNotFoundException e) {
      fail("FriendshipNotFoundException thrown.");
    } catch (UserNotFoundException e) {
      // UserNotFoundException should be caught here, since one user does
      // not
      // exist in graph
    } finally {
      assertEquals(graph.order(), 3);
      assertEquals(graph.size(), 1);
    }
  }

  /**
   * This method tests that all vertices in the graph are returned from the
   * getAllVertices method.
   */
  @Test
  void test015_GetAllVertices() {
    Set<User> allCorrect = new HashSet<User>();
    allCorrect.add(harry);
    allCorrect.add(kenny);
    allCorrect.add(saniya);
    allCorrect.add(shannon);
    Set<User> allVertices = null;

    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addVertex(shannon);
      allVertices = graph.getAllVertices();
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } finally {
      if (!allCorrect.equals(allVertices)) {
        fail("Not all vertices in the graph are returned from method. "
            + "Correct set is " + allCorrect + ", but returned " + allVertices);
      }
    }

  }

  /**
   * This method tests that all of the correct neighboring vertices are returned
   * for a specific vertex in the graph.
   */
  @Test
  void test016_GetNeighbors_of_vertex_in_graph() {
    Set<User> correctAdj = new HashSet<User>();
    correctAdj.add(kenny);
    correctAdj.add(saniya);
    Set<User> allAdj = new HashSet<User>();

    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addEdge(harry, kenny);
      graph.addEdge(harry, saniya);
      allAdj = graph.getNeighbors(harry);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (UserNotFoundException e) {
      fail("UserNotFoundException thrown.");
    } finally {
      if (!correctAdj.equals(allAdj)) {
        fail("Correct adjacent vertices list for the grpah is not returned from"
            + " method. Correct list is " + correctAdj + ", but returned "
            + allAdj);
      }
    }
  }

  /**
   * This method tests that an IllegalNullArgumentException is thrown when the
   * getNeighbors method attempts to get neighbors of a null vertex.
   */
  @Test
  void test017_GetNeighbors_of_null_vertex_graph() {
    Set<User> correctAdj = new HashSet<User>();
    Set<User> allAdj = new HashSet<User>();
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addEdge(harry, kenny);
      graph.addEdge(harry, saniya);
      allAdj = graph.getNeighbors(null);
      fail("IllegalNullArgumentException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      // IllegalNullArgumentException should be caught here, since vertex
      // is
      // null
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (UserNotFoundException e) {
      fail("UserNotFoundExceptoin thrown.");
    } finally {
      if (!correctAdj.equals(allAdj)) {
        fail("Correct adjacent vertices list for the grpah is not returned from"
            + " method. Correct list is " + correctAdj + ", but returned "
            + allAdj);
      }
    }
  }

  /**
   * This method tests that all of the correct neighboring vertices are returned
   * for a specific vertex in the graph.
   */
  @Test
  void test018_GetNeighbors_of_vertex_not_in_graph() {
    Set<User> correctAdj = new HashSet<User>();
    Set<User> allAdj = new HashSet<User>();
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addEdge(harry, kenny);
      graph.addEdge(harry, saniya);
      allAdj = graph.getNeighbors(shannon);
      fail("UserNotFoundException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (UserNotFoundException e) {
      // UserNotFoundException should be caught here, since shannon is not
      // user
      // in graph
    } finally {
      if (!correctAdj.equals(allAdj)) {
        fail("Correct adjacent vertices list for the grpah is not returned from"
            + " method. Correct list is " + correctAdj + ", but returned "
            + allAdj);
      }
    }
  }

  /**
   * This method tests that the correct User is returned for a a given username
   * when the user is in the graph.
   */
  @Test
  void test019_get_User_in_graph() {
    User correctUser = harry;
    User user = null;

    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addVertex(shannon);
      user = graph.getUser("Harry");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } finally {
      assertEquals(graph.order(), 4);
      assertEquals(graph.size(), 0);
      assertEquals(user, correctUser);
    }
  }

  /**
   * This method tests that null is returned for a a given username when it does
   * not correspond to a user in the graph.
   */
  @Test
  void test020_get_User_Not_in_graph() {
    User correctUser = null;
    User user = new User("");

    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      user = graph.getUser("Shannon");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } finally {
      assertEquals(graph.order(), 3);
      assertEquals(graph.size(), 0);
      assertEquals(user, correctUser);
    }
  }

  /**
   * This method tests that an IllegalArgumentException is thrown when a null
   * username is passed into getUser() method.
   */
  @Test
  void test021_get_Null_User_in_graph() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.getUser(null);
      fail("IllegalNullArgumentException should have been thrown.");
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      // IllegalNullArgumentException should be caught here
    } finally {
      assertEquals(graph.order(), 3);
      assertEquals(graph.size(), 0);
    }
  }

  /**
   * This method tests that all of the Usernames/vertices are returned for the
   * graph.
   */
  @Test
  void test022_get_All_UserNames_in_graph_for_graph_with_neighbors() {
    Set<String> usernames = new HashSet<String>();
    Set<String> correctUsernames = new HashSet<String>();
    correctUsernames.add("Harry");
    correctUsernames.add("Kenny");
    correctUsernames.add("Saniya");
    correctUsernames.add("Shannon");
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addVertex(shannon);
      graph.addEdge(harry, kenny);
      graph.addEdge(harry, saniya);
      graph.addEdge(harry, shannon);
      usernames = graph.getAllUsernames();
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } finally {
      assertEquals(graph.order(), 4);
      assertEquals(graph.size(), 3);
      assertEquals(usernames, correctUsernames);

    }
  }

  /**
   * This method tests that an empty set is returned for a graph with no users
   */
  @Test
  void test023_get_empty_usernames_list_for_graph__with_no_users() {
    Set<String> usernames = new HashSet<String>();
    Set<String> correctUsernames = new HashSet<String>();

    usernames = graph.getAllUsernames();
    assertEquals(graph.order(), 0);
    assertEquals(graph.size(), 0);
    assertEquals(usernames, correctUsernames);
  }

  /**
   * This method tests that order continues to return correct number of user
   * vertices for a large amount of vertex inputs.
   */
  @Test
  void test024_check_Order_For_Large_Number_User_Inserts() {
    try {
      for (int i = 0; i < 100; i++) {
        User newUser = new User(Integer.toString(i));
        graph.addVertex(newUser);
      }
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentExceptoin thrown.");
    } finally {
      assertEquals(graph.order(), 100);
    }
  }

  /**
   * This method tests that size continues to return correct number of edges for
   * a large amount of edge inputs.
   */
  @Test
  void test025_check_Size_For_Large_Number_Edge_Inserts() {
    try {
      for (int i = 0; i < 100; i++) {
        User user1 = new User(Integer.toString(i));
        if (i == 0) {
          graph.addVertex(user1);
        }
        User user2 = new User(Integer.toString(i + 1));
        graph.addVertex(user2);
        graph.addEdge(user1, user2);
      }
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentExceptoin thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown, but this should not be a "
          + "duplicate friendship.");
    } finally {
      assertEquals(graph.size(), 100);
    }
  }

  /**
   * This method tests that the order of the graph is correct after removing an
   * user from the graph and adding the same user back to the graph.
   */
  @Test
  void test026_check_Order_after_removing_User_and_Inserting_again() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      if (graph.order() != 2) {
        fail("Order of the graph should be 2, but it is " + graph.order());
      }
      graph.removeVertex(harry);
      if (graph.order() != 1) {
        fail("Order of the graph should be 1, but it is " + graph.order());
      }
      graph.addVertex(harry);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown");
    } catch (IllegalNullArgumentException e) {
      fail("Cannot add a vertex for this user; the user is null.");
    } catch (UserNotFoundException e) {
      fail("The User harry is not already in the graph, so cannot remove "
          + "vertex.");
    } finally {
      assertEquals(graph.order(), 2);
    }
  }

  /**
   * This method tests that the size of the graph is correct after removing an
   * edge from the graph and adding the same edge back to the graph.
   */
  @Test
  void test027_check_Size_after_removing_Edge_and_Inserting_again() {
    try {
      graph.addVertex(harry);
      graph.addVertex(kenny);
      graph.addVertex(saniya);
      graph.addEdge(harry, kenny);
      graph.addEdge(harry, saniya);
      if (graph.size() != 2) {
        fail("Size is incorrect. Should have been 2, but was " + graph.size());
      }
      graph.removeEdge(harry, saniya);
      if (graph.size() != 1) {
        fail("Size is incorrect. Should have been 1, but was " + graph.size());
      }
      graph.addEdge(harry, saniya);
    } catch (DuplicateUserException e) {
      fail("DuplicateUserException thrown.");
    } catch (IllegalNullArgumentException e) {
      fail("IllegalNullArgumentException thrown.");
    } catch (DuplicateFriendshipException e) {
      fail("DuplicateFriendshipException thrown.");
    } catch (FriendshipNotFoundException e) {
      fail("FriendshipNotFoundException thrown.");
    } catch (UserNotFoundException e) {
      fail("UserNotFoundException thrown.");
    } finally {
      assertEquals(graph.size(), 2);
    }
  }

}
