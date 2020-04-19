package ooga.models;

import org.json.JSONObject;

public class GameModel {
  private boolean canPlace;
  private JSONObject pieceJSON;

  public void setCanPlace (boolean canPlace) {
    this.canPlace = canPlace;
  }

  public void setPieceJSON (JSONObject pieceJSON) {
    this.pieceJSON = pieceJSON;
  }

  public boolean isCanPlace() {
    return canPlace;
  }

  public JSONObject getPieceJSON () {
    return pieceJSON;
  }
}
