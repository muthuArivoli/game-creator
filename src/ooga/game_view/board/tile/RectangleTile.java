package ooga.game_view.board.tile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleTile extends Rectangle {

  public RectangleTile(double tileWidth, double tileHeight, int xCoordinate, int yCoordinate, Color clr){
    this.setWidth(tileWidth);
    this.setHeight(tileHeight);
    relocate(xCoordinate*tileWidth, yCoordinate*tileHeight);
    this.setFill(clr);
    this.setStroke(Color.BLACK);
  }
}

