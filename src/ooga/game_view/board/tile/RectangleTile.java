package ooga.game_view.board.tile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ooga.controller.GameController;

public class RectangleTile extends Rectangle {

  public RectangleTile(GameController gameController, double tileWidth, double tileHeight, int xCoordinate, int yCoordinate, Color clr){
    this.setWidth(tileHeight);
    this.setHeight(tileWidth);
    relocate(xCoordinate*tileHeight, yCoordinate*tileWidth);
    this.setFill(clr);
    this.setStroke(Color.BLACK);

    this.setOnMouseClicked(e->{
      gameController.handleClick(yCoordinate, xCoordinate);
    });
  }
}

