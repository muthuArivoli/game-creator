package ooga.models;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ooga.AI.AI;
import ooga.AI.AIFactory;
import ooga.goals.Goal;
import org.json.JSONObject;

public class GameModel {
  private boolean canPlace;
  private JSONObject pieceJSON;
  private List<Goal> myGoals;
  private AIFactory myAIFactory = new AIFactory();
  private String aiType;

  public GameModel () {
    this.myGoals = new ArrayList<>();
  }
  public void setCanPlace (boolean canPlace) {
    this.canPlace = canPlace;
  }

  public void setPieceJSON (JSONObject pieceJSON) {
    this.pieceJSON = pieceJSON;
  }

  public boolean isCanPlace() {
    return canPlace;
  }

  public JSONObject getPieceJSON () {
    return pieceJSON;
  }

  public void addGoal (Goal goal) {
    myGoals.add(goal);
  }

  public List<Goal> getGoals() {
    return myGoals;
  }

  public void setAiType(String aiType) {
    this.aiType = aiType;
  }

  public AI getNewGameAI(GridModel myGridModel, int activePlayer) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    return myAIFactory.getAIClass(aiType, myGridModel, activePlayer);
  }
}
