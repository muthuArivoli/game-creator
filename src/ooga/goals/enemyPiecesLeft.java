package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Piece;

public class enemyPiecesLeft extends NumericGoal implements Goal {

  public enemyPiecesLeft(int myTarget) {
    super(myTarget);
  }

  @Override
  public int getWinner(GridModel gridModel, Piece lastPiece) {
    int side = lastPiece.getSide();


    return -1;
  }
}
