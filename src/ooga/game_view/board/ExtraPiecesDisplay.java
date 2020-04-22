package ooga.game_view.board;

import java.awt.List;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.GameController;

public class ExtraPiecesDisplay extends HBox {
  private GameController gameController;
  private ArrayList<String> pieceList;

  public ExtraPiecesDisplay(double width, double height, GameController gameController){
    this.gameController = gameController;
    this.setPrefSize(width, height);
    this.getStyleClass().add("displayBox");
    this.setAlignment(Pos.CENTER);

    pieceList = new ArrayList<>();
    addMainButton(width, height);
  }

  public void addPieces(String pieceName){
    pieceList.add(pieceName);
  }

  private void addMainButton(double width, double height){
    Button choosePiece = new Button("CHOOSE EXTRA PIECE");
    choosePiece.setPrefSize(width/2, height/2);
    choosePiece.setOnAction(e -> displayAvailablePieces());
    this.getChildren().addAll(choosePiece);
  }

  private void displayAvailablePieces() {
    VBox rt = new VBox();
    rt.setAlignment(Pos.CENTER);
    for (String name: pieceList){
      Button piece = new Button(name);
      //piece.setOnAction(e -> gameController.handleClick(name));
      rt.getChildren().addAll(piece);
    }
    Stage s = new Stage();
    s.setTitle("CHOOSE EXTRA PIECE");
    Scene temporaryScene = new Scene(rt, 250,250);
    s.setScene(temporaryScene);
    s.show();
  }
}
