package ooga.game_view.board;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import ooga.game_view.board.tile.CircleTile;
import ooga.game_view.board.tile.RectangleTile;
import ooga.models.GridModel;
import ooga.piece.Piece;

public class GameBoard extends BorderPane {
  private double tileWidth;
  private double tileHeight;
  private double displayWidth;
  private double displayHeight;
  private double boardSideLength;

  private List<Shape> tileList;

  private HBox displayBox = new HBox();
  private Group tileGroup = new Group();
  private Group pieceGroup = new Group();
  private StackPane everything = new StackPane();

  public GameBoard(){
    this.getStyleClass().add("GameBoard");
    everything.setBackground(this.getBackground());
  }

  public void createGameBoard(GridModel gridModel, List<Color> colors, double width, double height){
    calculateSize(width, height);
    int numRowTiles = gridModel.getGrid().length;
    int numColTiles = gridModel.getGrid()[0].length;
    tileWidth = boardSideLength/numRowTiles;
    tileHeight = boardSideLength/numColTiles;
    createPieceDisplay();
    createTiles(gridModel, numRowTiles, numColTiles, colors);
    everything.setMaxSize(boardSideLength, boardSideLength);
    BorderPane.setAlignment(everything, Pos.TOP_CENTER);
    this.setCenter(everything);
    this.setBottom(displayBox);
  }

  private void calculateSize(double dispWidth, double dispHeight) {
    displayWidth = dispWidth * 0.80;
    displayHeight = dispHeight * 0.14;
    boardSideLength = dispHeight - displayHeight;
    if (displayWidth < boardSideLength) {
      boardSideLength = displayWidth * 0.62;
    }
    this.setMaxSize(displayWidth, dispHeight);
  }

  private void createPieceDisplay() {
    displayBox.setPrefSize(displayWidth, displayHeight);
    displayBox.getStyleClass().add("displayBox");
  }

  private void createTiles(GridModel grid, int numRow, int numCol, List<Color> colors){
    for (int x = 0; x < numRow; x++){
      for (int y= 0; y < numCol; y++){
        Color main = colors.get(0);
        if ((x+y) % 2 == 0){main = colors.get(1);}
        RectangleTile tile = new RectangleTile(tileWidth, tileHeight, x, y, main);
        if(grid.getGrid()[y][x]!= null){
          CircleTile piece = new CircleTile(tileWidth, tileHeight, x, y, Color.RED);
          pieceGroup.getChildren().addAll(piece);
        }
        tileGroup.getChildren().addAll(tile);
      }
    }
    everything.getChildren().addAll(tileGroup, pieceGroup);
  }

}
