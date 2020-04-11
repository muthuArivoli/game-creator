package ooga.Board;

import java.lang.reflect.Field;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameBoard extends Pane {
  private static final int BOARD_WIDTH = 1000;
  private static final int BOARD_HEIGHT = 680;

  private double tileWidth;
  private double tileHeight;

  public GameBoard(int numRowTiles, int numColTiles, List<String> colors){
    tileWidth = BOARD_WIDTH/numRowTiles;
    tileHeight = BOARD_HEIGHT/numColTiles;

    createTiles(numRowTiles, numColTiles, colors);
  }

  private void createTiles(int numRow, int numCol, List<String> colors){
    for (int x = 0; x < numRow; x++){
      for (int y= 0; y < numCol; y++){
        String colorString = colors.get(0);
        if ((x+y) % 2 == 0){colors.get(1);}
        Color color;
        try {
          Field field = Class.forName("java.awt.Color").getField(colorString);
          color = (Color)field.get(null);
        } catch (Exception e) {
          color = null; // Not defined
        }
        Tile tile = new Tile(tileWidth, tileHeight, x, y, color);
      }
    }
  }

}
