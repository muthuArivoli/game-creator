package ooga.parser;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.piece.Piece;
import ooga.piece.movement.Movement;
import ooga.piece.movement.MovementFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class PieceParser {
  private JSONObject myPiecesJSON;
  private String [] PIECE_FIELDS = {
      "canPlace", "canMove", "canJump"
  };
  private MovementFactory factory = new MovementFactory();
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

//    for (String pieceField: PIECE_FIELDS) {
//        if (pieceJSON.has(pieceField)){
//          System.out.println("\t"+pieceField + ": " + pieceJSON.get(pieceField));
//        }
//    }

    JSONObject pieceJSON = myPiecesJSON.getJSONObject(pieceName);

    return new Piece(
        pieceName,
        pieceSide,
        generateMoves(pieceJSON.getJSONArray("normalMoves")),
        generateMoves(pieceJSON.getJSONArray("captureMoves")),
        pieceJSON.getBoolean("canCapture"),
        pieceJSON.getBoolean("canPlace"),
        pieceJSON.getBoolean("canJump")
    );
  }

  private List<List<Movement>> generateMoves(JSONArray movesJSON)
      throws InvalidPieceException {
    List<List<Movement>> moves = new ArrayList<List<Movement>>();

    for (int i = 0; i < movesJSON.length(); i++){
      JSONArray moveSetJSON = movesJSON.getJSONArray(i);
      List <Movement> moveSet = new ArrayList<>();
      for (int j = 0; j < moveSetJSON.length(); j++) {
        JSONObject singleMoveJSON = moveSetJSON.getJSONObject(j);

        Iterator<String> keys = singleMoveJSON.keys();
        String moveName = "";
        while(keys.hasNext()) {
          String key = keys.next();
          moveName = singleMoveJSON.getString(key);
        }
        try {
          moveSet.add(factory.getMovement(moveName));
        } catch (ClassNotFoundException | InstantiationException |NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
          throw new InvalidPieceException("Incorrect Move Specified");
        }
      }
      moves.add(moveSet);
    }

    return moves;
  }
}
