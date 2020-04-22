package ooga.game_view.board;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ooga.controller.GameController;
import ooga.game_view.board.pieceType.EllipsePiece;
import ooga.game_view.board.tile.RectangleTile;
import ooga.models.GridModel;
import ooga.piece.Coordinate;

public class GameBoard extends BorderPane {
  private GameController gameController;
  private GridModel gridModel;
  private double tileWidth;
  private double tileHeight;
  private double displayWidth;
  private double displayHeight;
  private double boardSideLength;
  private Background boardBackground;

  private HBox pieceDisplayBox = new HBox();
  private StackPane boardDisplay;

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

    boardDisplay = new StackPane();
    boardDisplay.setBackground(boardBackground);
    boardDisplay.setMaxSize(boardSideLength, boardSideLength);
    BorderPane.setAlignment(boardDisplay, Pos.TOP_CENTER);
    calculateSize(width, height);

    numRowTiles = gridModel.getGrid().length;
    numColTiles = gridModel.getGrid()[0].length;
    tileWidth = boardSideLength/numRowTiles;
    tileHeight = boardSideLength/numColTiles;
    createPieceDisplay();
    populateBoard();
    this.setCenter(boardDisplay);
    this.setBottom(pieceDisplayBox);
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
    pieceDisplayBox.setPrefSize(displayWidth, displayHeight);
    pieceDisplayBox.getStyleClass().add("displayBox");
    Button choosePiece = new Button("CHOOSE EXTRA PIECE");
    choosePiece.setPrefSize(displayWidth/2, displayHeight/2);
    pieceDisplayBox.setAlignment(Pos.CENTER);
    pieceDisplayBox.getChildren().addAll(choosePiece);
  }

  private void populateBoard(){
    Group tileGroup = new Group();
    Group pieceGroup = new Group();
    for (int col = 0; col < numColTiles; col++){
      for (int row= 0; row < numRowTiles; row++) {
        Color tileColor = colors.get(0);
        if ((col + row) % 2 == 0) {
          tileColor = colors.get(1);
        }
        createTileAndPiece(tileGroup, pieceGroup, row, col, tileColor);
      }
    }
    boardDisplay.getChildren().addAll(tileGroup, pieceGroup);
  }

  private void createTileAndPiece(Group tileGroup, Group pieceGroup, int row, int col, Color tileColor){
    List<Coordinate> validCoordinates = gameController.getValidMoves();
    RectangleTile tile = new RectangleTile(gameController, tileWidth, tileHeight, row, col,
        tileColor);
    if(new Coordinate(row, col).equals(gameController.getSelectedPiecePosition())){
      tile.setFill(Color.LIGHTBLUE);
    }else if (validCoordinates.contains(new Coordinate(row,col))) {
      tile.setFill(Color.LIGHTGREEN);
    }
    EllipsePiece piece;
    if(gridModel.getGrid()[row][col]!= null){
      piece = new EllipsePiece(gameController, tileWidth, tileHeight, row, col, gridModel.getGrid()[row][col].getPieceName());
    }
    else{
      piece = new EllipsePiece(gameController, tileWidth, tileHeight, row, col, tileColor);
    }
    pieceGroup.getChildren().addAll(piece);
    tileGroup.getChildren().addAll(tile);
  }

  public void updateDisplay () {
    boardDisplay.getChildren().clear();

//    Piece[][] myGrid = gameController.getGridModel().getGrid();
//        for (int i = 0; i < myGrid.length; i++) {
//      for (int j = 0; j < myGrid[0].length; j++) {
//        System.out.print(myGrid[i][j]);
//      }
//      System.out.println();
//    }

    populateBoard();
  }
}
