package ooga.game_view.board;

import java.util.ArrayList;
import java.util.Collection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.GameController;

public class ExtraPiecesDisplay extends HBox {
  private GameController gameController;
  private String currentStylesheet;
  private ArrayList<String> pieceList;
  private Button choosePiece;

  private double displayWidth;
  private double displayHeight;

  public ExtraPiecesDisplay(double width, double height, GameController gameController, String stylesheet){
    this.gameController = gameController;
    this.currentStylesheet = stylesheet;
    displayWidth = width;
    displayHeight = height;
    this.setPrefSize(width, height);
    this.getStyleClass().add("displayBox");
    this.setAlignment(Pos.CENTER);

    pieceList = new ArrayList<>();
  }

  public void addPieces(String pieceName){
    pieceList.add(pieceName);
  }

  public Collection<String> getPieces(){
    return pieceList;
  }

  public void updateStyleSheet(String stylesheet){
    currentStylesheet = stylesheet;
  }

  public void addMainButton(String buttonName){
    this.getChildren().removeAll(choosePiece);
    choosePiece = new Button(buttonName);
    choosePiece.setPrefSize(displayWidth/2, displayHeight/2);
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
    Scene temporaryScene = new Scene(rt, 200,pieceList.size()*50);
    temporaryScene.getStylesheets().add(currentStylesheet);
    s.setScene(temporaryScene);
    s.show();
  }
}
