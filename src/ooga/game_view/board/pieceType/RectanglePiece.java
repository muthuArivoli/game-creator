package ooga.game_view.board.pieceType;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.controller.GameController;

public class RectanglePiece extends PieceShape {

  public RectanglePiece(GameController gameController, double tileX, double tileY, int row, int col, Color tileColor){
    super(gameController, tileX, tileY, row, col, tileColor);
  }

  @Override
  protected Shape createShape(double tileX, double tileY) {
    Rectangle piece = new Rectangle(tileY*0.9, tileX*0.9);
    return piece;
  }
}
