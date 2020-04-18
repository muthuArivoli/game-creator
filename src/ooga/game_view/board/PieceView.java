package ooga.game_view.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PieceView extends Ellipse {
  private double xRadius;
  private double yRadius;

  public PieceView (double tileX, double tileY, int xCoordinate, int yCoordinate){
    setParameters(tileX, tileY);
    relocate(xCoordinate*tileY, yCoordinate*tileX);
    setOnMouseClicked(e -> {
      this.setFill(Color.BLUE);
    });
  }

  private void setParameters(double tileX, double tileY){
    this.setRadiusX(tileY*0.95/2);
    this.setRadiusY(tileX*0.95/2);
    this.setFill(Color.RED);
    this.setStroke(Color.BLACK);
  }

}