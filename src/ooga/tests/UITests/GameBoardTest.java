package ooga.tests.UITests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ooga.controller.GameController;
import ooga.game_view.board.GameBoard;
import ooga.game_view.board.tile.RectangleTile;
import org.junit.jupiter.api.Test;

class GameBoardTest {
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
  void numberOfTilesCreated(){
    gameDisplay.createGameBoard(myGameController, lightStyleSheet, colors, 1280,720);

    int numOfRows = myGameController.getGridModel().getGrid().length;
    int numOfCol = myGameController.getGridModel().getGrid()[0].length;

    StackPane board = (StackPane) gameDisplay.getCenter();
    Group tilesCreated = (Group) board.getChildren().get(0);
    assertEquals(numOfRows*numOfCol,tilesCreated.getChildren().size());
  }

  @Test
  void displaySizeOfComponents(){
    gameDisplay.createGameBoard(myGameController, lightStyleSheet, colors, 1280,720);
    //display Size of actual board
    double boardLength = 720 - (720*0.14);
    StackPane board = (StackPane) gameDisplay.getCenter();
    Group tilesCreated = (Group) board.getChildren().get(0);
    RectangleTile tile = (RectangleTile) tilesCreated.getChildren().get(0);
    int numOfRows = myGameController.getGridModel().getGrid().length;
    int numOfCol = myGameController.getGridModel().getGrid()[0].length;
    assertEquals(boardLength, tile.getWidth()*numOfRows);
    assertEquals(boardLength, tile.getHeight()*numOfCol);
    //display size of pieceDisplay
    double displayWidth = 1280 * 0.80;
    double displayHeight = 720 * 0.14;
    assertEquals(displayWidth, gameDisplay.getPieceDisplayBox().getPrefWidth());
    assertEquals(displayHeight, gameDisplay.getPieceDisplayBox().getPrefHeight());
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}