package ooga.game_view.board.pieceType;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import ooga.controller.GameController;

public abstract class PieceShape extends StackPane {
  private GameController gameController;
  private Text text;
  private Shape shape;
  private boolean fillerPiece;

  public PieceShape(GameController gameController, double tileX, double tileY, int row, int col, String pieceName) {
    this.gameController = gameController;
    fillerPiece = false;
    text = new Text(pieceName);
    shape = createShape(tileX, tileY, row, col);

    relocate(col*tileY, row*tileX);
    getChildren().addAll(text, shape);

    if(fillerPiece){
      setOnMouseClicked(e -> {
        this.gameController.handleClick(row, col);
      });
    }
  }

  public void setFillerPiece(boolean fillerPiece, Color tileColor) {
    this.fillerPiece = fillerPiece;
    if(fillerPiece){
      shape.setStroke(tileColor);
      shape.setFill(tileColor);
    }
  }

  abstract protected Shape createShape(double tileX, double tileY, int row, int col);

}
