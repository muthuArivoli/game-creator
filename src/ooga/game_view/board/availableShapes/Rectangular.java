package ooga.game_view.board.availableShapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.controller.GameController;

public class Rectangular extends ComponentShape {

  public Rectangular(GameController gameController, double tileX, double tileY, int row, int col, Color tileColor){
    super(gameController, tileX, tileY, row, col, tileColor);
  }

  @Override
  protected Shape createShape(double tileX, double tileY, boolean isPiece) {
    double radX = tileY;
    double radY = tileX;
    if (isPiece){
      radX = radX*0.9;
      radY = radY*0.9;
    }
    return new Rectangle(radX, radY);
  }
}
