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
import ooga.models.GridModel;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Rect;

class GridModelTest {
  private GameController myGameController;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    List<Color> gameColors = new ArrayList<Color>(
        Arrays.asList(Color.WHITE, Color.BLACK, Color.RED, Color.CYAN));
    myGameController = new GameController();
    myGameController.parseFile("C:/Users/Okechukwu/Desktop/College/COMPSCI 308/final_team09/data/gameFiles/chess.json");
  }

  @Test


  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}