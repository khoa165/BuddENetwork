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
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar.
 *
 */
public class Main extends Application {
  private static SocialNetwork buddENetwork = new SocialNetwork();
  private static String currentFilename = null;
  private static VBox allUsersDropdownVBox = null;

  private static HBox topHBox = null;
  private static VBox centerVBox = null;
  private static VBox rightVBox = null;

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
    topHBox = setupTopBox();
    root.setTop(topHBox); // Set top pane.

    // ---------------------- Center pane -------------------------------------
    centerVBox = setupCenterBox();
    root.setCenter(centerVBox); // Set center pane.

    // ---------------------- Right pane --------------------------------------
    rightVBox = setupRightBox();
    root.setRight(rightVBox); // Set right pane.

    // TODO Do we want to add anything in the left pane?
    // Add ComboBox to left pane
    // ComboBox<String> comBox = new ComboBox<String>();
    // comBox.getItems().addAll("Harry", "Kenny", "Saniya", "Shannon");
    // root.setLeft(comBox);



    // Add done button to bottom pane
    HBox statusHBox = new HBox();
    Label status = new Label("STATUS: ");
    status.setFont(Font.font("Calibri", FontWeight.BOLD, 24));
    status.setTextFill(Color.BLUE);
    TextField statusMessage = new TextField();
    statusHBox.getChildren().addAll(status, statusMessage);
    // statusHBox.setStyle("-fx-background-color: blue");

    root.setBottom(statusHBox);
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.getIcons().add(new Image("images/buddENetworkIcon.png"));
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Please note that this class allows us to view the list of mutual BuddEs,
   * between the central user and one of his/her buddEs.
   * 
   * @author
   *
   */
  static class addFriendToCell extends ListCell<String> {
    @Override
    public void updateItem(String item, boolean empty) {
      super.updateItem(item, empty);
      Label friendNameLabel;
      if (item != null) {
        friendNameLabel = new Label(item);
        setGraphic(friendNameLabel);
      }
    }
  }

  private static HBox setupTopBox() {
    // Add image to label to go in top pane
    ImageView logoView = createLogo();

    // Create New, Open file, Undo, Redo, Save file buttons.
    VBox newVBox = createNavbarButton("images/New.png", "New");
    VBox openVBox = createNavbarButton("images/Load.png", "Load file");
    VBox undoVBox = createNavbarButton("images/Undo.png", "Undo");
    VBox redoVBox = createNavbarButton("images/Redo.png", "Redo");
    VBox saveVBox = createNavbarButton("images/Save.png", "Save file");
    // Create custom search field.
    HBox searchVBox = createSearchField();

    // Add vbox for setting central user
    Set<String> users = buddENetwork.getAllUsernames();
    allUsersDropdownVBox = createDropdown(users, "Set central user");

    // create tool bar of functions for top pane
    ToolBar toolBar = new ToolBar(newVBox, openVBox, new Separator(), undoVBox,
        redoVBox, new Separator(), saveVBox, new Separator(), searchVBox,
        new Separator(), allUsersDropdownVBox);

    // Add hbox with vboxes in it to top pane
    HBox topBox = new HBox();
    topBox.getChildren().addAll(logoView, toolBar);
    topBox.setSpacing(10);
    // topHBox.setStyle("-fx-background-color: blue");

    return topBox;
  }

  private static VBox setupCenterBox() {
    VBox centerBox = new VBox();
    return centerBox;
  }

  private static VBox setupRightBox() {
    // create separator between user and buddE functions
    Separator separator1 = new Separator();

    // Title for BuddEs section
    Label userSettingsTitle = new Label("User Settings");
    userSettingsTitle.setFont(Font.font(16));
    userSettingsTitle.setTextFill(Color.BLUE);

    // Create input fields to add new user and remove user.
    HBox addUserHBox = createInputField("Create New User", 10);
    HBox removeUserHBox = createInputField("Remove User", 29);


    // create vbox for user functions
    VBox userVBox = new VBox();
    userVBox.getChildren().addAll(userSettingsTitle, addUserHBox,
        removeUserHBox);
    userVBox.setSpacing(10);

    // create separator between user and buddE functions
    Separator separator2 = new Separator();
    // separator1.setMaxWidth(150);

    // Title for BuddEs section
    Label buddESettingsTitle = new Label("BuddE Settings");
    buddESettingsTitle.setFont(Font.font(16));
    buddESettingsTitle.setTextFill(Color.BLUE);

    // create hbox for adding BuddE connection between central user and
    // other user
    HBox addBuddEHBox = createInputField("Add BuddE", 40);

    // create hbox for removing BuddE connection between central user and
    // other user
    HBox removeBuddEHBox = createInputField("Remove BuddE", 18);

    // create vbox for BuddE functions
    VBox buddEVBox = new VBox();
    buddEVBox.getChildren().addAll(buddESettingsTitle, addBuddEHBox,
        removeBuddEHBox);
    buddEVBox.setSpacing(10);


    // create separator between buddE functions and mutual BuddEs/friends
    Separator separator3 = new Separator();

    // Title for Mutual BuddEs section
    Label mutualBuddEsTitle = new Label("Mutual BuddEs");
    mutualBuddEsTitle.setFont(Font.font(16));
    mutualBuddEsTitle.setTextFill(Color.BLUE);

    // create hbox with button and text field for Mutual BuddEs section
    HBox mutualBuddEsHBox = createInputField("Mutual BuddEs", 19);



    // ---------------------- Mutual BuddEs code ------------------------------
    ListView<String> list = new ListView<>();
    ObservableList<String> mutualFriends =
        FXCollections.observableList(List.of("Saniya", "Shannon"));

    VBox mutualBuddEsVBox = new VBox();

    Scene scene = new Scene(mutualBuddEsVBox, 100, 100);
    // stage.setScene(scene);
    // stage.setTitle("Mutual BuddEs");
    mutualBuddEsVBox.getChildren().addAll(list);
    VBox.setVgrow(list, Priority.ALWAYS);

    list.setItems(mutualFriends);

    list.setCellFactory((ListView<String> l) -> new addFriendToCell());

    // stage.show();

    // ------------------End Mutual BuddEs code -------------------------------

    // create separator between user and buddE functions
    Separator separator4 = new Separator();

    // Add vbox with hboxes in it to right pane
    VBox rightBox = new VBox();
    rightBox.getChildren().addAll(separator1, userVBox, separator2, buddEVBox,
        separator3, mutualBuddEsTitle, mutualBuddEsHBox, mutualBuddEsVBox,
        separator4);
    rightBox.setSpacing(10);

    return rightBox;
  }

  private static ImageView createLogo() {
    Image logoPic = new Image("images/buddENetworkLogo.png");
    ImageView logoView = new ImageView();
    logoView.setImage(logoPic);
    logoView.setFitHeight(75); // Set image height.
    logoView.setPreserveRatio(true); // Keep original image ratio.
    return logoView;
  }

  private static VBox createNavbarButton(String iconFilename, String label) {
    VBox vBox = new VBox(); // Create a VBox.
    Image icon = new Image(iconFilename); // Import image.
    ImageView iconView = new ImageView(); // Create image view.
    iconView.setImage(icon); // Link image view and image.
    iconView.setFitWidth(50); // Set image width.
    iconView.setPreserveRatio(true); // Keep original image ratio.
    Button iconButton = new Button(); // Create a button.
    iconButton.setGraphic(iconView); // Link button with image view.
    iconButton.setOnAction(e -> createInputDialogAndLoadFile());
    Label buttonLabel = new Label(label); // Label for button.
    vBox.getChildren().addAll(iconButton, buttonLabel); // Add button and label.
    return vBox;
  }

  private static HBox createSearchField() {
    HBox hBox = new HBox(); // Create a VBox.
    TextField searchField = new TextField(); // Create an input field.
    searchField.setPromptText("Search for User"); // Placeholder for text.
    Image searchIcon = new Image("images/Search.png"); // Import image.
    ImageView iconView = new ImageView(); // Create image view.
    iconView.setImage(searchIcon); // Link image view and image.
    iconView.setFitWidth(20); // Set image width.
    iconView.setPreserveRatio(true); // Keep original image ratio.
    Button searchButton = new Button(); // Create a button to search.
    searchButton.setGraphic(iconView); // Link button with image view.
    // Add search button and input field.
    hBox.getChildren().addAll(searchField, searchButton);
    return hBox;
  }

  private static VBox createDropdown(Set<String> users, String label) {
    VBox vBox = new VBox(); // Create a VBox.
    Label dropdownLabel = new Label(label); // Create a label for drop-down.
    ComboBox<String> dropdown = new ComboBox<String>(); // Create a drop-down.
    dropdown.getItems().addAll(users); // Add items to the drop-down.
    // Add label and drop-down.
    vBox.getChildren().addAll(dropdownLabel, dropdown);
    return vBox;
  }
  
  private static void setCentralUserFromDropdown(ComboBox<String> dropdown) {
    String chosenUser = dropdown.getValue();
    System.out.println(chosenUser);
    try {
      buddENetwork.setCentralUser(chosenUser);
      drawGraph();
    } catch (Exception e) {
    }
  }

  private static HBox createInputField(String buttonLabel, int spacing) {
    HBox hBox = new HBox();
    Button button = new Button(buttonLabel);
    TextField inputField = new TextField();
    inputField.setPromptText("Please enter a name");
    hBox.getChildren().addAll(button, inputField);
    hBox.setSpacing(spacing);
    return hBox;
  }

  private static void createInputDialogAndLoadFile() {
    TextInputDialog dialog = new TextInputDialog("Please enter filename:");
    dialog.setHeaderText("Provide valid file to load your social network.");
    dialog.showAndWait();
    currentFilename = dialog.getEditor().getText();
    loadSocialNetwork();
    updateDropdownOfAllUsers();
    drawGraph();
  }

  private static void loadSocialNetwork() {
    try {
      buddENetwork.loadFromFile("data-files/" + currentFilename);
    } catch (IOException e) {
      Alert alert = new Alert(AlertType.WARNING,
          currentFilename + " does not exist in the directory.");
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
    ComboBox<String> dropdown = new ComboBox<String>(); // Create a drop-down.
    dropdown.getItems().addAll(users); // Add items to the drop-down.
    allUsersDropdownVBox.getChildren().add(dropdown);
    
    dropdown.setOnAction(e -> setCentralUserFromDropdown(dropdown));
  }

  private static void drawGraph() {
    Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // Set text attributes
    gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    // Set stroke attributes
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);


    // Draw circles (vertices) to represent people and lines connecting the
    // central user and their friends
    gc.setFill(Color.RED);
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

          gc.setFill(Color.BLUE);
          gc.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2.0, RADIUS * 2.0);

          gc.setFill(Color.YELLOW);
          gc.fillText(friend.getName(), x - RADIUS + 5, y + 5);
          i++;
        }
        gc.setFill(Color.RED);
        gc.fillOval(centerX - RADIUS, centerY - RADIUS, RADIUS * 2.0,
            RADIUS * 2.0);
        gc.setFill(Color.YELLOW);
        gc.fillText(centralName, centerX - RADIUS + 5, centerY + 5);
      } catch (Exception e) {
      }
    }

    centerVBox.getChildren().clear();
    centerVBox.getChildren().add(canvas);
    // set background color of center pane
    centerVBox.setStyle("-fx-background-color: white");
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

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

}
