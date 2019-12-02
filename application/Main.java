//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Main.java
// Files: Main.java   (A-Team 12 Milestone 2)
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

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar.
 *
 */
public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 700;
  private static final int CANVAS_WIDTH = 400;
  private static final int CANVAS_HEIGHT = 200;
  private static final String APP_TITLE = "BuddE Network";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    args = this.getParameters().getRaw();

    // Create a vertical box with Hello labels for each args
    VBox centerVBox = new VBox();

    // Add interactive graph to center pane
    // Creates a canvas that can draw shapes and text
    Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // Write some text
    // Text is filled with the fill color
    gc.setFill(Color.GREEN);
    gc.setFont(new Font(30));
    gc.fillText("Hello World!", 70, 170);
    // Draw a line
    // Lines use the stroke color
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(2);
    gc.strokeLine(40, 100, 250, 50);
    // Draw a few circles
    gc.setFill(Color.BLACK);
    // The circles draw from the top left, so to center them, subtract the
    // radius from each coordinate
    gc.fillOval(40 - 15, 100 - 15, 30, 30);
    gc.setFill(Color.RED);
    gc.fillOval(250 - 15, 50 - 15, 30, 30);

    centerVBox.getChildren().add(canvas);
    // set background color of center pane
    centerVBox.setStyle("-fx-background-color: white");

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    
    // Add title to top of the root pane    
    root.setTop(new Label(APP_TITLE));
    
    // Add image to label to go in top pane
    Label logo = new Label();
    Image pic = new Image("buddENetworkLogo.png");
    ImageView seeLogo = new ImageView();
    seeLogo.setImage(pic);
    logo.setGraphic(seeLogo);
    //logoVBox.getChildren().addAll(seeLogo);
    //root.setLeft(logo);
    
    // new vbox
    VBox newVBox = new VBox();
    Image newPic = new Image("New.png");
    ImageView seeNew = new ImageView();
    seeNew.setImage(newPic);
    Button newIcon = new Button();
    newIcon.setGraphic(seeNew);
    Label newLabel = new Label("New");
    newVBox.getChildren().addAll(newIcon, newLabel);
    
    // open vbox
    VBox openVBox = new VBox();
    Image openPic = new Image("Open.png");
    ImageView seeOpen = new ImageView();
    seeOpen.setImage(openPic);
    Button open = new Button();
    open.setGraphic(seeOpen);
    Label openLabel = new Label("Open");
    openVBox.getChildren().addAll(open, openLabel);
    
    // undo vbox
    VBox undoVBox = new VBox();
    Image undoPic = new Image("Undo.png");
    ImageView seeUndo = new ImageView();
    seeUndo.setImage(undoPic);
    Button undo = new Button();
    undo.setGraphic(seeUndo);
    Label undoLabel = new Label("Undo");
    undoVBox.getChildren().addAll(undo, undoLabel);
    
    // redo vbox
    VBox redoVBox = new VBox();
    Image redoPic = new Image("Redo.png");
    ImageView seeRedo = new ImageView();
    seeRedo.setImage(redoPic);
    Button redo = new Button();
    redo.setGraphic(seeRedo);
    Label redoLabel = new Label("Redo");
    redoVBox.getChildren().addAll(redo, redoLabel);
    
    // save vbox
    VBox saveVBox = new VBox();
    Image savePic = new Image("Save.png");
    ImageView seeSave = new ImageView();
    seeSave.setImage(savePic);
    Button save = new Button();
    save.setGraphic(seeSave);
    Label saveLabel = new Label("Save");
    saveVBox.getChildren().addAll(save, saveLabel);
      
    // Add vbox for setting central user
    VBox setCentralUser = new VBox();
    Label set = new Label("Set Central User");
    ComboBox<String> userOptions = new ComboBox<String>();
    userOptions.getItems().addAll("Harry", "Kenny", "Saniya", "Shannon");
    setCentralUser.getChildren().addAll(set, userOptions);
    
    // create tool bar of functions for top pane
    ToolBar toolBar = new ToolBar(
    	     newVBox,
    	     openVBox,
    	     new Separator(),
    	     undoVBox,
    	     redoVBox,
    	     new Separator(),
    	     saveVBox,
    	     new Separator(),
    	     setCentralUser
    	 );
    
    // Add hbox with vboxes in it to top pane
    HBox topHBox = new HBox();
    topHBox.getChildren().addAll(logo, toolBar);
    topHBox.setSpacing(10);
    //topHBox.setStyle("-fx-background-color: blue");

    // set top pane
    root.setTop(topHBox);

    // Add the vertical box to the center of the root pane
    root.setCenter(centerVBox);
    
    // TODO Do we want to add anything in the left pane?
    // Add ComboBox to left pane
//    ComboBox<String> comBox = new ComboBox<String>();
//    comBox.getItems().addAll("Harry", "Kenny", "Saniya", "Shannon");
//    root.setLeft(comBox);
    
    // create separator between user and buddE functions
    Separator separator1 = new Separator();

    // Title for BuddEs section
    Label userSettingsTitle = new Label("User Settings");
    userSettingsTitle.setFont(Font.font (16));
    userSettingsTitle.setTextFill(Color.BLUE);
    
    // create hbox for add new user
    HBox addUserHBox = new HBox();
    Button newUser = new Button("Create New User");
    TextField newUserName = new TextField();
    newUserName.setPromptText("Please Enter a Name");
    addUserHBox.getChildren().addAll(newUser, newUserName);
    addUserHBox.setSpacing(10);

    // create hbox for removing user
    HBox removeUserHBox = new HBox();
    Button removeUser = new Button("Remove User");
    // List<String> allUsersList = List.of("Harry", "Kenny", "Saniya",
    // "Shannon");
    // ComboBox<String> removeUserName = new
    // ComboBox<String>(FXCollections.observableList(allUsersList));
    TextField removeUserName = new TextField();
    removeUserName.setPromptText("Please Enter a Name");
    removeUserHBox.getChildren().addAll(removeUser, removeUserName);
    removeUserHBox.setSpacing(29);

    // create vbox for user functions
    VBox userVBox = new VBox();
    userVBox.getChildren().addAll(userSettingsTitle, addUserHBox, removeUserHBox);
    userVBox.setSpacing(10);

    // create separator between user and buddE functions
    Separator separator2 = new Separator();
    // separator1.setMaxWidth(150);
    
    // Title for BuddEs section
    Label buddESettingsTitle = new Label("BuddE Settings");
    buddESettingsTitle.setFont(Font.font (16));
    buddESettingsTitle.setTextFill(Color.BLUE);

    // create hbox for adding BuddE connection between central user and
    // other user
    HBox addBuddEHBox = new HBox();
    // Label addBuddECentralUser = new Label("Harry");
    Button addBuddE = new Button("Add BuddE");
    TextField addBuddEOtherUser = new TextField();
    addBuddEOtherUser.setPromptText("Please Enter a Name");
    addBuddEHBox.getChildren().addAll(addBuddE, addBuddEOtherUser);
    addBuddEHBox.setSpacing(31);

    // create hbox for removing BuddE connection between central user and
    // other user
    HBox removeBuddEHBox = new HBox();
    // Label removeBuddECentralUser = new Label("Harry");
    Button removeBuddE = new Button("Remove BuddE");
    TextField removeBuddEOtherUser = new TextField();
    removeBuddEOtherUser.setPromptText("Please Enter a Name");
    removeBuddEHBox.getChildren().addAll(removeBuddE, removeBuddEOtherUser);
    removeBuddEHBox.setSpacing(10);

    // create vbox for BuddE functions
    VBox buddEVBox = new VBox();
    buddEVBox.getChildren().addAll(buddESettingsTitle, addBuddEHBox, removeBuddEHBox);
    buddEVBox.setSpacing(10);


    // create separator between buddE functions and mutual BuddEs/friends
    Separator separator3 = new Separator();
    
    // Title for Mutual BuddEs section
    Label mutualBuddEsTitle = new Label("Mutual BuddEs");
    mutualBuddEsTitle.setFont(Font.font (16));
    mutualBuddEsTitle.setTextFill(Color.BLUE);
    
    // create hbox with button and text field for Mutual BuddEs section
    HBox mutualBuddEsHBox = new HBox();
    // Label removeBuddECentralUser = new Label("Harry");
    Button findMutualBuddEs = new Button("Find Mutual BuddEs");
    TextField otherUserName = new TextField();
    otherUserName.setPromptText("Please Enter a Name");
    mutualBuddEsHBox.getChildren().addAll(findMutualBuddEs, otherUserName);
    mutualBuddEsHBox.setSpacing(10);
    
    
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
    VBox rightVBox = new VBox();
    rightVBox.getChildren().addAll(separator1, userVBox, separator2, buddEVBox, separator3,
        mutualBuddEsTitle, mutualBuddEsHBox, mutualBuddEsVBox, separator4);
    rightVBox.setSpacing(10);

    // set right pane
    root.setRight(rightVBox);


    // Add done button to bottom pane
    HBox statusHBox = new HBox();
    Label status = new Label("STATUS: ");
    status.setFont(Font.font ("Calibri", FontWeight.BOLD, 24));
    status.setTextFill(Color.BLUE);
    TextField statusMessage = new TextField();
    statusHBox.getChildren().addAll(status, statusMessage);
    //statusHBox.setStyle("-fx-background-color: blue");
    
    root.setBottom(statusHBox);
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.getIcons().add(new Image("buddENetworkIcon.png"));
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Please note that this class allows us to view the list of mutual BuddEs, between the central user
   * and one of his/her buddEs.
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

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

}
