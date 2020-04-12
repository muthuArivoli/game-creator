package ooga.game_view.board;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ooga.game_view.board.tile.CircleTile;
import ooga.game_view.board.tile.RectangleTile;

public class GameBoard extends BorderPane {

  private static final int DISPLAY_WIDTH = 1000;
  private static final int DISPLAY_HEIGHT = 720;
  private static final int BOARD_WIDTH = 1000;
  private static final int BOARD_HEIGHT = 720;
  public static final double DISPLAY_BORDER_WIDTH = 5.0;

  private double tileWidth;
  private double tileHeight;
  private Color backgroundColor;
  private HBox displayBox;
  private Group tileGroup = new Group();

  public GameBoard(int numRowTiles, int numColTiles, List<Color> colors){
    tileWidth = BOARD_WIDTH/numRowTiles;
    tileHeight = BOARD_HEIGHT/numColTiles;

    createPieceDisplay();
    createTiles(numRowTiles, numColTiles, colors);
    setBackgroundColor(Color.WHITE);
    this.setCenter(tileGroup);
    this.setBottom(displayBox);
    this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(
        DISPLAY_BORDER_WIDTH))));
}

  private void createPieceDisplay() {
    displayBox = new HBox();
    displayBox.setPrefSize(DISPLAY_WIDTH,DISPLAY_HEIGHT);
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

  public void setBackgroundColor(Color clr){
    backgroundColor = clr;
    this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
  }

}
