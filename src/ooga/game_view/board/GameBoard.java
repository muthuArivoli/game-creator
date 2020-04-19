package ooga.game_view.board;

import java.util.Collection;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ooga.controller.GameController;
import ooga.game_view.board.tile.CircleTile;
import ooga.game_view.board.tile.RectangleTile;
import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class GameBoard extends BorderPane {
  private GameController gameController;
  private GridModel gridModel;
  private double tileWidth;
  private double tileHeight;
  private double displayWidth;
  private double displayHeight;
  private double boardSideLength;
  private Background boardBackground;

  private HBox displayBox = new HBox();
  private StackPane everything;

  private int numRowTiles;
  private int numColTiles;
  private List<Color>colors;

  public GameBoard(){
    this.getStyleClass().add("GameBoard");
    boardBackground = this.getBackground();
  }

  public void createGameBoard(GameController gameController, List<Color> colors, double width, double height){
    this.gameController = gameController;
    this.gridModel = gameController.getGridModel();
    this.colors = colors;

    everything = new StackPane();
    everything.setBackground(boardBackground);
    everything.setMaxSize(boardSideLength, boardSideLength);
    BorderPane.setAlignment(everything, Pos.TOP_CENTER);
    calculateSize(width, height);

    numRowTiles = gridModel.getGrid().length;
    numColTiles = gridModel.getGrid()[0].length;
    tileWidth = boardSideLength/numRowTiles;
    tileHeight = boardSideLength/numColTiles;
    createPieceDisplay();
    createTiles(gridModel, numRowTiles, numColTiles, colors);
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

  private void createTiles(GridModel grid, int numRowTiles, int numColTiles, List<Color> colors){
    List<Coordinate> validCoordinates = gameController.getValidMoves();
    Group tileGroup = new Group();
    Group pieceGroup = new Group();
    for (int col = 0; col < numColTiles; col++){
      for (int row= 0; row < numRowTiles; row++) {
        Color main = colors.get(0);
        if ((col + row) % 2 == 0) {
          main = colors.get(1);
        }
        RectangleTile tile = new RectangleTile(gameController, tileWidth, tileHeight, row, col,
            main);
        if(new Coordinate(row, col).equals(gameController.getSelectedPiecePosition())){
          tile.setFill(Color.LIGHTBLUE);
        }else if (validCoordinates.contains(new Coordinate(row,col))) {
          tile.setFill(Color.LIGHTGREEN);
        }
        if(grid.getGrid()[row][col]!= null){
          PieceView piece = new PieceView(gameController, tileWidth, tileHeight, row, col);
          pieceGroup.getChildren().addAll(piece);
        }
        tileGroup.getChildren().addAll(tile);
      }
    }
    everything.getChildren().addAll(tileGroup, pieceGroup);
  }

  public void updateDisplay () {
    everything.getChildren().clear();

//    Piece[][] myGrid = gameController.getGridModel().getGrid();
//        for (int i = 0; i < myGrid.length; i++) {
//      for (int j = 0; j < myGrid[0].length; j++) {
//        System.out.print(myGrid[i][j]);
//      }
//      System.out.println();
//    }

    createTiles(gridModel, numRowTiles, numColTiles, colors);
  }
}
