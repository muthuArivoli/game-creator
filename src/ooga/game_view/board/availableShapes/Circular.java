package ooga.game_view.board.availableShapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import ooga.controller.GameController;

public class Circular extends ComponentShape {

  public Circular(GameController gameController, double tileX, double tileY, int row, int col, Color tileColor){
    super(gameController, tileX, tileY, row, col, tileColor);
  }

  @Override
  protected Shape createShape(double tileX, double tileY, boolean isPiece) {
    double radX = tileY/2;
    double radY = tileX/2;
    if (isPiece){
      radX = radX*0.9;
      radY = radY*0.9;
    }
    return new Ellipse(radX, radY);
  }
}
