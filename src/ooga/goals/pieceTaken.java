package ooga.goals;

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
    boolean side1Exists = false;
    boolean side2Exists = false;
    for (int i = 0; i < bounds.getRow(); i++) {
      for (int j = 0; j < bounds.getCol(); j++) {
        Coordinate c = new Coordinate(i, j);
        if (gridModel.checkPieceExists(c) && myTargets.contains(gridModel.getPiece(c).getPieceName())) {
          if (gridModel.getPiece(c).getSide() == 1) {
            side1Exists = true;
          } else {
            side2Exists = true;
          }
        }
      }
    }

    if (!side1Exists) return 1;
    if (!side2Exists) return 2;
    return -1;
  }
}
