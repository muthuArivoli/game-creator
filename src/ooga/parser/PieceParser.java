package ooga.parser;

import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.piece.Piece;
import org.json.JSONArray;
import org.json.JSONObject;

public class PieceParser {
  private JSONObject myPiecesJSON;
  private String [] PIECE_FIELDS = {
      "canPlace", "canMove", "canJump"
  };
  public PieceParser (JSONObject piecesJSON){
    this.myPiecesJSON = piecesJSON;
  }

  /**
   * Returns a piece object
   * @param pieceSymbol
   * @throws InvalidPieceException
   */
  public Piece generatePiece(String pieceSymbol) throws InvalidPieceException {
    String pieceName = pieceSymbol.replaceAll("[\\d]","");
    String pieceSide = pieceSymbol.replaceAll("[a-zA-Z]","");
    System.out.println(pieceName + " " + pieceSide);

    if (!myPiecesJSON.has(pieceName)) throw new InvalidPieceException("Piece: " + pieceName + " is not defined.");
    JSONObject pieceJSON = myPiecesJSON.getJSONObject(pieceName);
    for (String pieceField: PIECE_FIELDS) {
        if (pieceJSON.has(pieceField)){
          System.out.println("\t"+pieceField + ": " + pieceJSON.get(pieceField));
        }
      }
    return newPiece;
  }


}
