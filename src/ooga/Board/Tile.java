package ooga.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Tile extends Rectangle {

  public Tile (double tileWidth, double tileHeight, int xCoordinate, int yCoordinate, Color clr){
    relocate(xCoordinate*tileWidth, yCoordinate*tileHeight);
    setFill(clr);
  }
}
