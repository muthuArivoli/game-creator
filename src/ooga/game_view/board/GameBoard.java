package ooga.game_view.board;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ooga.game_view.board.tile.RectangleTile;

public class GameBoard extends BorderPane {
  private double tileWidth;
  private double tileHeight;
  private double displayWidth;
  private double displayHeight;
  private double boardSideLength;
  private double displayBorderWidth= 5.0;

  private List<Shape> tileList;

  private HBox displayBox = new HBox();
  private Group tileGroup = new Group();

  public GameBoard(int numRowTiles, int numColTiles, List<Color> colors, double width, double height){
    calculateSize(width, height);

    tileWidth = boardSideLength/numRowTiles;
    tileHeight = boardSideLength/numColTiles;

    createPieceDisplay();
    createTiles(numRowTiles, numColTiles, colors);
    BorderPane.setAlignment(tileGroup, Pos.TOP_CENTER);
    this.setCenter(tileGroup);
    this.setBottom(displayBox);
    this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(
        displayBorderWidth))));
}

  private void calculateSize(double dispWidth, double dispHeight) {
    displayWidth = dispWidth * 0.80;
    displayHeight = dispHeight * 0.14;
    boardSideLength = dispHeight - displayHeight;
    if (displayWidth < boardSideLength) {
      boardSideLength = displayWidth * 0.62;
    }
    this.setMaxSize(displayWidth, dispHeight);
    displayBox.setPrefSize(displayWidth, displayHeight);
  }

  private void createPieceDisplay() {
    displayBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    displayBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,
        null,new BorderWidths(3))));
  }

  private void createTiles(int numRow, int numCol, List<Color> colors){
    for (int x = 0; x < numRow; x++){
      for (int y= 0; y < numCol; y++){
        Color main = colors.get(0);
        if ((x+y) % 2 == 0){main = colors.get(1);}
        RectangleTile tile = new RectangleTile(tileWidth, tileHeight, x, y, main);
        tileGroup.getChildren().addAll(tile);
      }
    }
  }

}
