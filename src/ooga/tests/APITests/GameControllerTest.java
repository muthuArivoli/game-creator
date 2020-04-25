package ooga.tests.APITests;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
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
import ooga.goals.Goal;
import ooga.goals.enemyPiecesLeft;
import ooga.models.GameModel;
import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Rect;

class GameControllerTest {
  private GameController myGameController;
  private GridModel myGridModel;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    myGameController = new GameController();
    myGameController.parseFile("./data/gameFiles/chess.json");
    myGridModel = myGameController.getGridModel();
  }

  @Test
  void validateDimensions () {
    assertEquals(8, myGridModel.getDimensions().getCol());
    assertEquals(8, myGridModel.getDimensions().getRow());
  }

  @Test
  void invalidClicks () {
    myGameController.handleClick(7,5);
    myGameController.handleClick(6,4);
    myGameController.handleClick(0,8);
    myGameController.handleClick(308,308);
    assertNull(myGameController.getSelectedPiecePosition());
  }

  @Test
  void testValidGoals () {
    GameModel myGameModel = myGameController.getGameModel();
    List<Goal> myGoals = myGameModel.getGoals();
    for (Goal g: myGoals) {
      assertThat(g.getClass().toString(), anyOf(containsString("enemyPiecesLeft"), containsString("pieceTaken")));
    }
  }
  @Test
  void checkGameWinner() {
    // after capturing the player 2 king with the queen, assert that player 1 is declared winner
    myGameController.toggleAI();
    myGameController.handleClick(6, 2);
    myGameController.handleClick(4, 2);
    myGameController.handleClick(1,3);
    myGameController.handleClick(3,3);
    myGameController.handleClick(7,3);
    myGameController.handleClick(4,0);
    myGameController.handleClick(1,7);
    myGameController.handleClick(2,7);
    myGameController.handleClick(4,0);
    myGameController.handleClick(0,4);

    assertEquals(myGameController.checkGameEnd(), 1);
  }


  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}