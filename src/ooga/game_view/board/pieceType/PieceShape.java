package ooga.game_view.board.pieceType;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ooga.controller.GameController;

public abstract class PieceShape extends StackPane {
  private GameController gameController;
  private Text text;
  private Shape shape;
  private boolean fillerPiece;

  public PieceShape(GameController gameController, double tileX, double tileY, int row, int col, String pieceName) {
    text = new Text(pieceName);
    fillerPiece = false;
    initiatePiece(gameController, tileX, tileY, row, col);
  }

  public PieceShape(GameController gameController, double tileX, double tileY, int row, int col, Color tileColor){
    text = new Text("");
    fillerPiece = true;
    initiatePiece(gameController, tileX, tileY, row, col);
    shape.setStroke(tileColor);
    shape.setFill(tileColor);
  }

  private void initiatePiece(GameController gameController, double tileX, double tileY, int row, int col){
    this.gameController = gameController;
    shape = createShape(tileX, tileY, row, col);

    relocate(col*tileY, row*tileX);
    getChildren().addAll(shape, text);

    setOnMouseClicked(e -> {
      this.gameController.handleClick(row, col);
    });
  }

  abstract protected Shape createShape(double tileX, double tileY, int row, int col);

}
