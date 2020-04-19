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

  public PieceView (GameController gameController, double tileX, double tileY, int xCoordinate, int yCoordinate){
    this.gameController = gameController;
    setParameters(tileX, tileY);
    relocate(xCoordinate*tileY, yCoordinate*tileX);

    this.row = yCoordinate;
    this.col = xCoordinate;

    setOnMouseClicked(e -> {
      this.gameController.handleClick(yCoordinate, xCoordinate);
    });
  }

  private void setParameters(double tileX, double tileY){
    this.setRadiusX(tileY*0.95/2);
    this.setRadiusY(tileX*0.95/2);
    this.setFill(Color.RED);
    this.setStroke(Color.BLACK);
  }

}
