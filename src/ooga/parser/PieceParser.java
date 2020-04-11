package ooga.parser;

import org.json.JSONArray;
import org.json.JSONObject;

public class PieceParser {
  private JSONArray myPieceJSONs;
  private String [] PIECE_FIELDS = {
      "name", "abbreviation", "canPlace", "canMove", "canJump"
  };
  public PieceParser (JSONArray pieceJSONs) {
    this.myPieceJSONs = pieceJSONs;

    parsePieces();
  }

  private void parsePieces() {
    for (int i = 0; i < myPieceJSONs.length(); i++) {
      JSONObject pieceJSON = new JSONObject(myPieceJSONs.get(i));
      for (String pieceField: PIECE_FIELDS) {
        // update the piece object field
      }
    }
  }

}
