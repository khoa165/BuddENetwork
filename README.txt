# BuddE Network project
Created by: Khoa Thien Le (Harry), Kenneth Mui, Shannon Stiles, Saniya Khullar.
UW-Madison, CS 400, A-Team 12.
- Khoa Thien Le (Harry), ktle4@wisc.edu, x-team 22, lecture 001.
- Shannon Stiles, sstiles@wisc.edu, x-team 129, lecture 001.
- Kenneth Mui, klmui@wisc.edu, x-team 73, lecture 002.
- Saniya Khullar, skhullar2@wisc.edu, x-team 42, lecture 001.

NOTES FOR GRADER
- If you build a social network from scratch, then you have to start by adding a user first, and use the drop down or search field to set that user as central user, from then, you can start adding/removing friends and even adding other users, checking for mutual friends. If you attempt to add/remove friends or check for mutual buddies before creating any user, you will receive a warning that you need to set central user first.
- You can load file by placing it in the project directory and enter the file name, including the .txt extension. Multiple files can be loaded and data would be added to existing social network.
- If you want to navigate from one user to another user, please use drop down as it contains the a link or search field to set central user, clicking on central user's friends would not work.

SCREENSHOTS EXPLANATION
- 0: Start the application, no action performed yet.
- 1:  Enter "CS400" and click "Create New User". Then either select "CS400" from the dropdown or enter "CS400" in Search User section and click Search icon.
- 2:  Enter "Deb" and click "Add BuddE".
- 3:  Click "Load file" and enter "data/friends.txt" (because we store data files in data folder for clean organization).
- 4:  Click "OK". The graph shows up with Saniya as central user with her friends because of the commands from friends.txt
- 5:  Enter "Kenny" and click "Mutual BuddEs".
- 6:  Either select "Peter" from the dropdown or enter "Peter"" in Search User section and click Search icon.
- 7:  Enter "Brian" in the Shortest path section and click Search icon.
- 8:  Either select "Saniya" from the dropdown or enter "Saniya"" in Search User section and click Search icon. Then enter "Deb" and click "Add BuddE". Now Saniya should have 9 friends, and graph has been restructured.
- 9:  Enter "Deb" and click "Add BuddE" again, error should pop up due to Duplicate Friendship.
- 10: Enter "Cat" and click "Remove BuddE", error should pop up due to Friendship Not Found (Cat does exist in social network, otherwise error would have been User Not Found).
- 11: Close the application, warning pops up due to file not saved.

Finished goals:
- [X] Set up the repository and project.
- [X] Get all team members to clone the repository and set up local work directories.
- [X] Define GraphADT.java interface.
- [X] Define SocialNetworkADT.java interfaces.
- [X] Implementation of Graph.java class.
- [X] Implementation of GraphTest.java class.
- [X] Implementation of SocialNetwork.java class.
- [X] Implementation of SocialNetworkTest.java class.
- [X] Link Back-end with GUI.

Upcoming goals: (personal goals for Winter break, not for the assignment)
- [ ] Easy: File uploader instead of entering file name.
- [ ] Easy: Extend the User class to have more info of users such as age, address, nationality.
- [ ] Medium: Authentication.
- [ ] Difficult: Store information in database instead of txt file.
