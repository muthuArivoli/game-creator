package ooga.tests.UITests;

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
  void testNoMovementClicks () {
    myGameController.handleClick(1,1);
    myGameController.handleClick(2,4);
    myGameController.handleClick(8,8);
    assertNull(myGameController.getSelectedPiecePosition());
  }

  

  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}