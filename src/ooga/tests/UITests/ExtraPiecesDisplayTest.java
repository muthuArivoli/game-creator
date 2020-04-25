package ooga.tests.UITests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import ooga.controller.GameController;
import ooga.game_view.board.ExtraPiecesDisplay;
import ooga.game_view.board.GameBoard;
import org.junit.jupiter.api.Test;

class ExtraPiecesDisplayTest {
  private GameController myGameController;
  private GameBoard gameDisplay;
  private String lightStyleSheet = "ooga/resources/styleSheets/lightMode.css";
  private String darkStyleSheet = "ooga/resources/styleSheets/darkMode.css";
  private ExtraPiecesDisplay pieceDisplayBox;

  @org.junit.jupiter.api.BeforeEach
  void setUp(){
    List<Color> colors = new ArrayList<Color>(
        Arrays.asList(Color.WHITE, Color.BLACK, Color.RED, Color.CYAN));
    gameDisplay = new GameBoard(colors);
    myGameController = new GameController();
    pieceDisplayBox = new ExtraPiecesDisplay(1000, 200, myGameController, lightStyleSheet);
  }

  @Test
  void addPieces() {
    assertEquals(0, pieceDisplayBox.getPieces().size());
    pieceDisplayBox.addPieces("Test String 1");
    pieceDisplayBox.addPieces("Test String 2");
    assertTrue(pieceDisplayBox.getPieces().size() == 2);
  }

  @Test
  void updateStyleSheet() {
    assertNotNull(pieceDisplayBox.getStyleClass().get(0));
    pieceDisplayBox.updateStyleSheet(darkStyleSheet);

    assertNotNull(pieceDisplayBox.getStyleClass().get(0));
    assertEquals("displayBox", pieceDisplayBox.getStyleClass().get(0));
  }

  @Test
  void addMainButton() {
    assertEquals(0, pieceDisplayBox.getChildren().size());
    // pieceDisplayBox.addMainButton("Test Button");
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {

  }
}