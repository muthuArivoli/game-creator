package ooga.game_view.board.tile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class CircleTile extends Ellipse {

  public CircleTile(double tileWidth, double tileHeight, int xCoordinate, int yCoordinate, Color clr){
    this.setRadiusX(tileWidth/2);
    this.setRadiusY(tileHeight/2);
    relocate(xCoordinate*tileWidth, yCoordinate*tileHeight);
    this.setFill(clr);
    this.setStroke(Color.BLACK);

    setOnMouseClicked(e -> {
      this.setFill(Color.BLUE);
    });
  }
}
