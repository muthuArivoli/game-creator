package ooga.game_view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.game_view.board.GameBoard;
import org.junit.jupiter.api.Test;

class UITest {
  private GameController myGameController;
  private GameBoard gameDisplay;
  private ArrayList<Color> colors;
  private String lightStyleSheet = "ooga/resources/styleSheets/lightMode.css";
  private String darkStyleSheet = "ooga/resources/styleSheets/darkMode.css";


  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    gameDisplay = new GameBoard();
    myGameController = new GameController();
    myGameController.parseFile("C:/Users/Okechukwu/Desktop/College/COMPSCI 308/final_team09/data/gameFiles/chess.json");
    colors = new ArrayList<>();
    colors.add(Color.WHITE);
    colors.add(Color.BLACK);
  }

  @Test
  void correctGameLoaded(){
    assertEquals("CHESS", myGameController.getGameName().toUpperCase());
  }

  @Test
  void checkBackgroundColor(){
    gameDisplay.getStylesheets().add(lightStyleSheet);
    gameDisplay.getStyleClass().add("GameBoard");


    assertEquals(Color.SILVER, gameDisplay.getBackground());
  }

  @Test
  void numberOfTilesCreated(){
    gameDisplay.createGameBoard(myGameController, lightStyleSheet, colors, 1280,720);

    int numOfRows = myGameController.getGridModel().getGrid().length;
    int numOfCol = myGameController.getGridModel().getGrid()[0].length;

    StackPane board = (StackPane) gameDisplay.getCenter();
    Group tilesCreated = (Group) board.getChildren().get(0);
    assertEquals(numOfRows*numOfCol,tilesCreated.getChildren().size());
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}