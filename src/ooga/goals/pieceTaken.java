package ooga.goals;

import java.util.List;
import ooga.models.GridModel;

public class pieceTaken extends MultiGoal implements Goal{

  public pieceTaken(List<String> targets) {
    super(targets);
  }

  @Override
  public boolean checkGoal(GridModel gridModel) {
    return false;
  }
}
