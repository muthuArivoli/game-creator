package ooga.goals;

import ooga.models.GridModel;

public class piecesInRow extends NumericGoal implements Goal{

  public piecesInRow(int myTarget) {
    super(myTarget);
  }

  @Override
  public boolean checkGoal(GridModel gridModel) {
    return false;
  }
}
