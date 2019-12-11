/**
 * Project:    BuddE Network
 * Filename:   FriendshipNotFoundException.java
 * Associated Files: Main.java, SocialNetworkADT.java, SocialNetwork.java, 
 * 					 GraphADT.java, Graph.java, GraphTest.java, User.java,
 * 		             SocialNetworkTest.java, DuplicateFriendshipException.java,
 * 					 DuplicateUserException.java, UserNotFoundException.java,
 * 					 IllegalNullArgumentException.java
 *
 * Authors:    Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

/**
 * Checked exception thrown if the edge/friendship being removed is not in the
 * graph.
 */
@SuppressWarnings("serial")
public class FriendshipNotFoundException extends Exception {
}
