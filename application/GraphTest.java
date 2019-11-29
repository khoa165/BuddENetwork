/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

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
	
	static Graph graph;
	static User harry;
	static User kenny;
	static User saniya;
	static User shannon;

	/**
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

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
