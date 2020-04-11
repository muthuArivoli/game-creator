package ooga.game_view.Board;

import java.lang.reflect.Field;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameBoard extends Pane {
  private static final int BOARD_WIDTH = 800;
  private static final int BOARD_HEIGHT = 660;

  private double tileWidth;
  private double tileHeight;
  private Group tileGroup = new Group();

  public GameBoard(int numRowTiles, int numColTiles, List<Color> colors){
    tileWidth = BOARD_WIDTH/numRowTiles;
    tileHeight = BOARD_HEIGHT/numColTiles;

    createTiles(numRowTiles, numColTiles, colors);
    this.getChildren().addAll(tileGroup);
    this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(20.0))));
  }

  private void createTiles(int numRow, int numCol, List<Color> colors){
    for (int x = 0; x < numRow; x++){
      for (int y= 0; y < numCol; y++){
        Color main = colors.get(0);
        if ((x+y) % 2 == 0){main = colors.get(1);}
        Tile tile = new Tile(tileWidth, tileHeight, x, y, main);
        tileGroup.getChildren().addAll(tile);
      }
    }
  }

}
