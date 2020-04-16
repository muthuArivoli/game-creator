package ooga.game_view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.game_view.board.GameBoard;

public class GameGuiController extends Application {

  private static final String STYLESHEET = "ooga/resources/styleSheets/default.css";
  private static final String PIECES_DIRECTORY = "src/ooga/resources/images/pieces";
  private static final String LANGUAGES_PACKAGE = "ooga.resources.languages.";
  private static final String GAME_DIRECTORY = "data/gameFiles";
  private static final String GAME_FILE_EXTENSIONS = "*.json";
  private static final double FRAMES_PER_SECOND = 60;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private static final double SCENE_WIDTH = 1280;
  private static final double SCENE_HEIGHT = 720;
  private static final Color ALL_COLOR = Color.WHITE;

  private static String currentLanguage = "English";
  private static String guiLanguage = "English";
  private static ResourceBundle myResources = ResourceBundle.getBundle(LANGUAGES_PACKAGE + currentLanguage);
  private static FileSelect gameFile = new FileSelect(GAME_FILE_EXTENSIONS, GAME_DIRECTORY, myResources.getString("FileType"), LANGUAGES_PACKAGE + currentLanguage);

  private BorderPane root;
  private Stage myStage;
  private Timeline animation;
  private GameBoard gameDisplay;
  private GUIButtons buttons;
  private VBox buttonGroup;

  private GameController myGameController;

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
    setBorderPane();
    initiateGameController();
    addGameButtons();
    addGameBoardDisplay();
    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add(STYLESHEET);
    myStage.setScene(scene);
    myStage.show();
  }

  private void setBorderPane() {
    root = new BorderPane();
    root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
    root.setMaxWidth(SCENE_WIDTH);
    root.setMaxHeight(SCENE_HEIGHT);
  }

  private void initiateGameController(){
    myGameController = new GameController();
  }

  private void addGameButtons() throws FileNotFoundException {
    buttons = new GUIButtons(LANGUAGES_PACKAGE + guiLanguage);
    buttonGroup = buttons.getVBox();
    root.setLeft(buttonGroup);
    root.setAlignment(buttonGroup, Pos.BOTTOM_LEFT);
  }

  private void addGameBoardDisplay(){
    ArrayList<Color> colors = new ArrayList<>();
    colors.add(Color.WHITE);
    colors.add(Color.WHITE);
    gameDisplay = new GameBoard(6,7,colors);
    root.setRight(gameDisplay);
  }

  private void startAnimationLoop() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      try {
        step();
      } catch (FileNotFoundException ex) {
      }
    });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void step() throws FileNotFoundException {
    changeLanguage(buttons.getLanguageStatus());
    checkNewGame(buttons.getNewGameStatus());
  }

  private void changeLanguage(String language) throws FileNotFoundException {
    guiLanguage = myResources.getString(language);
    if (!guiLanguage.contains(currentLanguage)) {
      currentLanguage = guiLanguage;
      myResources = ResourceBundle.getBundle(LANGUAGES_PACKAGE + currentLanguage);
      gameFile = new FileSelect(GAME_FILE_EXTENSIONS, GAME_DIRECTORY, myResources.getString("FileType"), LANGUAGES_PACKAGE + currentLanguage);
      buttonGroup.getChildren().clear();
      root.getChildren().remove(buttonGroup);
      addGameButtons();
    }
  }

  private void checkNewGame(boolean newGamePressed){
    if (newGamePressed){
      File dataFile = gameFile.getFileChooser().showOpenDialog(myStage);
      buttons.setNewGamePressedOff();
      if (dataFile == null) { return; }
      else {
        myGameController.parseFile(dataFile.getPath());
      }
    }
  }

}
