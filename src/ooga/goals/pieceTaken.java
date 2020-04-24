package ooga.goals;

import java.util.ArrayList;
import java.util.List;
import ooga.models.GridModel;
import ooga.piece.Coordinate;
import ooga.piece.Piece;

public class pieceTaken extends MultiGoal implements Goal{

  public pieceTaken(List<String> targets) {
    super(targets);
  }

  @Override
  public int getWinner(GridModel gridModel, Piece lastPiece) {
    Coordinate bounds = gridModel.getDimensions();

    List<String> side1Targets = cloneTargets();
    List<String> side2Targets = cloneTargets();
    
    for (int i = 0; i < bounds.getRow(); i++) {
      for (int j = 0; j < bounds.getCol(); j++) {
        Coordinate c = new Coordinate(i, j);
        if (gridModel.checkPieceExists(c) && myTargets.contains(gridModel.getPiece(c).getPieceName())) {
          Piece piece = gridModel.getPiece(c);
          if (piece.getSide() == 1) {
            int ix = side1Targets.indexOf(piece.getPieceName());
            if (ix!=-1) side1Targets.remove(ix);
          } else {
            int ix = side2Targets.indexOf(piece.getPieceName());
            if (ix!=-1) side2Targets.remove(ix);
          }
        }
      }
    }

    System.out.println(side1Targets);
    System.out.println(side2Targets);
    if (side1Targets.size() != 0) return -1;
    if (side2Targets.size() != 0) return 1;
    return 0;
  }

  private List<String> cloneTargets () {
    List<String> clone = new ArrayList<>();
    for (String t: myTargets) {
      System.out.println(t);
      clone.add(t);
    }
    return clone;
  }
}