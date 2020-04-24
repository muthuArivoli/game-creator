package ooga.goals;

import java.util.List;
import ooga.models.GridModel;
import ooga.piece.Piece;

public class pieceTaken extends MultiGoal implements Goal{

  public pieceTaken(List<String> targets) {
    super(targets);
  }

  @Override
  public int getWinner(GridModel gridModel, Piece lastPiece) {
    return -1;
  }
}
