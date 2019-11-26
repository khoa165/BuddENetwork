package application;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Khoa Thien Le (Harry), Shannon Stiles, Kenneth Mui, Saniya Khullar.
 *
 */
public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final int WINDOW_WIDTH = 300;
  private static final int WINDOW_HEIGHT = 200;
  private static final String APP_TITLE = "Hello World!";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    args = this.getParameters().getRaw();

    // Create a vertical box with Hello labels for each args
    VBox vbox = new VBox();

    // Creates a canvas that can draw shapes and text
    Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
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

    vbox.getChildren().add(canvas);

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Add the vertical box to the center of the root pane
    root.setCenter(vbox);
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
