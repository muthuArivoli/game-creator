package ooga.game_view.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

  public Tile (double tileWidth, double tileHeight, int xCoordinate, int yCoordinate, Color clr){
    this.setWidth(tileWidth);
    this.setHeight(tileHeight);
    relocate(xCoordinate*tileWidth, yCoordinate*tileHeight);
    this.setFill(clr);
  }
}
