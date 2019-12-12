/**
 * Project: BuddE Network
 * 
 * Filename: Main.java
 * 
 * Associated Files: SocialNetworkADT.java, SocialNetwork.java, GraphADT.java,
 * Graph.java, GraphTest.java, User.java, SocialNetworkTest.java,
 * DuplicateFriendshipException.java, DuplicateUserException.java,
 * FriendshipNotFoundException.java, UserNotFoundException.java,
 * IllegalNullArgumentException.java
 *
 * Authors: Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
 * 
 * Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
 */
package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main class represents the implementation of front-end (GUI) of the social
 * network using JavaFX that allows users to interact with the back-end data
 * structures.
 * 
 * @author Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar.
 */
public class Main extends Application {
  // Social network instance.
  private static SocialNetwork buddENetwork = new SocialNetwork();
  // Filename for load/save social network.
  private static String currentFilename = null;
  // Drop-down on navbar.
  private static VBox allUsersDropdownVBox = new VBox();
  // Label for central user statistics.
  private static Label centralUserStat = new Label();
  // Label for social network statistics.
  private static Label socialNetworkStat = new Label();
  // Keep track of social network is changed and unsaved.
  private static boolean socialNetworkChangedAndUnsaved = false;

  private static HBox topSection = new HBox(); // Top view.
  private static HBox bottomSection = new HBox(); // Bottom view.
  private static VBox centerSection = new VBox(); // Center view.
  private static VBox rightSection = new VBox(); // Right view.

  private static final double WINDOW_WIDTH = 1400; // Application width.
  private static final double WINDOW_HEIGHT = 750; // Application height.
  private static final double CANVAS_WIDTH = 1000; // Canvas width.
  private static final double CANVAS_HEIGHT = 550; // Canvas height.
  private static final double RADIUS = 40; // Radius of user circle.
  // Distance between central user and friends in central user graph.
  private static final double DISTANCE = 200;
  private static final String APP_TITLE = "BuddE Network"; // Application title.

  /**
   * Method to start the application.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Main layout is Border Pane example (top, left, center, right, bottom).
    BorderPane root = new BorderPane();

    // ---------------------- Top Pane ----------------------------------------
    setupTopView();
    root.setTop(topSection);

    // ---------------------- Bottom Pane -------------------------------------
    setupBottomView();
    root.setBottom(bottomSection);

    // ---------------------- Center pane -------------------------------------
    setupCenterView();
    root.setCenter(centerSection);

    // ---------------------- Right pane --------------------------------------
    setupRightView();
    root.setRight(rightSection);


    // Create scene.
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add icon and set the primary stage.
    primaryStage.setTitle(APP_TITLE);
    primaryStage.getIcons().add(new Image("images/buddENetworkIcon.png"));
    primaryStage.setScene(mainScene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> confirmWhenClose(primaryStage, e));
  }

  /**
   * Set up the top view, mainly with a navbar that includes button for
   * new/load/save social network.
   */
  private static void setupTopView() {
    // Create social network/application logo.
    ImageView logoView = setupLogo();

    // Create tool bar of functions.
    ToolBar toolBar = new ToolBar();
    // Add navbar items to tool bar.
    toolBar.getItems().addAll(setupNavbar());
    toolBar.setStyle(
        "-fx-background-color: linear-gradient(to right, #61045F, #AA076B)");

    // Add logo and navbar to topSection.
    topSection.getChildren().addAll(logoView, toolBar);
    topSection.setSpacing(10);
    topSection.setStyle("-fx-background-color: linear-gradient(to right, "
        + "#61045F, #AA076B, #AA076B); -fx-border-color: black; "
        + "-fx-border-width: 0 0 3 0;");
  }

  /**
   * Set up bottom view, mainly to display and update status of central user and
   * social network.
   */
  private static void setupBottomView() {
    // Status label that indicates statistics of social network.
    socialNetworkStat = new Label("BuddE Network stats: 0 users --- 0 "
        + "friendships --- 0 connected groups/components.");
    socialNetworkStat.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
    socialNetworkStat.setTextFill(Color.RED);

    // Status label that indicates statistics of central user.
    centralUserStat =
        new Label("Central user is not set yet. No stat available.");
    centralUserStat.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
    centralUserStat.setTextFill(Color.BLUE);

    // Add status labels to bottom section.
    VBox stats = new VBox();
    stats.getChildren().addAll(centralUserStat, socialNetworkStat);

    // Add stats to bottom section.
    bottomSection.getChildren().add(stats);
    bottomSection.setStyle("-fx-background-color: #adeaea; -fx-border-color: "
        + "black; -fx-border-width: 3 0 0 0; -fx-padding: 5 0 5 10");
  }

  /**
   * Set up center view of the application, mainly to draw graph.
   */
  private static void setupCenterView() {
    // set background color of center pane
    centerSection.setStyle(
        "-fx-background-color: linear-gradient(to right, #61045F, #AA076B)");
  }

  /**
   * Set up right view of the application, mainly to get input from the user to
   * add/remove users/friendships.
   */
  private static void setupRightView() {
    // Create side-bar.
    rightSection.getChildren().addAll(setupSidebar());
    rightSection.setSpacing(10);
    rightSection
        .setStyle("-fx-background-color: #AA076B; -fx-border-color: black; "
            + "-fx-border-width: 0 0 0 3; -fx-padding: 0 10 0 10;");
  }

  /**
   * Set up navbar with buttons to allow users to create new social network,
   * load and save commands.
   * 
   * @return list of objects in navbar.
   */
  private static List<Node> setupNavbar() {
    List<Node> navbar = new ArrayList<Node>();
    // Create button to create new social network.
    navbar.add(createNavbarButton("images/New.png", "New", 0));

    navbar.add(new Separator());
    // Create button to load existing social network.
    navbar.add(createNavbarButton("images/Load.png", "Load file", 1));
    // Create button to save social network.
    navbar.add(createNavbarButton("images/Save.png", "Save file", 2));

    // navbar.add(new Separator());
    // Create button to undo change to social network.
    // navbar.add(createNavbarButton("images/Undo.png", "Undo", 3));
    // Create button to redo change to social network.
    // navbar.add(createNavbarButton("images/Redo.png", "Redo", 4));

    navbar.add(new Separator());
    // Create button to search for user.
    navbar.add(createSearchField("Search user", 0));

    navbar.add(new Separator());
    // Create drop-down to set central user.
    Set<String> users = buddENetwork.getAllUsernames();
    allUsersDropdownVBox = createDropdown(users, "Set central user");
    navbar.add(allUsersDropdownVBox);

    navbar.add(new Separator());
    // Create button to search for user.
    navbar.add(createSearchField("Shortest path", 1));

    return navbar;
  }

  /**
   * Create logo at top left corner.
   * 
   * @return ImageView of logo.
   */
  private static ImageView setupLogo() {
    Image logoPic = new Image("images/buddENetworkLogo.png"); // Load logo.
    ImageView logoView = new ImageView(); // Create ImageView for logo.
    logoView.setImage(logoPic);
    logoView.setFitHeight(75); // Set image height.
    logoView.setPreserveRatio(true); // Keep original image ratio.
    return logoView;
  }

  /**
   * Create navbar button.
   * 
   * @param iconFilename image for button.
   * @param label        button label.
   * @param index        0 for New, 1 for Load, 2 for Save.
   * @return VBox that has a navbar button (using image) and a label.
   */
  private static VBox createNavbarButton(String iconFilename, String label,
      int index) {
    VBox vBox = new VBox(); // Create a VBox.
    Image icon = new Image(iconFilename); // Import image.
    ImageView iconView = new ImageView(); // Create image view.
    iconView.setImage(icon); // Link image view and image.
    iconView.setFitWidth(50); // Set image width.
    iconView.setPreserveRatio(true); // Keep original image ratio.
    Button iconButton = new Button(); // Create a button.
    iconButton.setGraphic(iconView); // Link button with image view.

    // Event handler for different buttons, differentiate by index.
    switch (index) {
      case 0: // Create new social network.
        iconButton.setOnAction(e -> createNewSocialNetwork());
        break;
      case 1: // Create input dialog to get file name for loading.
        // iconButton.setOnAction(e -> createInputDialogAndLoadFile());
        iconButton.setOnAction(e -> createInputDialog(
            "Provide valid file to load your social network.", 0));
        break;
      case 2: // Create input dialog to get file name for saving.
        iconButton.setOnAction(e -> createInputDialog(
            "Provide valid file to save your social network.", 1));
        break;
      // case 3:
      // break;
      // case 4:
      // break;
      default:
        break;
    }
    Label buttonLabel = new Label(label); // Label for button.
    buttonLabel.setTextFill(Color.YELLOW);
    buttonLabel.setStyle("-fx-font-weight: bold");
    vBox.getChildren().addAll(iconButton, buttonLabel); // Add button and label.
    return vBox;
  }

  /**
   * Create search field to retrieve name entered by user.
   * 
   * @param label input field label.
   * @param index 0 for search and set central user, 1 for search and find
   *              shortest path.
   * @return VBox that contains input field and label.
   */
  private static VBox createSearchField(String label, int index) {
    HBox hBox = new HBox(); // Create a VBox.
    // Create a label for search field.
    Label searchLabel = new Label(label);
    searchLabel.setTextFill(Color.YELLOW);
    searchLabel.setStyle("-fx-font-weight: bold");
    TextField searchField = new TextField(); // Create an input field.
    searchField.setPromptText("Please enter a name"); // Placeholder for text.
    searchField.setPrefSize(150, 25);
    Image searchIcon = new Image("images/Search.png"); // Import image.
    ImageView iconView = new ImageView(); // Create image view.
    iconView.setImage(searchIcon); // Link image view and image.
    iconView.setFitHeight(17); // Set image width.
    iconView.setPreserveRatio(true); // Keep original image ratio.
    Button searchButton = new Button(); // Create a button to search.
    searchButton.setGraphic(iconView); // Link button with image view.

    searchButton.setOnAction(e -> searchUser(searchField, index));

    // Add search button and input field.
    hBox.getChildren().addAll(searchField, searchButton);
    VBox vBox = new VBox();
    vBox.getChildren().addAll(searchLabel, hBox);
    return vBox;
  }

  /**
   * Create drop-down when application starts, empty list.
   * 
   * @param users list of users.
   * @param label drop-down label.
   * @return VBox that contains drop-down and label.
   */
  private static VBox createDropdown(Set<String> users, String label) {
    VBox vBox = new VBox(); // Create a VBox.
    Label dropdownLabel = new Label(label); // Create a label for drop-down.
    dropdownLabel.setTextFill(Color.YELLOW);
    dropdownLabel.setStyle("-fx-font-weight: bold");
    ComboBox<String> dropdown = new ComboBox<String>(); // Create a drop-down.
    dropdown.getItems().addAll(users); // Add items to the drop-down.
    dropdown.setPrefSize(150, 25);
    // Add label and drop-down.
    vBox.getChildren().addAll(dropdownLabel, dropdown);
    return vBox;
  }

  /**
   * Create new social network, empty drop-down and graph.
   */
  private static void createNewSocialNetwork() {
    buddENetwork = new SocialNetwork();
    updateDropdownOfAllUsers();
    drawGraphCentralUser();
  }

  /**
   * Create text input dialog to prompt user for filename for load/save social
   * network.
   * 
   * @param header prompted text of dialog.
   * @param index  0 for loading, 1 for saving.
   */
  private static void createInputDialog(String header, int index) {
    // Create text input dialog.
    TextInputDialog dialog = new TextInputDialog("Please enter filename:");
    dialog.setHeaderText(header);

    // Check if user enters anything.
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) { // Input is available for use.
      currentFilename = dialog.getEditor().getText(); // Get filename.
      // Event handler for different buttons, differentiate by index.
      switch (index) {
        case 0:
          loadSaveSocialNetwork(index); // Load social network.
          updateDropdownOfAllUsers(); // Update drop-down to include all users.
          drawGraphCentralUser(); // Re-draw graph corresponding to changes.
          break;
        case 1:
          loadSaveSocialNetwork(index); // Save social network.
          break;
        default:
          break;
      }
    }
  }

  /**
   * Load or save social network from/to filename given by the user.
   * 
   * @param index 0 for loading, 1 for saving.
   */
  private static void loadSaveSocialNetwork(int index) {
    try {
      // Event handler for different buttons, differentiate by index.
      switch (index) {
        case 0: // Load file.
          buddENetwork.loadFromFile(currentFilename); // Load commands.
          socialNetworkChangedAndUnsaved = true; // Social network changed.
          updateSocialNetworkStat(); // Update network statistics.
          updateCentralUserStat(); // Update central user statistics.
          break;
        case 1: // Save file.
          buddENetwork.saveToFile(currentFilename); // Save commands.
          socialNetworkChangedAndUnsaved = false; // Mark it saved.
          break;
        default:
          break;
      }
    }

    // Catch different types of exception and displays appropriate messages
    // using alerts.
    catch (IOException e) {
      Alert alert = new Alert(AlertType.WARNING,
          "<" + currentFilename + "> does not exist in the directory.");
      alert.show();
    } catch (IllegalNullArgumentException e) {
      Alert alert =
          new Alert(AlertType.WARNING, "Empty filename is not acceptable.");
      alert.show();
    } catch (Exception e) {
      Alert alert =
          new Alert(AlertType.WARNING, "Sorry, unexpected error occured.");
      alert.show();
    }
  }

  /**
   * Update drop-down to include all users of social network. This method is
   * called when there is change to users in social network.
   */
  private static void updateDropdownOfAllUsers() {
    Set<String> users = buddENetwork.getAllUsernames(); // Get all users.
    allUsersDropdownVBox.getChildren().clear();
    // Create a label for drop-down.
    Label dropdownLabel = new Label("Set central user");
    dropdownLabel.setTextFill(Color.YELLOW);
    ComboBox<String> dropdown = new ComboBox<String>(); // Create a drop-down.
    dropdown.getItems().addAll(users); // Add items to the drop-down.
    dropdown.setPrefSize(150, 25); // Set drop-down width.
    // Add all users to drop-down.
    allUsersDropdownVBox.getChildren().addAll(dropdownLabel, dropdown);

    // Event listener waiting for user to select a value from dropdown.
    dropdown.setOnAction(e -> setCentralUserFromDropdown(dropdown));
  }

  /**
   * Search for user from the name entered in the text field.
   * 
   * @param searchField input field that contains name.
   * @param index       integer number to differentiate which action.
   */
  private static void searchUser(TextField searchField, int index) {
    String name = searchField.getText(); // Get text.
    searchField.setText(""); // Reset text.
    try {
      // Event handler for different buttons, differentiate by index.
      switch (index) {
        case 0: // Search and set central user.
          buddENetwork.setCentralUser(name);
          updateCentralUserStat();
          socialNetworkChangedAndUnsaved = true;
          drawGraphCentralUser();
          break;
        case 1: // Display shortest path from central user to entered user.
          displayShortestPath(name);
          break;
        default:
          break;
      }
    }

    // Catch different exceptions and display appropriate messages.
    catch (IllegalNullArgumentException e) {
      Alert alert =
          new Alert(AlertType.WARNING, "Field should not be left blank.");
      alert.show();
    } catch (UserNotFoundException e) {
      Alert alert = new Alert(AlertType.WARNING,
          "User <" + name + "> does not exist in the BuddE Network.");
      alert.show();
    } catch (Exception e) {
      Alert alert =
          new Alert(AlertType.WARNING, "Sorry, unexpected error occured.");
      alert.show();
    }
  }

  /**
   * Set the selected value from drop-down to be the central user.
   * 
   * @param dropdown drop-down that contains all users in social network.
   */
  private static void setCentralUserFromDropdown(ComboBox<String> dropdown) {
    String chosenUser = dropdown.getValue(); // Chosen user.
    try {
      buddENetwork.setCentralUser(chosenUser); // First, set central user.
      updateCentralUserStat(); // Then update central user statistics.
      socialNetworkChangedAndUnsaved = true; // Mark change in social network.
      drawGraphCentralUser(); // Redraw graph with new central user.
    } catch (Exception e) {
    }
  }

  /**
   * Call other helper methods to set up side bar with sections to add/remove
   * users/friendships and find mutual friends.
   * 
   * @return list of objects from the side bar.
   */
  private static List<Node> setupSidebar() {
    List<Node> sidebar = new ArrayList<Node>();
    // Add/remove user section.
    sidebar.add(createSidebarSection("User Settings", "Create New User",
        "Remove User", 10, 29, 0, 1));

    sidebar.add(new Separator());
    // Add/remove friendship section.
    sidebar.add(createSidebarSection("BuddE Settings", "Add BuddE",
        "Remove BuddE", 40, 18, 2, 3));

    sidebar.add(new Separator());
    // Add mutual friends section.
    sidebar.add(createMutualFriendsSection());

    return sidebar;
  }

  /**
   * Create side bar section on the right with two input fields and buttons.
   * 
   * @param sectionLabel  general section label.
   * @param addLabel      label for adding something.
   * @param removeLabel   label for removing something.
   * @param firstSpacing  spacing between first input field and button.
   * @param secondSpacing spacing between second input field and button.
   * @param firstIndex    first index to indicate action from SocialNetwork.
   * @param secondIndex   second index to indicate action from SocialNetwork.
   * @return VBox that contains a section with two input fields and two buttons.
   */
  private static VBox createSidebarSection(String sectionLabel, String addLabel,
      String removeLabel, int firstSpacing, int secondSpacing, int firstIndex,
      int secondIndex) {
    Label label = createSidebarLabel(sectionLabel);

    // Create input fields to add and to remove.
    HBox add = createInputField(addLabel, firstSpacing, firstIndex);
    HBox remove = createInputField(removeLabel, secondSpacing, secondIndex);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(label, add, remove);
    vBox.setSpacing(10);
    return vBox;
  }

  /**
   * Create label and apply style.
   * 
   * @param labelText label text.
   * @return Label instance.
   */
  private static Label createSidebarLabel(String labelText) {
    Label label = new Label(labelText);
    label.setFont(Font.font(16));
    label.setStyle("-fx-font-weight: bold");
    label.setTextFill(Color.YELLOW);
    return label;
  }

  /**
   * Create section that allows user to enter name to find mutual friends
   * between central user and entered user.
   * 
   * @return VBox that contains input field to retrieve name, button, and list
   *         view of mutual buddies.
   */
  private static VBox createMutualFriendsSection() {
    VBox vBox = new VBox(); // VBox for input field and button.
    // Label for mutual buddies section.
    Label label = createSidebarLabel("Mutual BuddEs");
    // Create button and text field to retrieve second user.
    HBox field = createInputField("Mutual BuddEs", 19, 4);
    // List view to display mutual friends.
    ListView<String> listView = new ListView<String>();
    vBox.getChildren().addAll(label, field, listView);
    vBox.setSpacing(10);
    VBox.setVgrow(listView, Priority.ALWAYS);

    return vBox;
  }

  /**
   * Create input field to retrieve name from user.
   * 
   * @param buttonLabel label for button.
   * @param spacing     spacing between button and input field.
   * @param index       integer number to differential which action to do.
   * @return the HBox that contains input field and button.
   */
  private static HBox createInputField(String buttonLabel, int spacing,
      int index) {
    HBox hBox = new HBox(); // New HBox.
    Button button = new Button(buttonLabel); // Create new button.
    TextField inputField = new TextField(); // Create new text field.
    inputField.setPromptText("Please enter a name"); // Prompt text.
    // Add input field and button to HBox.
    hBox.getChildren().addAll(button, inputField);
    hBox.setSpacing(spacing); // Set spacing between button and input field.

    // Event listener for button to do action of SocialNetwork.
    button.setOnAction(e -> socialNetworkAction(inputField, index));
    return hBox;
  }

  /**
   * Call appropriate methods from SocialNetwork after user enters input.
   * 
   * @param inputField field to retrieve name from user input.
   * @param index      an integer differentiating which action to do.
   */
  private static void socialNetworkAction(TextField inputField, int index) {
    // Get entered name from input field.
    String name = inputField.getText();
    if (index != 4) { // No need to clear input for mutual buddies section.
      inputField.setText("");
    }
    // Get central user name.
    User centralUser = buddENetwork.getCentralUser();
    String centralName = null;
    if (centralUser != null) {
      centralName = centralUser.getName();
    }

    // Show warning if user attempts add/remove friendship or find mutual
    // buddies before setting central user.
    if ((index == 2 || index == 3 || index == 4) && centralName == null) {
      Label label = new Label("Central user is not set. Please create a user "
          + "and set central user first before attempting to add/remove "
          + "friendship.");
      label.setWrapText(true);
      Alert alert = new Alert(AlertType.WARNING);
      alert.getDialogPane().setContent(label);
      alert.show();
    }

    // Event handler for different buttons, differentiate by index.
    try {
      switch (index) {
        case 0: // Add user.
          buddENetwork.addUser(name);
          break;
        case 1: // Remove user.
          buddENetwork.removeUser(name);
          break;
        case 2: // Add friendship.
          if (centralName != null) {
            buddENetwork.addFriendship(centralName, name);
          }
          break;
        case 3: // Remove friendship.
          if (centralName != null) {
            buddENetwork.removeFriendship(centralName, name);
          }
          break;
        case 4: // Find mutual friends.
          if (centralName != null) {
            // Set of mutual friends.
            Set<String> mutual =
                buddENetwork.getMutualFriends(centralName, name);
            // Mutual friend section is 4th element of rightSection
            Node node = rightSection.getChildren().get(4);
            if (node instanceof VBox) {
              // Retrieve the view list to add users.
              VBox mutualSection = (VBox) node;
              Node list = mutualSection.getChildren().get(2);

              if (list instanceof ListView) {
                // Adding mutual friends to the list for display.
                @SuppressWarnings("unchecked")
                ListView<String> mutualList = (ListView<String>) list;
                mutualList.getItems().clear();
                // If there is no mutual friend, show message to indicate so.
                if (mutual.size() <= 0) {
                  mutualList.getItems()
                      .add("No mutual friends between you and " + name + "!");
                } else {
                  mutualList.getItems().addAll(mutual);
                }
              }
            }
          }
          break;
        default:
          break;
      }

      // Except for finding mutual buddies, add/remove user/friendship will
      // change the social network, update drop-down, update social network and
      // central user statistics.
      if (index != 4) {
        socialNetworkChangedAndUnsaved = true;
        updateDropdownOfAllUsers();
        drawGraphCentralUser();
        updateSocialNetworkStat();
        updateCentralUserStat();
      }
    }

    // Catch different types of exception and displays appropriate messages
    // using alerts.
    catch (IllegalNullArgumentException e) {
      Alert alert =
          new Alert(AlertType.WARNING, "Field should not be left blank.");
      alert.show();
    } catch (UserNotFoundException e) {
      Alert alert = new Alert(AlertType.WARNING,
          "User <" + name + "> does not exist in the BuddE Network.");
      alert.show();
    } catch (FriendshipNotFoundException e) {
      Alert alert = new Alert(AlertType.WARNING, "Friendship between you and "
          + name + " did not exist in the BuddE Network");
      alert.show();
    } catch (DuplicateUserException e) {
      Alert alert = new Alert(AlertType.WARNING,
          "User <" + name + "> already exists in the BuddE Network.");
      alert.show();
    } catch (DuplicateFriendshipException e) {
      Alert alert = new Alert(AlertType.WARNING, "Friendship between you and "
          + name + " already exists in the BuddE Network");
      alert.show();
    } catch (Exception e) {
      Alert alert =
          new Alert(AlertType.WARNING, "Sorry, unexpected error occured.");
      alert.show();
    }
  }

  /**
   * Draw graph that displays friends of central user.
   */
  private static void drawGraphCentralUser() {
    // Create canvas, set font, style.
    Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // Set text attributes
    gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    // Set stroke attributes
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);

    // Draw circles (vertices) to represent people and lines connecting the
    // central user and their friends
    // The circles draw from the top left, so to center them, subtract the
    // radius from each coordinate
    double centerX = CANVAS_WIDTH / 2.0;
    double centerY = CANVAS_HEIGHT / 2.0;
    // Names are centered in the middle of the circle
    gc.setFill(Color.YELLOW);
    if (buddENetwork.getCentralUser() != null) {
      String centralName = buddENetwork.getCentralUser().getName();
      int numFriends = -1;
      try {
        // Friends of central user.
        Set<User> friends = buddENetwork.getFriends(centralName);
        numFriends = friends.size();
        // Retrieve coordinates for friends.
        double[][] coords = getCoordinates(numFriends);
        int i = 0;
        // Draw circles representing friends of central user.
        for (User friend : friends) {
          double x = centerX + coords[i][0];
          double y = centerY + coords[i][1];
          gc.strokeLine(centerX, centerY, x, y);

          gc.setFill(
              Paint.valueOf("linear-gradient(to top left, #E5F230, #54DB63)"));
          gc.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2.0, RADIUS * 2.0);

          gc.setFill(Paint.valueOf("#0100EC"));
          gc.fillText(friend.getName(), x - RADIUS + 5, y + 5);
          i++;
        }

        // Draw circle for central user with different style.
        gc.setFill(
            Paint.valueOf("linear-gradient(to top left, #00FFED, #9D00C6)"));
        gc.fillOval(centerX - RADIUS, centerY - RADIUS, RADIUS * 2.0,
            RADIUS * 2.0);
        gc.setFill(Paint.valueOf("#f6da63"));
        gc.fillText(centralName, centerX - RADIUS + 5, centerY + 5);
      } catch (Exception e) {
      }
    }

    // Clear center section and add new canvas.
    centerSection.getChildren().clear();
    centerSection.getChildren().add(canvas);
  }

  /**
   * Get coordinates for friends of central user.
   * 
   * @param numUsers number of friends of current central user.
   * @return 2-d array representing coordinates of friends.
   */
  private static double[][] getCoordinates(int numUsers) {
    double[][] coords = new double[numUsers][2];
    double angle = Math.PI * 2.0 / numUsers; // Angle between user circles.
    // Use math to distribute space evenly between circles.
    for (int i = 0; i < numUsers; ++i) {
      double anglePosition = angle * i;
      coords[i][0] = Math.sin(anglePosition) * DISTANCE;
      coords[i][1] = Math.cos(anglePosition) * DISTANCE;
    }
    return coords;
  }

  /**
   * GUI that shows the shortest path from central user to another user.
   * 
   * @param enteredName username that user enters.
   */
  private static void displayShortestPath(String enteredName) {
    // Create canvas, set font, style.
    Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // Set text attributes
    gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    // Set stroke attributes
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);

    // Draw circles (vertices) to represent people and lines connecting the
    // central user and their friends
    // The circles draw from the top left, so to center them, subtract the
    // radius from each coordinate.
    double centerY = CANVAS_HEIGHT / 2.0;
    // Names are centered in the middle of the circle
    gc.setFill(Color.YELLOW);
    if (buddENetwork.getCentralUser() != null) {
      String centralName = buddENetwork.getCentralUser().getName();
      try {
        double x = 100; // Value of central user circle's center.
        double distance = 3 * RADIUS; // Distance between circles' center.
        // Shortest path from one user to another.
        List<User> users =
            buddENetwork.getShortestPath(centralName, enteredName);
        // Draw line connecting users.
        for (int i = 1; i < users.size(); ++i) {
          gc.strokeLine(x, centerY, x + distance, centerY);
          x = x + distance;
        }
        x = 100 + distance; // Center of first user, not including central user.
        // Draw circles of users, except central user.
        for (int i = 1; i < users.size(); ++i) {
          gc.setFill(
              Paint.valueOf("linear-gradient(to top left, #E5F230, #54DB63)"));
          gc.fillOval(x - RADIUS, centerY - RADIUS, RADIUS * 2.0, RADIUS * 2.0);

          gc.setFill(Paint.valueOf("#0100EC"));
          gc.fillText(users.get(i).getName(), x - RADIUS + 5, centerY + 5);

          x = x + distance;
        }

        // Draw circle for central user with different style.
        gc.setFill(
            Paint.valueOf("linear-gradient(to top left, #00FFED, #9D00C6)"));
        gc.fillOval(100 - RADIUS, centerY - RADIUS, RADIUS * 2.0, RADIUS * 2.0);
        gc.setFill(Paint.valueOf("#f6da63"));
        gc.fillText(centralName, 100 - RADIUS + 5, centerY + 5);
      } catch (Exception e) {
      }
    }

    // Clear center section and add new canvas.
    centerSection.getChildren().clear();
    centerSection.getChildren().add(canvas);
  }

  /**
   * Update number of users, connections, and connected components of social
   * network.
   */
  private static void updateSocialNetworkStat() {
    String status = "BuddE Network stats: " + buddENetwork.numberUsers()
        + " users --- " + buddENetwork.numberConnections() + " friendships --- "
        + buddENetwork.getConnectedComponents().size()
        + " connected groups/components.";
    socialNetworkStat.setText(status);
  }

  /**
   * Update number of friends of central user.
   */
  private static void updateCentralUserStat() {
    User centralUser = buddENetwork.getCentralUser();
    if (centralUser != null) {
      String status = centralUser.getName() + " has "
          + centralUser.getFriends().size() + " buddies.";
      centralUserStat.setText(status);
    }
  }

  /**
   * Show up a confirmation dialog if user exits and does not save change to
   * social network, otherwise no pop up shows up.
   * 
   * @param stage main stage of application.
   * @param e     triggered when closing application.
   */
  private static void confirmWhenClose(Stage stage, WindowEvent e) {
    // Only show confirmation box if social network was changed.
    if (socialNetworkChangedAndUnsaved) {
      // Create alert pop-up.
      Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
      // Close button.
      Button exitButton = (Button) closeConfirmation.getDialogPane()
          .lookupButton(ButtonType.OK);
      exitButton.setText("Exit");
      // Set confirmation text.
      closeConfirmation.setHeaderText(
          "Are you sure you want to exit without saving? If so, all the changes"
              + " that you have made to the social network would not be saved.");
      // Trigger event and link everything together.
      Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
      if (!ButtonType.OK.equals(closeResponse.get())) {
        e.consume();
      }
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
