package ooga.goals;

import ooga.models.GridModel;

public class enemyPiecesLeft extends NumericGoal implements Goal {

  public enemyPiecesLeft(int myTarget) {
    super(myTarget);
  }

  @Override
  public int checkGoal(GridModel gridModel) {
    return -1;
  }
}
