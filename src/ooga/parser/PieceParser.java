package ooga.parser;

import ooga.exceptions.InvalidPieceException;
import org.json.JSONArray;
import org.json.JSONObject;

public class PieceParser {
  private JSONArray myPieceJSONs;
  private String [] PIECE_FIELDS = {
      "name", "abbreviation", "canPlace", "canMove", "canJump"
  };
  public PieceParser (JSONArray pieceJSONs) throws InvalidPieceException {
    this.myPieceJSONs = pieceJSONs;
    parsePieces();
  }

  private void parsePieces() throws InvalidPieceException {
    for (int i = 0; i < myPieceJSONs.length(); i++) {
      JSONObject pieceJSON = myPieceJSONs.getJSONObject(i);

      for (String pieceField: PIECE_FIELDS) {
        if (pieceJSON.has(pieceField)){
          System.out.println(pieceJSON.get(pieceField));
        }
      }
    }
  }

}
