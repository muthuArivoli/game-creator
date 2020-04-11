package ooga.parser;

import java.util.Map;
import ooga.exceptions.InvalidPieceException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Maps each piece abbreviation to its corresponding JSON object
 */
public class PieceMapper {
  private JSONArray myPieceJSONs;

  public PieceMapper (JSONArray pieceJSONs) throws DuplicatePieceNameException {
    this.myPieceJSONs = pieceJSONs;
  }

  public Map<String, JSONObject> generatePieceMap () throws InvalidPieceException {
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
