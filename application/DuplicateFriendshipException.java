/**
 * Project: BuddE Network
 * 
 * Filename: DuplicateFriendshipException.java
 * 
 * Associated Files: Main.java, SocialNetworkADT.java, SocialNetwork.java,
 * GraphADT.java, Graph.java, GraphTest.java, User.java, SocialNetworkTest.java,
 * DuplicateUserException.java, FriendshipNotFoundException.java,
 * UserNotFoundException.java, IllegalNullArgumentException.java
 *
 * Authors: Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

/**
 * Checked exception thrown if the edge being inserted between two users already
 * exists in the graph.
 * 
 * @author Khoa Thien Le (Harry).
 */
@SuppressWarnings("serial")
public class DuplicateFriendshipException extends Exception {
}
