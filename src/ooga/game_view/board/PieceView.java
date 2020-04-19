package ooga.game_view.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import ooga.controller.GameController;

public class PieceView extends Ellipse {
  private GameController gameController;
  private double xRadius;
  private double yRadius;
  private int row;
  private int col;

  private boolean fillerPiece;

  public PieceView (GameController gameController, double tileX, double tileY, int row, int col){
    setParameters(tileX, tileY);
    relocate(col*tileY, row*tileX);

    this.row = row;
    this.col = col
  }

  private void setParameters(double tileX, double tileY){
    this.setRadiusX(tileY*0.95/2);
    this.setRadiusY(tileX*0.95/2);
    this.setFill(Color.RED);
    this.setStroke(Color.BLACK);
  }

}
