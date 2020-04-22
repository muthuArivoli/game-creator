package ooga.game_view.board;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ExtraPiecesDisplay extends HBox {

  public ExtraPiecesDisplay(double width, double height){
    this.setPrefSize(width, height);
    this.getStyleClass().add("displayBox");
    this.setAlignment(Pos.CENTER);

    addMainButton(width, height);
  }

  private void addMainButton(double width, double height){
    Button choosePiece = new Button("CHOOSE EXTRA PIECE");
    choosePiece.setPrefSize(width/2, height/2);
    choosePiece.setOnAction(e -> displayAvailablePieces());
    this.getChildren().addAll(choosePiece);
  }

  private void displayAvailablePieces() {
  }
}
