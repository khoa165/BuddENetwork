/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Shannon Stiles
 *
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
		} catch (DuplicateUserException e){
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
		} catch (DuplicateUserException e){
			fail("DuplicateUserException thrown");
		} catch (IllegalNullArgumentException e) {
			// IllegalNullArgumentException should be caught here, since User is null
		} finally {
			// no vertex should have been added to graph, so order should be zero
			assertEquals(graph.order(), 0);
		}
	}
	
	/**
	 * This method tests to see if a DuplicateUserException is thrown when vertex already in graph
	 * is attempted to be added
	 */
	@Test
	void test003_AddDuplicateVertex() {
		try {
			graph.addVertex(harry);
			graph.addVertex(harry);
		} catch (DuplicateUserException e){
			// DuplicateUserException should be caught here, since duplicate user was added
		} catch (IllegalNullArgumentException e) {
			fail("Cannot add a vertex for this user; the user is null.");
		} finally {
			// duplicate vertex should not have been added, so graph order should be 1
			assertEquals(graph.order(), 1);
		}
	}
	

}
