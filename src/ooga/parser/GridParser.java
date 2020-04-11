package ooga.parser;

import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GridParser {
  private JSONArray myGridJSON;
  private PieceParser myPieceParser;

  public GridParser (JSONArray gridJSON, JSONObject piecesJSON) {
    this.myGridJSON = gridJSON;
    this.myPieceParser = new PieceParser(piecesJSON);
  }

  public void parseGrid () throws InvalidGridException, InvalidPieceException {
    if (!validateGrid()) {
      throw new InvalidGridException("Grid must contain same number of columns per row.");
    }

    for (int i = 0; i < myGridJSON.length(); i++) {
      JSONArray rowJSON = myGridJSON.getJSONArray(i);
      for (int j = 0; j < rowJSON.length(); j++){
        String pieceSymbol = rowJSON.getString(j);

        if(pieceSymbol!=""){
          myPieceParser.generatePiece(pieceSymbol);
        }
      }
    }
  }

  private boolean validateGrid () {
//    for (int i = 0; i < myGridJSON.length(); i++){
//
//    }
    return true;
  }

}
