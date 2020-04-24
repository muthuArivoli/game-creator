package ooga.game_view.board.availableShapes;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import ooga.controller.GameController;

public abstract class ComponentShape extends StackPane {
  private GameController gameController;
  private Text text;
  private Shape shape;
  private boolean isPiece;

  public ComponentShape(GameController gameController, double tileX, double tileY, int row, int col, Color tileColor, boolean isPiece){
    text = new Text("");
    this.isPiece = isPiece;
    initiatePiece(gameController, tileX, tileY, row, col);
    shape.setStroke(tileColor);
    shape.setFill(tileColor);
  }

  public void setColor(Color clr){
    shape.setFill(clr);
    shape.setStroke(Color.BLACK);
  }

  public void addName(String pieceName){
    text.setText(pieceName);
  }

  private void initiatePiece(GameController gameController, double tileX, double tileY, int row, int col){
    this.gameController = gameController;
    shape = createShape(tileX, tileY, isPiece);

    relocate(col*tileY, row*tileX);
    getChildren().addAll(shape, text);

    setOnMouseClicked(e -> {
      this.gameController.handleClick(row, col);
    });
  }

  abstract protected Shape createShape(double tileX, double tileY, boolean isPiece);

}
