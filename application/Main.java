//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Main.java
// Files: Main.java (A-Team 12 Milestone 2)
// Course: CS 400, Section 001/002, Fall 2019
//
// Authors: Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar
// Email: ktle4@wisc.edu, sstiles@wisc.edu, klmui@wisc.edu, skhullar2@wisc.edu
// Lecturer's Name: Deb Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Lecturer's Name: Deb Deppeler
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: CS Learning Center
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Please note that this class is A Team 12's implementation of the JavaFX
 * Project, which is the BuddE Network.
 * 
 * @author Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar,
 *         Professor Deppeler, Teaching Assistants
 *
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
 * @author Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar.
 *
 */
public class Main extends Application {
  private static SocialNetwork buddENetwork = new SocialNetwork();
  private static String currentFilename = null;
  private static VBox allUsersDropdownVBox = new VBox();
  private static Label centralUserStat = new Label();
  private static Label socialNetworkStat = new Label();

  private static boolean socialNetworkChangedAndUnsaved = false;

  private static HBox topSection = new HBox();
  private static HBox bottomSection = new HBox();
  private static VBox centerSection = new VBox();
  private static VBox rightSection = new VBox();

  private static final double WINDOW_WIDTH = 1400;
  private static final double WINDOW_HEIGHT = 750;
  private static final double CANVAS_WIDTH = 1000;
  private static final double CANVAS_HEIGHT = 550;
  private static final double RADIUS = 40;
  private static final double DISTANCE = 200;
  private static final String APP_TITLE = "BuddE Network";

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



    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.getIcons().add(new Image("images/buddENetworkIcon.png"));
    primaryStage.setScene(mainScene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> confirmWhenClose(primaryStage, e));
  }

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

  private static void setupCenterView() {
    // set background color of center pane
    centerSection.setStyle(
        "-fx-background-color: linear-gradient(to right, #61045F, #AA076B)");
  }

  private static void setupRightView() {
    // Create side-bar.
    rightSection.getChildren().addAll(setupSidebar());
    rightSection.setSpacing(10);
    rightSection
        .setStyle("-fx-background-color: #AA076B; -fx-border-color: black; "
            + "-fx-border-width: 0 0 0 3; -fx-padding: 0 10 0 10;");
  }

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
    navbar.add(createSearchField());

    navbar.add(new Separator());
    // Create drop-down to set central user.
    Set<String> users = buddENetwork.getAllUsernames();
    allUsersDropdownVBox = createDropdown(users, "Set central user");
    navbar.add(allUsersDropdownVBox);
    return navbar;
  }

  private static ImageView setupLogo() {
    Image logoPic = new Image("images/buddENetworkLogo.png");
    ImageView logoView = new ImageView();
    logoView.setImage(logoPic);
    logoView.setFitHeight(75); // Set image height.
    logoView.setPreserveRatio(true); // Keep original image ratio.
    return logoView;
  }

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
      case 0:
        iconButton.setOnAction(e -> createNewSocialNetwork());
        break;
      case 1:
        // iconButton.setOnAction(e -> createInputDialogAndLoadFile());
        iconButton.setOnAction(e -> createInputDialog(
            "Provide valid file to load your social network.", 0));
        break;
      case 2:
        iconButton.setOnAction(e -> createInputDialog(
            "Provide valid file to save your social network.", 1));
        break;
      case 3:
        break;
      case 4:
        break;
      default:
    }
    Label buttonLabel = new Label(label); // Label for button.
    buttonLabel.setTextFill(Color.YELLOW);
    buttonLabel.setStyle("-fx-font-weight: bold");
    vBox.getChildren().addAll(iconButton, buttonLabel); // Add button and label.
    return vBox;
  }

  private static VBox createSearchField() {
    HBox hBox = new HBox(); // Create a VBox.
    // Create a label for search field.
    Label searchLabel = new Label("Search user");
    searchLabel.setTextFill(Color.YELLOW);
    searchLabel.setStyle("-fx-font-weight: bold");
    TextField searchField = new TextField(); // Create an input field.
    searchField.setPromptText("Search for User"); // Placeholder for text.
    searchField.setPrefSize(150, 25);
    Image searchIcon = new Image("images/Search.png"); // Import image.
    ImageView iconView = new ImageView(); // Create image view.
    iconView.setImage(searchIcon); // Link image view and image.
    iconView.setFitHeight(17); // Set image width.
    iconView.setPreserveRatio(true); // Keep original image ratio.
    Button searchButton = new Button(); // Create a button to search.
    searchButton.setGraphic(iconView); // Link button with image view.

    searchButton.setOnAction(e -> searchUserAndSetCentralUser(searchField));

    // Add search button and input field.
    hBox.getChildren().addAll(searchField, searchButton);
    VBox vBox = new VBox();
    vBox.getChildren().addAll(searchLabel, hBox);
    return vBox;
  }

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

  private static void createNewSocialNetwork() {
    buddENetwork = new SocialNetwork();
    updateDropdownOfAllUsers();
    drawGraph();
  }

  private static void createInputDialog(String header, int index) {
    TextInputDialog dialog = new TextInputDialog("Please enter filename:");
    dialog.setHeaderText(header);
    // dialog.showAndWait();

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      currentFilename = dialog.getEditor().getText();
      // Event handler for different buttons, differentiate by index.
      switch (index) {
        case 0:
          loadSaveSocialNetwork(index);
          updateDropdownOfAllUsers();
          drawGraph();
          break;
        case 1:
          loadSaveSocialNetwork(index);
          break;
        default:
      }
    }
  }

  private static void loadSaveSocialNetwork(int index) {
    try {
      // Event handler for different buttons, differentiate by index.
      switch (index) {
        case 0:
          buddENetwork.loadFromFile(currentFilename);
          updateSocialNetworkStat();
          updateCentralUserStat();
          break;
        case 1:
          buddENetwork.saveToFile(currentFilename);
          socialNetworkChangedAndUnsaved = false;
          break;
        default:
      }
    } catch (IOException e) {
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

  private static void updateDropdownOfAllUsers() {
    Set<String> users = buddENetwork.getAllUsernames();
    allUsersDropdownVBox.getChildren().clear();
    // Create a label for drop-down.
    Label dropdownLabel = new Label("Set central user");
    dropdownLabel.setTextFill(Color.WHITE);
    ComboBox<String> dropdown = new ComboBox<String>(); // Create a drop-down.
    dropdown.getItems().addAll(users); // Add items to the drop-down.
    dropdown.setPrefSize(150, 25);
    allUsersDropdownVBox.getChildren().addAll(dropdownLabel, dropdown);

    dropdown.setOnAction(e -> setCentralUserFromDropdown(dropdown));
  }

  private static void searchUserAndSetCentralUser(TextField searchField) {
    String name = searchField.getText();
    searchField.setText("");
    try {
      buddENetwork.setCentralUser(name);
      updateCentralUserStat();
      socialNetworkChangedAndUnsaved = true;
      drawGraph();
    } catch (IllegalNullArgumentException e) {
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

  private static void setCentralUserFromDropdown(ComboBox<String> dropdown) {
    String chosenUser = dropdown.getValue();
    try {
      buddENetwork.setCentralUser(chosenUser);
      updateCentralUserStat();
      socialNetworkChangedAndUnsaved = true;
      drawGraph();
    } catch (Exception e) {
    }
  }

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

  private static Label createSidebarLabel(String labelText) {
    Label label = new Label(labelText);
    label.setFont(Font.font(16));
    label.setStyle("-fx-font-weight: bold");
    label.setTextFill(Color.YELLOW);
    return label;
  }

  private static VBox createMutualFriendsSection() {
    VBox vBox = new VBox();
    // Label for mutual buddies section.
    Label label = createSidebarLabel("Mutual BuddEs");
    // Create button and text field to retrieve second user.
    HBox field = createInputField("Mutual BuddEs", 19, 4);

    ListView<String> listView = new ListView<String>();
    vBox.getChildren().addAll(label, field, listView);
    vBox.setSpacing(10);
    VBox.setVgrow(listView, Priority.ALWAYS);

    return vBox;
  }

  private static HBox createInputField(String buttonLabel, int spacing,
      int index) {
    HBox hBox = new HBox();
    Button button = new Button(buttonLabel);
    TextField inputField = new TextField();
    inputField.setPromptText("Please enter a name");
    hBox.getChildren().addAll(button, inputField);
    hBox.setSpacing(spacing);

    button.setOnAction(e -> socialNetworkAction(inputField, index));
    return hBox;
  }

  private static void socialNetworkAction(TextField inputField, int index) {
    String name = inputField.getText();
    if (index != 4) { // No need to clear input for mutual buddies section.
      inputField.setText("");
    }
    User centralUser = buddENetwork.getCentralUser();
    String centralName = null;
    if (centralUser != null) {
      centralName = centralUser.getName();
    }

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
        case 0:
          buddENetwork.addUser(name);
          break;
        case 1:
          buddENetwork.removeUser(name);
          break;
        case 2:
          if (centralName != null) {
            buddENetwork.addFriendship(centralName, name);
          }
          break;
        case 3:
          if (centralName != null) {
            buddENetwork.removeFriendship(centralName, name);
          }
          break;
        case 4:
          if (centralName != null) {
            Set<String> mutual =
                buddENetwork.getMutualFriends(centralName, name);
            // Mutual friend section is 4th element of rightSection
            Node node = rightSection.getChildren().get(4);
            if (node instanceof VBox) {
              VBox mutualSection = (VBox) node;
              Node list = mutualSection.getChildren().get(2);

              if (list instanceof ListView) {
                @SuppressWarnings("unchecked")
                ListView<String> mutualList = (ListView<String>) list;
                mutualList.getItems().clear();
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
      socialNetworkChangedAndUnsaved = true;
      updateDropdownOfAllUsers();
      drawGraph();
      updateSocialNetworkStat();
      updateCentralUserStat();
    } catch (IllegalNullArgumentException e) {
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

  private static void drawGraph() {
    // centerVBox.getChildren().remove(0);
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
        Set<User> friends = buddENetwork.getFriends(centralName);
        numFriends = friends.size();

        double[][] coords = getCoordinates(numFriends);
        int i = 0;
        for (User friend : buddENetwork.getFriends(centralName)) {
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

        // gc.setFill(Paint.valueOf(
        // "linear-gradient(to top left, #FFE031, #F04579)"));
        gc.setFill(
            Paint.valueOf("linear-gradient(to top left, #00FFED, #9D00C6)"));
        gc.fillOval(centerX - RADIUS, centerY - RADIUS, RADIUS * 2.0,
            RADIUS * 2.0);
        gc.setFill(Paint.valueOf("#f6da63"));
        gc.fillText(centralName, centerX - RADIUS + 5, centerY + 5);
      } catch (Exception e) {
      }
    }

    centerSection.getChildren().clear();
    // centerVBox = new VBox();
    centerSection.getChildren().add(canvas);
  }

  private static double[][] getCoordinates(int numUsers) {
    double[][] coords = new double[numUsers][2];
    double angle = Math.PI * 2.0 / numUsers;
    for (int i = 0; i < numUsers; ++i) {
      double anglePosition = angle * i;
      coords[i][0] = Math.sin(anglePosition) * DISTANCE;
      coords[i][1] = Math.cos(anglePosition) * DISTANCE;
    }
    return coords;
  }

  private static void updateSocialNetworkStat() {
    String status = "BuddE Network stats: " + buddENetwork.numberUsers()
        + " users --- " + buddENetwork.numberConnections() + " friendships --- "
        + buddENetwork.getConnectedComponents().size()
        + " connected groups/components.";
    socialNetworkStat.setText(status);
  }

  private static void updateCentralUserStat() {
    User centralUser = buddENetwork.getCentralUser();
    if (centralUser != null) {
      String status = centralUser.getName() + " has "
          + centralUser.getFriends().size() + " buddies.";
      centralUserStat.setText(status);
    }
  }

  private static void confirmWhenClose(Stage stage, WindowEvent e) {
    if (socialNetworkChangedAndUnsaved) {
      Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
      Button exitButton = (Button) closeConfirmation.getDialogPane()
          .lookupButton(ButtonType.OK);
      exitButton.setText("Exit");
      closeConfirmation.setHeaderText(
          "Are you sure you want to exit without saving? If so, all the changes"
              + " that you have made to the social network would not be saved.");
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
