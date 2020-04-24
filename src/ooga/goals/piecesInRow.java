package ooga.goals;

import ooga.models.GridModel;

public class piecesInRow extends NumericGoal implements Goal{

  public piecesInRow(int myTarget) {
    super(myTarget);
  }

  @Override
  public int checkGoal(GridModel gridModel) {
    return -1;
  }
}
