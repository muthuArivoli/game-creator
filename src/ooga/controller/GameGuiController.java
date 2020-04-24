package ooga.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.game_view.FileSelect;
import ooga.game_view.GUIButtons;
import ooga.game_view.board.GameBoard;

public class GameGuiController extends Application {
  private static final String LIGHT_STYLESHEET = "ooga/resources/styleSheets/lightMode.css";
  private static final String DARK_STYLESHEET = "ooga/resources/styleSheets/darkMode.css";
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
  private List<Color> gameColors = new ArrayList<Color>(
      Arrays.asList(Color.WHITE, Color.BLACK, Color.RED, Color.CYAN));
  private List<String> availableShapes = new ArrayList<>(Arrays.asList("Rectangular", "Circular"));
  private List<String> componentShapes = new ArrayList<>(Arrays.asList("Rectangular", "Circular"));
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
    gameDisplay = new GameBoard(gameColors);
    BorderPane.setAlignment(gameDisplay, Pos.CENTER);
    root.setRight(gameDisplay);
  }

  private void startAnimationLoop() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      try {
        step();
      } catch (Exception ex) {
        //Do Nothing
      }
    });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void step() {
    changeLanguage(buttons.getLanguageStatus());
    checkNewGame(buttons.getNewGameStatus());
    checkSettings(buttons.getSettingsStatus());
    checkRestartGame(buttons.getRestartGameStatus());

    if(myGameController.isChanged()) {
      gameDisplay.updateDisplay();
      myGameController.setChanged(false);
      int gameWinner = myGameController.checkGameEnd();
      if (gameWinner != 0) {
        System.out.println("Winner");
        Text winMsg = new Text();
        winMsg.setFont(new Font(20));
        winMsg.setText("Player " + (gameWinner == 1 ? "1" : "2") + "wins!");

        final Stage winModal = new Stage();
        VBox rt = new VBox(20);
        rt.setAlignment(Pos.CENTER);
        rt.getChildren().add(winMsg);
        Scene dialogScene = new Scene(rt, 300, 200);
        winModal.setScene(dialogScene);
        winModal.show();
      }
    }
  }

  private void changeLanguage(String language) {
    guiLanguage = myResources.getString(language);
    if (!guiLanguage.contains(currentLanguage)) {
      try{
        currentLanguage = guiLanguage;
        myResources = ResourceBundle.getBundle(LANGUAGES_PACKAGE + currentLanguage);
        gameFile = new FileSelect(GAME_FILE_EXTENSIONS, GAME_DIRECTORY, myResources.getString("FileType"), LANGUAGES_PACKAGE + currentLanguage);
        buttonGroup.getChildren().clear();
        root.getChildren().remove(buttonGroup);
        gameDisplay.getPieceDisplayBox().addMainButton(myResources.getString("ExtraPiecesButton"));
        addGameButtons();
      }catch(NullPointerException | FileNotFoundException e ){
        checkNewGame(true);
        currentLanguage = "English";
        changeLanguage(buttons.getLanguageStatus());
      }
    }
  }

  private void checkNewGame(boolean newGamePressed){
    if (newGamePressed){
      File tempFile = gameFile.getFileChooser().showOpenDialog(myStage);
      buttons.setNewGamePressedOff();
      startGame(tempFile);
    }
  }

  private void checkRestartGame(boolean restartStatus){
    if(restartStatus){
      buttons.setRestartGamePressedOff();
      startGame(currentDataFile);
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
      gameDisplay.createGameBoard(myGameController, currentStyleSheet, scene_width, scene_height);
      gameDisplay.getPieceDisplayBox().addMainButton(myResources.getString("ExtraPiecesButton"));
      titleBox.getChildren().add(gameTitle);
    }
  }

  private void checkSettings(boolean settingsStatus){
    if (settingsStatus){
      buttons.setSettingsPressedOff();
      VBox rt = new VBox();
      rt.setAlignment(Pos.CENTER);
      rt.setSpacing(20);
      Stage settingsStage = createNewStage(rt, "Settings", 400, 300);
      Button darkMode = createButton(myResources.getString("DarkSetting"), event -> changeLightTheme(settingsStage.getScene()));
      Button playerMode = createButton(myResources.getString(myGameController.isAiEnabled() ? "DisableAi" : "EnableAi"), event -> toggleAI());
      Button changeTile = createButton(myResources.getString("ChangeColor"), event -> changeColor(settingsStage));
      Button changePiece = createButton(myResources.getString("ChangeShape"), event -> changeShape(settingsStage));
      rt.getChildren().addAll(darkMode, playerMode, changeTile, changePiece);
      settingsStage.show();
    }
  }

  private void toggleAI () {
    myGameController.toggleAI();
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
    try{
      gameDisplay.getPieceDisplayBox().updateStyleSheet(currentStyleSheet);
    }
    catch (NullPointerException e){
      //do Nothing
    }
  }

  private void changeColor(Stage settings){
    settings.close();
    VBox rt = new VBox();
    rt.setSpacing(10);
    rt.setAlignment(Pos.CENTER);
    Stage ColorStage = createNewStage(rt, "ChangeColor", 300, 200);
    rt.getChildren().addAll(createButton("TileColor 1", event -> chooseColor(0, rt)),
        createButton("TileColor 2", event -> chooseColor(1, rt)),
        createButton("UserPieceColor", event -> chooseColor(2, rt)),
        createButton("ComputerPieceColor", event -> chooseColor(3, rt)));
    ColorStage.show();
  }

  private void chooseColor(int index, VBox root){
    if (root.getChildren().size() == 5) {root.getChildren().remove(4);}
    ColorPicker cp = new ColorPicker();
    cp.setValue(gameColors.get(index));
    cp.setOnAction(new EventHandler() {
      public void handle(Event t) {
        gameColors.set(index, cp.getValue());
        gameDisplay.updateComponents(gameColors, componentShapes);
      }
    });
    root.getChildren().add(cp);
  }

  private void changeShape(Stage settings){
    settings.close();
    VBox rt = new VBox();
    rt.setSpacing(10);
    rt.setAlignment(Pos.CENTER);
    Stage ShapeStage = createNewStage(rt, "ChangeShape", 300, 200);
    rt.getChildren().addAll(createButton("Tile Shape", event -> chooseShape(0, rt)),
        createButton("Piece Shape", event -> chooseShape(1, rt)));
    ShapeStage.show();
  }

  private void chooseShape(int index, VBox root){
    if (root.getChildren().size() == 3) {root.getChildren().remove(2);}
    ComboBox tempMenu = new ComboBox();
    tempMenu.getItems().addAll(availableShapes);
    tempMenu.setOnAction(new EventHandler() {
      public void handle(Event t) {
        componentShapes.set(index, (String) tempMenu.getValue());
        gameDisplay.updateComponents(gameColors, componentShapes);
      }
    });
    root.getChildren().add(tempMenu);
  }

  private Button createButton(String buttonName, EventHandler event){
    Button temp = new Button(buttonName);
    temp.setOnAction(event);
    return temp;
  }

  private Stage createNewStage(Pane rt, String title, double width, double height){
    Stage s = new Stage();
    s.setTitle(myResources.getString(title));
    Scene temporaryScene = new Scene(rt,width,height);
    temporaryScene.getStylesheets().add(currentStyleSheet);
    s.setScene(temporaryScene);
    return s;
  }
}
