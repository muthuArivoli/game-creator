package ooga.tests.UITests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.controller.GameController;
import ooga.game_view.board.GameBoard;
import ooga.game_view.board.availableShapes.ComponentShape;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Rect;

class GameBoardTest {
  private GameController myGameController;
  private GameBoard gameDisplay;
  private ArrayList<Color> colors;
  private String lightStyleSheet = "ooga/resources/styleSheets/lightMode.css";
  private String darkStyleSheet = "ooga/resources/styleSheets/darkMode.css";


  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    List<Color> gameColors = new ArrayList<Color>(
        Arrays.asList(Color.WHITE, Color.BLACK, Color.RED, Color.CYAN));
    gameDisplay = new GameBoard(gameColors);
    myGameController = new GameController();
    myGameController.parseFile("C:/Users/Okechukwu/Desktop/College/COMPSCI 308/final_team09/data/gameFiles/chess.json");
  }

  @Test
  void correctGameLoaded(){
    assertEquals("CHESS", myGameController.getGameName().toUpperCase());
  }

  @Test
  void numberOfTilesCreated(){
    gameDisplay.createGameBoard(myGameController, lightStyleSheet, 1280,720);

    int numOfRows = myGameController.getGridModel().getGrid().length;
    int numOfCol = myGameController.getGridModel().getGrid()[0].length;

    StackPane board = (StackPane) gameDisplay.getCenter();
    Group tilesCreated = (Group) board.getChildren().get(0);
    assertEquals(numOfRows*numOfCol,tilesCreated.getChildren().size());
  }

  @Test
  void displaySizeOfComponents(){
    gameDisplay.createGameBoard(myGameController, lightStyleSheet, 1280,720);
    //display Size of actual board
    double boardLength = 720 - (720*0.14);
    StackPane board = (StackPane) gameDisplay.getCenter();
    Group tilesCreated = (Group) board.getChildren().get(0);
    ComponentShape stack = (ComponentShape) tilesCreated.getChildren().get(0);
    Rectangle tile = (Rectangle) stack.getChildren().get(0);
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