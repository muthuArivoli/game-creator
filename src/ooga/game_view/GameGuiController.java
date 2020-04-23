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
import javafx.geometry.Rectangle2D;
import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.game_view.board.GameBoard;
import ooga.models.GridModel;
import ooga.piece.Coordinate;

public class GameGuiController extends Application {
  private static final String LIGHT_STYLESHEET = "ooga/resources/styleSheets/lightMode.css";
  private static final String DARK_STYLESHEET = "ooga/resources/styleSheets/darkMode.css";
  private static final String PIECES_DIRECTORY = "src/ooga/resources/images/pieces";
  private static final String LANGUAGES_PACKAGE = "ooga.resources.languages.";
  private static final String GAME_DIRECTORY = "data/gameFiles";
  private static final String GAME_FILE_EXTENSIONS = "*.json";
  private static final double FRAMES_PER_SECOND = 60;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
  private double scene_width = primaryScreenBounds.getWidth()*0.9;
  private double scene_height = primaryScreenBounds.getHeight()*0.9;

  private static String currentLanguage = "English";
  private static String guiLanguage = "English";
  private static ResourceBundle myResources = ResourceBundle.getBundle(LANGUAGES_PACKAGE + currentLanguage);
  private static FileSelect gameFile = new FileSelect(GAME_FILE_EXTENSIONS, GAME_DIRECTORY, myResources.getString("FileType"), LANGUAGES_PACKAGE + currentLanguage);
  private File currentDataFile;

  private BorderPane root;
  private Stage myStage;
  private Scene myScene;
  private Timeline animation;

  private GameController myGameController;
  private GameBoard gameDisplay;
  private BorderPane controlDisplay = new BorderPane();
  private GUIButtons buttons;
  private VBox buttonGroup;
  private Text gameTitle;
  private VBox titleBox = new VBox();

  private boolean darkEnabled = false;

  private String currentStyleSheet = LIGHT_STYLESHEET;

  /**
   * Empty Constructor needed to run the application due to Application requirements
   */
  public GameGuiController() {
  }

  /**
   * Constructor used in Main to begin the program Begins our JavaFX application Starts the
   * Animation Loop and sets the Border Pane, filling it with ....
   * @param args is the String[] passed in by main
   */
  public GameGuiController(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Game Engine");
    myStage = primaryStage;
    myStage.setX(primaryScreenBounds.getMinX());
    myStage.setY(primaryScreenBounds.getMinY());
    myStage.setWidth(primaryScreenBounds.getWidth());
    myStage.setHeight(primaryScreenBounds.getHeight());
    setBorderPane();
    startAnimationLoop();
    initiateGameController();
    addTitleBox();
    addGameButtons();
    addGameBoardDisplay();
    myScene = new Scene(root, scene_width, scene_height);
    myScene.getStylesheets().add(currentStyleSheet);
    myStage.setScene(myScene);
    myStage.show();
    //startGame(new File("./data/gameFiles/chess.json"));
    //startGame(new File("./data/gameFiles/connect4.json"));
  }

  private void setBorderPane() {
    root = new BorderPane();
    root.setMaxWidth(scene_width);
    root.setMaxHeight(scene_height);
  }

  private void initiateGameController(){
    myGameController = new GameController();
  }

  private void addGameButtons() throws FileNotFoundException {
    buttons = new GUIButtons(LANGUAGES_PACKAGE + guiLanguage);
    buttonGroup = buttons.getVBox();
    controlDisplay.setCenter(buttonGroup);
    controlDisplay.setMaxHeight(scene_height);
    BorderPane.setAlignment(controlDisplay, Pos.CENTER);
    root.setPadding(new Insets(0, 0 , 0, 30));
    root.setLeft(controlDisplay);
  }

  private void addTitleBox(){
    titleBox.getStyleClass().addAll("titleBox");
    BorderPane.setAlignment(titleBox, Pos.CENTER);
    controlDisplay.setTop(titleBox);
  }

  private void addGameBoardDisplay(){
    gameDisplay = new GameBoard();
    BorderPane.setAlignment(gameDisplay, Pos.CENTER);
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
    checkSettings(buttons.getSettingsStatus());
    checkRestartGame(buttons.getRestartGameStatus());

    if(myGameController.isChanged()) {
      gameDisplay.updateDisplay();
      myGameController.setChanged(false);
    }
  }

  private void changeLanguage(String language) throws FileNotFoundException {
    guiLanguage = myResources.getString(language);
    if (!guiLanguage.contains(currentLanguage)) {
      currentLanguage = guiLanguage;
      myResources = ResourceBundle.getBundle(LANGUAGES_PACKAGE + currentLanguage);
      gameFile = new FileSelect(GAME_FILE_EXTENSIONS, GAME_DIRECTORY, myResources.getString("FileType"), LANGUAGES_PACKAGE + currentLanguage);
      buttonGroup.getChildren().clear();
      root.getChildren().remove(buttonGroup);
      gameDisplay.getPieceDisplayBox().addMainButton(myResources.getString("ExtraPiecesButton"));
      addGameButtons();
    }
  }

  private void checkNewGame(boolean newGamePressed){
    if (newGamePressed){
      File tempFile = gameFile.getFileChooser().showOpenDialog(myStage);
      buttons.setNewGamePressedOff();
      startGame(tempFile);
    }
  }

  private void startGame(File temporaryFile){
    if (temporaryFile == null) { return; }
    else {
      currentDataFile = temporaryFile;
      gameDisplay.getChildren().removeAll();
      titleBox.getChildren().remove(gameTitle);
      myGameController = new GameController();
      myGameController.parseFile(currentDataFile.getPath());
      gameTitle = new Text(myGameController.getGameName().toUpperCase());
      ArrayList<Color> colors = new ArrayList<>();
      colors.add(Color.WHITE);
      colors.add(Color.BLACK);
      gameDisplay.createGameBoard(myGameController, currentStyleSheet, colors, scene_width, scene_height);
      gameDisplay.getPieceDisplayBox().addMainButton(myResources.getString("ExtraPiecesButton"));
      titleBox.getChildren().add(gameTitle);
    }
  }

  private void checkSettings(boolean settingsStatus){
    if (settingsStatus){
      buttons.setSettingsPressedOff();
      FlowPane rt = new FlowPane();
      rt.setAlignment(Pos.CENTER);
      rt.setVgap(20);
      Stage newStage = createNewStage(rt, "Settings", 250, 250);
      Button darkMode = new Button(myResources.getString("DarkSetting"));
      darkMode.setOnAction(event -> changeLightTheme(newStage.getScene()));
      rt.getChildren().addAll(darkMode);
      newStage.show();
    }
  }

  private void checkRestartGame(boolean restartStatus){
    if(restartStatus){
      buttons.setRestartGamePressedOff();
      startGame(currentDataFile);
    }
  }

  private Stage createNewStage(Pane rt, String title, double width, double height){
    Stage s = new Stage();
    s.setTitle(myResources.getString(title));
    Scene temporaryScene = new Scene(rt,width,height);
    temporaryScene.getStylesheets().add(currentStyleSheet);
    s.setScene(temporaryScene);
    return s;
  }

  private void changeLightTheme(Scene scene){
    darkEnabled = !darkEnabled;
    scene.getStylesheets().removeAll(currentStyleSheet);
    myScene.getStylesheets().removeAll(currentStyleSheet);
    currentStyleSheet = LIGHT_STYLESHEET;
    if(darkEnabled) {
      currentStyleSheet = DARK_STYLESHEET;
    }
    scene.getStylesheets().add(currentStyleSheet);
    myScene.getStylesheets().add(currentStyleSheet);
    gameDisplay.getPieceDisplayBox().updateStyleSheet(currentStyleSheet);
  }
}
