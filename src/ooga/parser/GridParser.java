package ooga.parser;

import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GridModel;
import ooga.piece.Piece;
import org.json.JSONArray;
import org.json.JSONObject;

public class GridParser {
  private JSONArray myGridJSON;
  private PieceParser myPieceParser;
  private GridModel myGridModel;

  public GridParser (JSONArray gridJSON, JSONObject piecesJSON)
      throws InvalidGridException, InvalidPieceException {
    this.myGridJSON = gridJSON;
    this.myPieceParser = new PieceParser(piecesJSON);
    this.myGridModel = new GridModel();
    populateGridModel();
  }

  private void populateGridModel () throws InvalidGridException, InvalidPieceException {
    if (!validateGrid()) {
      throw new InvalidGridException("Grid must contain same number of columns per row.");
    }

    for (int i = 0; i < myGridJSON.length(); i++) {
      JSONArray rowJSON = myGridJSON.getJSONArray(i);
      for (int j = 0; j < rowJSON.length(); j++){
        String pieceSymbol = rowJSON.getString(j);

        if(!pieceSymbol.equals("")){
          Piece newPiece = myPieceParser.generatePiece(pieceSymbol, i, j);
          myGridModel.addPiece(newPiece);
        }
      }
    }
  }

  private boolean validateGrid () {
    return true;
  }

  public GridModel getMyGridModel(){return myGridModel;}

}
