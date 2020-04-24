package ooga.exceptions;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameExceptions extends Exception {
  public GameExceptions(String errorMsg) {
    super(errorMsg);
    Stage s = new Stage();
    HBox test = new HBox();
    Text t = new Text(errorMsg);
    test.setAlignment(Pos.CENTER);
    test.getChildren().add(t);
    Scene temporaryScene = new Scene(test,100,100);
    s.setScene(temporaryScene);
    s.show();
  }
}
