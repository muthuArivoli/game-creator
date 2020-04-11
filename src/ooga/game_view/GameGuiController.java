package ooga.game_view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

public class GameGuiController extends Application {

  private static final String STYLESHEET = "ooga/resources/styleSheets/default.css";
  private static final String PIECES_DIRECTORY = "src/ooga/resources/images/pieces";
  private static final String LANGUAGES_PACKAGE = "ooga.resources.languages.";
  private static final String GAME_DIRECTORY = "ooga.resources.gameFiles";
  private static final String GAME_FILE_EXTENSIONS = "*.xml,*.json,*.csv";
  private static final double FRAMES_PER_SECOND = 30;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private static final double SCENE_WIDTH = 1280;
  private static final double SCENE_HEIGHT = 720;
  private static final Color ALL_COLOR = Color.WHITE;

  private static String startLanguage = "English";
  private static String guiLanguage = "English";
  private static ResourceBundle myResources = ResourceBundle.getBundle(LANGUAGES_PACKAGE + startLanguage);
  //private static FileSelect GameFile = new FileSelect(GAME_FILE_EXTENSIONS, GAME_DIRECTORY, myResources.getString("GameFile"), LANGUAGES_PACKAGE + startLanguage);

  private AnchorPane root;
  private Stage myStage;
  private Timeline animation;
  private GameBoard board;

  /**
   * Empty Constructor needed to run the application due to Application requirements
   */
  public GameGuiController() {
  }

  /**
   * Constructor used in Main to begin the program Begins our JavaFX application Starts the
   * Animation Loop and sets the Border Pane, filling it with a ButtonController, SliderController,
   * and TurtleHabitat, TerminalView, and VariablesTabPaneView Sets the stage and scene and shows
   * it
   *
   * @param args is the String[] passed in by main
   */
  public GameGuiController(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Game Engine");
    myStage = primaryStage;
    startAnimationLoop();
    setAnchorPane();
    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add(STYLESHEET);
    myStage.setScene(scene);
    myStage.show();
  }

  private void setAnchorPane() throws FileNotFoundException {
    root = new AnchorPane();
    root.setBackground(new Background(new BackgroundFill(ALL_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
    root.setMaxWidth(SCENE_WIDTH);
    root.setMaxHeight(SCENE_HEIGHT);
    addBoard();
  }

  private void addBoard() throws FileNotFoundException {
    board = new GameBoard("chessboard.png");
    root.setRightAnchor(board, 20.0);
    root.setTopAnchor(board, 20.0);
    root.setBottomAnchor(board, 20.0);
    GUIButtons buttons = new GUIButtons(LANGUAGES_PACKAGE + guiLanguage);
    root.getChildren().addAll(buttons.getVBox(), board);
  }

  private void startAnimationLoop() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      try {
        step(); }
      catch (IOException ex) {
        System.out.println("Help Text File Not Found."); } });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void step() throws IOException {

  }


}
