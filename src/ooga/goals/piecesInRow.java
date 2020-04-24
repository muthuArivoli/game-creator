package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Piece;

public class piecesInRow extends NumericGoal implements Goal{

  public piecesInRow(int myTarget) {
    super(myTarget);
  }

  @Override
  public int checkGoal(GridModel gridModel, Piece lastPiece) {

    return -1;
  }
}
