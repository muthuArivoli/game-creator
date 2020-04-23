package ooga.goals;

import ooga.models.GridModel;
import ooga.piece.Coordinate;

import java.util.List;
import java.util.function.Predicate;


public interface Goal {
    boolean checkGoal(GridModel gridModel);
}
