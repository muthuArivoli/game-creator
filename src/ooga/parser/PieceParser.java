package ooga.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.piece.Piece;
import ooga.piece.movement.Movement;
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
    int pieceSide = Integer.parseInt(pieceSymbol.replaceAll("[a-zA-Z]",""));

    if (!myPiecesJSON.has(pieceName)) throw new InvalidPieceException("Piece: " + pieceName + " is not defined.");
    JSONObject pieceJSON = myPiecesJSON.getJSONObject(pieceName);

    JSONArray normalMovesJSON = pieceJSON.getJSONArray("normalMoves");
    JSONArray captureMovesJSON = pieceJSON.getJSONArray("captureMoves");

    List<List<Movement>> normalMoves = new ArrayList<List<Movement>>();
    for (int i = 0; i < normalMovesJSON.length(); i++){
      JSONArray moveSetJSON = normalMovesJSON.getJSONArray(i);
      List <Movement> moveSet = new ArrayList<>();
      for (int j = 0; j < moveSetJSON.length(); j++) {
        moveSet.append(new Movement());
      }
      normalMoves.append(moveSet);
    }
//    for (String pieceField: PIECE_FIELDS) {
//        if (pieceJSON.has(pieceField)){
//          System.out.println("\t"+pieceField + ": " + pieceJSON.get(pieceField));
//        }
//    }

    return new Piece(
        pieceName,
        pieceSide,

    );
  }
}
