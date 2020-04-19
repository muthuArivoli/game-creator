package ooga.parser;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.piece.Piece;
import ooga.piece.movement.CompositeMovement;
import ooga.piece.movement.Movement;
import ooga.piece.movement.MovementFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class PieceParser {
  private JSONObject myPiecesJSON;
  private MovementFactory factory = new MovementFactory();
  public PieceParser (JSONObject piecesJSON){
    this.myPiecesJSON = piecesJSON;
  }

  /**
   * Returns a piece object
   * @param pieceSymbol
   * @throws InvalidPieceException
   */
  public Piece generatePiece(String pieceSymbol, int row, int column) throws InvalidPieceException {
    String pieceName = pieceSymbol.replaceAll("[\\d]","");
    int pieceSide = Integer.parseInt(pieceSymbol.replaceAll("[a-zA-Z]",""));

    if (!myPiecesJSON.has(pieceName)) throw new InvalidPieceException("Piece: " + pieceName + " is not defined.");

//    for (String pieceField: PIECE_FIELDS) {
//        if (pieceJSON.has(pieceField)){
//          System.out.println("\t"+pieceField + ": " + pieceJSON.get(pieceField));
//        }
//    }

    JSONObject pieceJSON = myPiecesJSON.getJSONObject(pieceName);
    JSONObject normalMovesJSON = pieceJSON.getJSONObject("normalMoves");

    return new Piece(
        pieceName,
        pieceSide,
        row,
        column,
        normalMovesJSON.has("firstTime") ? generateMoves(normalMovesJSON.getJSONArray("firstTime")) : new ArrayList<Movement>(),
        normalMovesJSON.has("anyTime") ? generateMoves(normalMovesJSON.getJSONArray("anyTime")) : new ArrayList<Movement>(),
        generateMoves(pieceJSON.getJSONArray("captureMoves")),
        pieceJSON.getInt("canJump") > 0,
        pieceJSON.getInt("canPlace") > 0,
        pieceJSON.getInt("canCaptureJump") > 0
    );
  }

  private List<Movement> generateMoves(JSONArray movesJSON)
      throws InvalidPieceException {
    List<Movement> moves = new ArrayList<>();

    for (int i = 0; i < movesJSON.length(); i++){
      JSONArray moveSetJSON = movesJSON.getJSONArray(i);
      CompositeMovement moveSet = new CompositeMovement();
      for (int j = 0; j < moveSetJSON.length(); j++) {
        JSONObject singleMoveJSON = moveSetJSON.getJSONObject(j);

        Iterator<String> keys = singleMoveJSON.keys();
        String moveName = "";
        int rbegin = 1;
        int rend = -1; //-1 stands for infinity
        while(keys.hasNext()) {
          String key = keys.next();
          moveName = key;
          JSONArray range = singleMoveJSON.getJSONArray(moveName);
          rbegin = range.getInt(0);
          rend = range.getInt(1);
        }
        try {
          moveSet.add(factory.getMovement(moveName, rbegin, rend));
        } catch (ClassNotFoundException | InstantiationException |NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
          throw new InvalidPieceException("Invalid Move Specified: " + moveName + "..." + rbegin + "," +rend);
        }
      }
      moves.add(moveSet);
    }

    return moves;
  }
}
