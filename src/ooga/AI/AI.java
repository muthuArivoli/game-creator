package ooga.AI;

import ooga.goals.Goal;
import ooga.models.GameModel;
import ooga.piece.Coordinate;

import java.util.List;

public interface AI {
    List<Coordinate> getBestMove(int depthOfAnalysis);
    void setMyGoal(Goal myGoal);
    void setMyGameModel(GameModel myGameModel);
}
