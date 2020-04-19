package ooga.game_view.board.pieceType;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import ooga.controller.GameController;

public class EllipsePiece extends PieceShape {

  public EllipsePiece(GameController gameController, double tileX, double tileY, int row, int col, String pieceName){
    super(gameController, tileX, tileY, row, col, pieceName);
  }

  public EllipsePiece(GameController gameController, double tileX, double tileY, int row, int col, Color tileColor){
    super(gameController, tileX, tileY, row, col, tileColor);
  }

  @Override
  protected Shape createShape(double tileX, double tileY, int row, int col) {
    Ellipse piece = new Ellipse(tileY*0.95/2, tileX*0.95/2);
    piece.setFill(Color.RED);
    piece.setStroke(Color.BLACK);
    return piece;
  }
}
