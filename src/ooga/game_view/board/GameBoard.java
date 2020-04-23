package ooga.game_view.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.game_view.board.pieceType.EllipsePiece;
import ooga.game_view.board.tile.RectangleTile;
import ooga.models.GridModel;
import ooga.piece.Coordinate;

public class GameBoard extends BorderPane {
  private GameController gameController;
  private GridModel gridModel;
  private String currentStyleSheet;
  private double tileWidth;
  private double tileHeight;
  private double displayWidth;
  private double displayHeight;
  private double boardSideLength;
  private Background boardBackground;

  private ExtraPiecesDisplay pieceDisplayBox;
  private StackPane boardDisplay;

  private int numRowTiles;
  private int numColTiles;
  private List<Color> availableColors;
  private List<String> PieceNames;

  public GameBoard(){
    this.getStyleClass().add("GameBoard");
    boardBackground = this.getBackground();
    availableColors = new ArrayList<Color>(Arrays.asList(Color.WHITE, Color.BLACK, Color.RED, Color.CYAN));
  }

  public void createGameBoard(GameController gameController, String styleSheet, List<Color> colors, double width, double height){
    this.gameController = gameController;
    this.currentStyleSheet = styleSheet;
    this.gridModel = gameController.getGridModel();

    boardDisplay = new StackPane();
    boardDisplay.setBackground(boardBackground);
    boardDisplay.setMaxSize(boardSideLength, boardSideLength);
    BorderPane.setAlignment(boardDisplay, Pos.TOP_CENTER);
    calculateSize(width, height);

    numRowTiles = gridModel.getGrid().length;
    numColTiles = gridModel.getGrid()[0].length;
    tileWidth = boardSideLength/numRowTiles;
    tileHeight = boardSideLength/numColTiles;
    pieceDisplayBox = new ExtraPiecesDisplay(displayWidth, displayHeight, gameController, currentStyleSheet);
    populateBoard();
    this.setCenter(boardDisplay);
    this.setBottom(pieceDisplayBox);
  }

  public void updateDisplay () {
    boardDisplay.getChildren().clear();
    populateBoard();
  }

  public void updateColors(List<Color> newColors){
    availableColors = newColors;
    updateDisplay();
  }

  public ExtraPiecesDisplay getPieceDisplayBox(){
    return pieceDisplayBox;
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

  private void populateBoard(){
    Group tileGroup = new Group();
    Group pieceGroup = new Group();
    for (int col = 0; col < numColTiles; col++){
      for (int row= 0; row < numRowTiles; row++) {
        Color tileColor = availableColors.get(0);
        if ((col + row) % 2 == 0) {
          tileColor = availableColors.get(1);
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
    EllipsePiece piece = new EllipsePiece(gameController, tileWidth, tileHeight, row, col, tileColor);
    if(gridModel.getGrid()[row][col]!= null){
      piece.setColor(availableColors.get(2));
      piece.addName(gridModel.getGrid()[row][col].getPieceName());
      piece.isFiller(false);
      if(gridModel.getGrid()[row][col].getSide() == -1){
        piece.setColor(availableColors.get(3));
      }
    }
    pieceGroup.getChildren().addAll(piece);
    tileGroup.getChildren().addAll(tile);
  }
}
