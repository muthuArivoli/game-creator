package ooga.game_view;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameBoard extends Pane {

  private static final String GAME_BOARDS = "src/ooga/resources/images/gameBoards/";

  private static Color backgroundColor;

  public GameBoard(String fileAddress){
    Image image = new Image("File:" + GAME_BOARDS + fileAddress);
    ImageView boardpic = new ImageView();
    boardpic.setFitWidth(1000);
    boardpic.setFitHeight(680);
    boardpic.setImage(image);
    this.getChildren().addAll(boardpic);
  }

  private void setBackground(Color c) {
    setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
    backgroundColor = c;
  }

}
