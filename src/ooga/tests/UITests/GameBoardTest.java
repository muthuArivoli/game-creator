package ooga.tests.UITests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

class GameBoardTest {
  private GameController myGameController;
  private GameBoard gameDisplay;
  private ArrayList<Color> colors;
  private String lightStyleSheet = "ooga/resources/styleSheets/lightMode.css";
  private String chess = "./data/gameFiles/chess.json";
  private String connect4 = "./data/gameFiles/connect4.json";
  private String tictactoe = "./data/gameFiles/tictactoe.json";
  private List<String> games = new ArrayList<>(Arrays.asList(chess,connect4,tictactoe));


  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    colors = new ArrayList<Color>(
        Arrays.asList(Color.WHITE, Color.BLACK, Color.RED, Color.CYAN));
    gameDisplay = new GameBoard(colors);
    myGameController = new GameController();
  }

  @Test
  void correctGameLoaded(){
    myGameController.parseFile(chess);
    assertEquals("CHESS", myGameController.getGameName().toUpperCase());
    myGameController.parseFile(connect4);
    assertEquals("CONNECT4", myGameController.getGameName().toUpperCase());
    myGameController.parseFile(tictactoe);
    assertEquals("TIC TAC TOE", myGameController.getGameName().toUpperCase());
  }

  @Test
  void numberOfTilesCreated(){
    for (String name : games){
      myGameController.parseFile(name);
      gameDisplay.createGameBoard(myGameController, lightStyleSheet, 1280,720);
      int numOfRows = myGameController.getGridModel().getGrid().length;
      int numOfCol = myGameController.getGridModel().getGrid()[0].length;
      StackPane board = (StackPane) gameDisplay.getCenter();
      Group tilesCreated = (Group) board.getChildren().get(0);
      assertEquals(numOfRows*numOfCol,tilesCreated.getChildren().size());
    }
  }

  @Test
  void displaySizeOfComponents(){
    myGameController.parseFile(chess);
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

  @Test
  void updateDisplayTest(){
    myGameController.parseFile(chess);
    List<String> shapes = new ArrayList<>(Arrays.asList("Rectangular","Circular"));
    gameDisplay.updateComponents(colors, shapes);
    assertNull((StackPane) gameDisplay.getCenter());

    gameDisplay.createGameBoard(myGameController, lightStyleSheet, 1280,720);
    assertNotNull((StackPane) gameDisplay.getCenter());
    
    List<Color> newColors = new ArrayList<Color>(Arrays.asList(Color.YELLOW, Color.MIDNIGHTBLUE, Color.GREEN, Color.CYAN));
    gameDisplay.updateComponents(newColors, shapes);
    StackPane board = (StackPane) gameDisplay.getCenter();
    Group tilesCreated = (Group) board.getChildren().get(0);
    Group piecesCreated = (Group) board.getChildren().get(1);
    Shape tile1 = (Shape) ((ComponentShape) tilesCreated.getChildren().get(0)).getChildren().get(0);
    Shape tile2 = (Shape) ((ComponentShape) tilesCreated.getChildren().get(1)).getChildren().get(0);
    Shape piece1 = (Shape) ((ComponentShape) piecesCreated.getChildren().get(0)).getChildren().get(0);
    Shape piece2 = (Shape) ((ComponentShape) piecesCreated.getChildren().get(piecesCreated.getChildren().size()-1)).getChildren().get(0);

    assertEquals(newColors.get(1), tile1.getFill());
    assertEquals(newColors.get(0), tile2.getFill());
    assertEquals(newColors.get(3), piece1.getFill());
    assertEquals(newColors.get(2), piece2.getFill());
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}